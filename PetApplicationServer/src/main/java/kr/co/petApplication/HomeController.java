package kr.co.petApplication;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Stack;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import kr.co.petApplication.dao.Dao;
import kr.co.petApplication.dto.Bookmark;
import kr.co.petApplication.dto.ImageFile;
import kr.co.petApplication.dto.Mark;
import kr.co.petApplication.dto.Member;
import kr.co.petApplication.dto.Review;
import kr.co.petApplication.dto.ReviewMember;
import kr.co.petApplication.dto.Store;
import kr.co.petApplication.dto.StoreData;
import kr.co.petApplication.dto.StoreImage;
import sun.security.action.GetBooleanAction;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@RequestMapping("searchaddress")
	public String SearchAddress() {
		return "SearchAddress";
	}
	
	@RequestMapping(value="join.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String join(HttpServletRequest request) {
		
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
		String name = request.getParameter("name");
		
		System.out.println(login_id + " " + login_pw + " " + name);
		
		Integer checkId = dao.isAlreadyJoined(login_id);
		
		Boolean checkLoginId = true;
		if (checkId >= 1) {
			checkLoginId = false;
		}
		
		if (checkLoginId) {
			dao.insertMember(login_id, login_pw, name);
		}
	
		Gson gson = new Gson();
		
		return gson.toJson(checkLoginId);
	}
	
	@RequestMapping(value="login.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String login(HttpServletRequest req) {
		
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String login_id = req.getParameter("login_id");
		String login_pw = req.getParameter("login_pw");
		
		Member member = dao.isIdPwRight(login_id, login_pw);
		
		Gson gson = new Gson();
		return gson.toJson(member);
	}
	
	@RequestMapping(value="submitStore.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String submitStore(HttpServletRequest req, RedirectAttributes red ) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String root_path = req.getSession().getServletContext().getRealPath("/");
		String attach_path = "resources/upload/";
		String path = root_path + attach_path;	
		
		Store store = SubmitStoreGetParameter(req);
		dao.submitStore(store);
		
		Long id = store.getId();
		
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
		
		Iterator<String> iter = mhsr.getFileNames();
		MultipartFile mfile = null;
		
		while (iter.hasNext()) {
			String fieldName = (String) iter.next();
			
			mfile = mhsr.getFile(fieldName);
			String origName;
			
			origName = mfile.getOriginalFilename();
			
			if (origName != null && !origName.equals("")) {
				String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
				String saveFileName = getRandomString() + ext;
	
				File serverFile = new File(path + "/" + saveFileName);
				try {
					mfile.transferTo(serverFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				dao.insertStoreImage(origName, saveFileName, id);
			}
		}
		
		Gson gson = new Gson();
		return gson.toJson(id);
	}
	
	@RequestMapping(value="storeDetail.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String storeDetail(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String id = req.getParameter("id");
		
		dao.updateStoreHit(Long.parseLong(id));
		Store store = dao.getStoreById(Long.parseLong(id));
		ArrayList<ImageFile> images = dao.getImageByStoreId(Long.parseLong(id));
		ArrayList<Review> reviews = dao.getReviewById(Long.parseLong(id));
		ArrayList<Member> members = new ArrayList<Member>();
		
		for (int i = 0; i < reviews.size(); i++) {
			Member member = dao.getMemberByReview(reviews.get(i).getMember_id());
			members.add(member);
		}
		
		StoreData storeData = new StoreData(store,images,reviews,members);
		
		Gson gson = new Gson();
		return gson.toJson(storeData);
	}
	
	@RequestMapping(value="getStoreGeneral.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getStoreGeneral(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String sigungu_str = req.getParameter("sigungu");
		String dog_size_str = req.getParameter("dog_size");
		String category_str = req.getParameter("category");
		
		ArrayList<Store> stores = dao.getStoreGeneral(Integer.parseInt(sigungu_str), Integer.parseInt(dog_size_str), Integer.parseInt(category_str));
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData storeData = new StoreData(stores.get(i), images,reviews);
			storeDatas.add(storeData);
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="getStoreGeneralWhenLogIn.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getStoreGeneralWhenLogIn(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String sigungu_str = req.getParameter("sigungu");
		String dog_size_str = req.getParameter("dog_size");
		String category_str = req.getParameter("category");
		String member_id = req.getParameter("member_id");
		
		ArrayList<Store> stores = dao.getStoreGeneral(Integer.parseInt(sigungu_str), Integer.parseInt(dog_size_str), Integer.parseInt(category_str));
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			Bookmark bookmarks = dao.getBookMarkList(stores.get(i).getId(), Long.parseLong(member_id));
			
			StoreData storedata = new StoreData(stores.get(i),images,reviews,null,bookmarks);
			storeDatas.add(storedata);
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="getStoreSpecial.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getStoreSpecial(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String sigungu = req.getParameter("sigungu");
		String dog_size = req.getParameter("dog_size");
		String category = req.getParameter("category");
		
		ArrayList<Store> stores = dao.getStoreSpecial(Integer.parseInt(sigungu),Integer.parseInt(dog_size),Integer.parseInt(category));
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData storeData = new StoreData(stores.get(i), images,reviews);
			storeDatas.add(storeData);
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="getStoreSpecialWhenLogIn.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getStoreSpecialWhenLogIn(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String sigungu_str = req.getParameter("sigungu");
		String dog_size_str = req.getParameter("dog_size");
		String category_str = req.getParameter("category");
		String member_id = req.getParameter("member_id");
		
		ArrayList<Store> stores = dao.getStoreSpecial(Integer.parseInt(sigungu_str),Integer.parseInt(dog_size_str),Integer.parseInt(category_str));
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			Bookmark bookmarks = dao.getBookMarkList(stores.get(i).getId(), Long.parseLong(member_id));
			
			StoreData storedata = new StoreData(stores.get(i),images,reviews,null,bookmarks);
			storeDatas.add(storedata);
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	
	@RequestMapping(value="getStoreForMap.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getAllmark(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		ArrayList<Store> stores = dao.getStoreForMap();
		
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData tmp = new StoreData(stores.get(i),images,reviews);
			storeDatas.add(tmp);
		}
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="getStoreByCategory.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getStoreByCategory(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String sigungu_str = req.getParameter("sigungu");
		String category_str = req.getParameter("category");
		
		Integer sigungu = Integer.parseInt(sigungu_str);
		Integer category = Integer.parseInt(category_str);
		
		ArrayList<Store> stores = new ArrayList<Store>();
		if (category < 100) {
			stores = dao.getStoreGeneral(sigungu, 1, category);
		} else {
			stores = dao.getStoreSpecial(sigungu, 1, category);
		}
		
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData tmp = new StoreData(stores.get(i),images,reviews);
			storeDatas.add(tmp);
		}
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="WriteReview.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String InsertReview(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String content = req.getParameter("content");
		String score = req.getParameter("score");
		String store_id = req.getParameter("store_id");
		String member_id = req.getParameter("member_id");
		String date = req.getParameter("date");
		
		dao.updateStoreScore(Long.parseLong(store_id), Double.parseDouble(score));
		dao.insertReview(Long.parseLong(store_id), Long.parseLong(member_id), content,date);
		
		// store score sum, score count update 해야되고, content review에 넣어야하고.
		
		return "";
	}
	
	@RequestMapping(value="GetStoreForSearch.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String GetStoreForSearch() {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		ArrayList<Store> stores = dao.getStoreForMap();
		
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData tmp = new StoreData(stores.get(i),images,reviews);
			storeDatas.add(tmp);
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="AddBookMark.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String AddBookMark(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String store_id = req.getParameter("store_id");
		String member_id = req.getParameter("member_id");
		
		dao.insertBookMark(Long.parseLong(store_id), Long.parseLong(member_id));
		
		return "";
	}
	
	@RequestMapping(value="DeleteBookMark.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String DeleteBookMark(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String store_id = req.getParameter("store_id");
		String member_id = req.getParameter("member_id");
		
		dao.deleteBookMark(Long.parseLong(store_id), Long.parseLong(member_id));
		
		return "";
	}
	
	@RequestMapping(value="getBookmarkStore.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBookmarkStore(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String member_id = req.getParameter("member_id");
		ArrayList<Bookmark> getMemberBookmark = dao.getBookmarkStoreId(Long.parseLong(member_id));
		
		ArrayList<Store> stores = new ArrayList<Store>();
		for (int i = 0; i < getMemberBookmark.size(); i++) {
			Store store = dao.getStoreById(getMemberBookmark.get(i).getStore_id());
			stores.add(store);
		}
		
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData tmp = new StoreData(stores.get(i),images,reviews);
			storeDatas.add(tmp);
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	@RequestMapping(value="getMyStore.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getMyStore(HttpServletRequest req) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String member_id = req.getParameter("member_id");
		
		ArrayList<Store> stores = dao.getMemberStore(Long.parseLong(member_id));
		
		ArrayList<StoreData> storeDatas = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++ ) {
			ArrayList<ImageFile> images = dao.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = dao.getReviewById(stores.get(i).getId());
			
			StoreData tmp = new StoreData(stores.get(i),images,reviews);
			storeDatas.add(tmp);
			
		}
		
		Gson gson = new Gson();
		return gson.toJson(storeDatas);
	}
	
	/************ function *************/
	public Store SubmitStoreGetParameter(HttpServletRequest req) {
		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String str_dog_size = req.getParameter("dog_size");
		String store_info = req.getParameter("store_information");
		String operation_day = req.getParameter("operation_day");
		String operation_time = req.getParameter("operation_time");
		String str_parking = req.getParameter("parking");
		String str_reservation = req.getParameter("reservation");
		String address = req.getParameter("address");
		String str_sigungu = req.getParameter("sigungu");
		String str_lat = req.getParameter("lat");
		String str_lng = req.getParameter("lng");
		String str_category = req.getParameter("category");
		String member_id = req.getParameter("member_id");
	
		Integer dog_size = Integer.parseInt(str_dog_size);
		Integer parking = Integer.parseInt(str_parking);
		Integer reservation = Integer.parseInt(str_reservation);
		Integer sigungu = Integer.parseInt(str_sigungu);
		double lat = Double.parseDouble(str_lat);
		double lng = Double.parseDouble(str_lng);
		Integer category = Integer.parseInt(str_category);
		long id = Long.parseLong(member_id);
		
		Store store = new Store(0l,name,contact,dog_size,store_info,operation_day,operation_time,parking,reservation,address,sigungu,lat,lng,category,0,0,0,id);
	
		return store;
	}
	
	public String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

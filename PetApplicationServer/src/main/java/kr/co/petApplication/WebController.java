package kr.co.petApplication;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import kr.co.petApplication.dao.Dao;
import kr.co.petApplication.dto.ImageFile;
import kr.co.petApplication.dto.Member;
import kr.co.petApplication.dto.Review;
import kr.co.petApplication.dto.Store;
import kr.co.petApplication.dto.StoreData;

/**
 * Handles requests for the application home page.
 */
@Controller
public class WebController {
	
	SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@RequestMapping("/")
	public String initial(HttpServletRequest request) {
		return "redirect:home.do";
	}
	@RequestMapping("home.do")
	public String home(HttpServletRequest request,Model model) {
		Dao DAO = sqlSession.getMapper(Dao.class);
	
		ArrayList<Store> stores = DAO.getStoreAll();
		ArrayList<StoreData> items = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = DAO.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = DAO.getReviewById(stores.get(i).getId());
			
			StoreData storeData = new StoreData(stores.get(i), images,reviews);
			items.add(storeData);
		}
		
		model.addAttribute("items",items);
		
		return "index";
	}
	@RequestMapping("shop.do")
	public String shop(HttpServletRequest request,Model model) {
		
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String id = request.getParameter("id");
		
		dao.updateStoreHit(Long.parseLong(id));//조회수 증가
		
		Store store = dao.getStoreById(Long.parseLong(id));
		ArrayList<ImageFile> images = dao.getImageByStoreId(Long.parseLong(id));
		ArrayList<Review> reviews = dao.getReviewById(Long.parseLong(id));
		ArrayList<Member> members = new ArrayList<Member>();
		
		for (int i = 0; i < reviews.size(); i++) {
			Member member = dao.getMemberByReview(reviews.get(i).getMember_id());
			members.add(member);
		}
		
		StoreData storeData = new StoreData(store,images,reviews,members);
		
		model.addAttribute("item",storeData);
		
		return "shop";
	}
	@RequestMapping("register.do")
	public String register(HttpServletRequest request) {
		return "register";
	}
	
	@RequestMapping("login_ok.do")
	public String loginOk(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String login_id = request.getParameter("login_id");
		String login_pwd = request.getParameter("login_pwd");
		
		Dao DAO = sqlSession.getMapper(Dao.class);
		
		Integer count = DAO.login(login_id, login_pwd);
		
		if (count > 0) {
			HttpSession httpSession = request.getSession();
			Member login_member = DAO.getMemberInfo(login_id, login_pwd);
			httpSession.setAttribute("login_member", login_member);
			
			return "redirect:home.do";
		} else {
			redirectAttributes.addAttribute("fail", "1");
			return "redirect:home.do";
		}		
	}
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("login_member");
		
		return "redirect:home.do";
	}
	
	//TODO: 회원가입
	@RequestMapping("join_ok.do")
	public String joinOk(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		Dao dao = sqlSession.getMapper(Dao.class);
		
		String login_id = request.getParameter("sign_up_id");
		String login_pw = request.getParameter("sign_up_pw");
		String name = request.getParameter("sign_up_name");
		
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
	
	@RequestMapping("search.do")
	public String search(HttpServletRequest request, Model model) {
		
		//
		String searchText = request.getParameter("searchText");
		String location = request.getParameter("selectLocation");
		String size = request.getParameter("selectSize");
		String place = request.getParameter("selectPlace");
		
		Dao DAO = sqlSession.getMapper(Dao.class);
		ArrayList<Store> stores = DAO.getStoreFromAttr();
		ArrayList<StoreData> items = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = DAO.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = DAO.getReviewById(stores.get(i).getId());
			
			StoreData tmpData = new StoreData(stores.get(i), images, reviews);
			items.add(tmpData);
		}
		
		model.addAttribute("items",items);
		
		// home.do 에 있던 것 
		/* Dao DAO = sqlSession.getMapper(Dao.class);
		
		ArrayList<Store> stores = DAO.getStoreAll();
		ArrayList<StoreData> items = new ArrayList<StoreData>();
		
		for (int i = 0; i < stores.size(); i++) {
			ArrayList<ImageFile> images = DAO.getImageByStoreId(stores.get(i).getId());
			ArrayList<Review> reviews = DAO.getReviewById(stores.get(i).getId());
			
			StoreData storeData = new StoreData(stores.get(i), images,reviews);
			items.add(storeData);
		}
		*/
		//model.addAttribute("items",items);
		model.addAttribute("search",1);
		
		return "index";
	}
	
}

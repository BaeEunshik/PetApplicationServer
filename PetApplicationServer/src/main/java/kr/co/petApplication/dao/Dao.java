package kr.co.petApplication.dao;

import java.util.ArrayList;

import kr.co.petApplication.dto.Bookmark;
import kr.co.petApplication.dto.ImageFile;
import kr.co.petApplication.dto.Mark;
import kr.co.petApplication.dto.Member;
import kr.co.petApplication.dto.Review;
import kr.co.petApplication.dto.Store;
import kr.co.petApplication.dto.StoreImage;

public interface Dao {
	public void insertMember(String login_id, String login_pw, String name);
	public Integer isAlreadyJoined(String login_id);
	public Member isIdPwRight(String login_id, String login_pw);
	public void submitStore(Store store);
	public Store getStoreById(long store_id);
	public ArrayList<Store> getMemberStore(long member_id);
	public ArrayList<ImageFile> getImageByStoreId(long store_id);
	public ArrayList<Store> getStoreForMap();
	public ArrayList<ImageFile> getStoreImage();
	public ArrayList<Store> getStoreGeneral(Integer sigungu, Integer dog_size, Integer category);
	public ArrayList<Store> getStoreSpecial(Integer sigungu, Integer dog_size, Integer category);
	public void insertStoreImage(String originName, String savedName, Long store_id);
	public void updateStoreHit(long store_id);
	public void updateStoreScore(long store_id, double score);
	public void insertReview(long store_id, long member_id, String content, String date);
	public ArrayList<Review> getReviewById(long store_id);
	public Member getMemberByReview(long member_id);
	public void insertBookMark(long store_id, long member_id);
	public void deleteBookMark(long store_id, long member_id);
	public Bookmark getBookMarkList(long store_id, long member_id);
	public ArrayList<Bookmark> getBookmarkStoreId(long member_id);
}

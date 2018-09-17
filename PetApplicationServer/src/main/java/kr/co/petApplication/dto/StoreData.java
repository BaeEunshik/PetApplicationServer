package kr.co.petApplication.dto;

import java.util.ArrayList;

public class StoreData {
	private Store store;
	private ArrayList<ImageFile> images;
	private ArrayList<Review> reviews;
	private ArrayList<Member> members;
	private Bookmark bookmarks;
	
	public StoreData() {
		
	}
	
	public StoreData(Store store, ArrayList<ImageFile> images, ArrayList<Review> reviews, ArrayList<Member> members,
			Bookmark bookmarks) {
		super();
		this.store = store;
		this.images = images;
		this.reviews = reviews;
		this.members = members;
		this.bookmarks = bookmarks;
	}

	public StoreData(Store store, ArrayList<ImageFile> images, ArrayList<Review> reviews, ArrayList<Member> members) {
		super();
		this.store = store;
		this.images = images;
		this.reviews = reviews;
		this.members = members;
	}

	public StoreData(Store store, ArrayList<ImageFile> images, ArrayList<Review> reviews) {
		super();
		this.store = store;
		this.images = images;
		this.reviews = reviews;
	}
	
	
	
	public Bookmark getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Bookmark bookmarks) {
		this.bookmarks = bookmarks;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<Member> member) {
		this.members = member;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public ArrayList<ImageFile> getImages() {
		return images;
	}
	public void setImages(ArrayList<ImageFile> images) {
		this.images = images;
	}
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	
}

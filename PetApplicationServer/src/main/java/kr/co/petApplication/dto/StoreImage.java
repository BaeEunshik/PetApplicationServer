package kr.co.petApplication.dto;

import java.util.ArrayList;

public class StoreImage {
	private Store store;
	private ArrayList<ImageFile> image;
	
	public StoreImage () {
		
	}

	public StoreImage(Store store, ArrayList<ImageFile> image) {
		super();
		this.store = store;
		this.image = image;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public ArrayList<ImageFile> getImage() {
		return image;
	}

	public void setImage(ArrayList<ImageFile> image) {
		this.image = image;
	}
	
}

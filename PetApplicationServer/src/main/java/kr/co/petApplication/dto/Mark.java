package kr.co.petApplication.dto;

public class Mark {
	private long id;
	private long store_id;
	private double lat;
	private double lng;
	
	public Mark() {
		
	}
	
	public Mark(long id, long store_id, double lat, double lng) {
		super();
		this.id = id;
		this.store_id = store_id;
		this.lat = lat;
		this.lng = lng;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStore_id() {
		return store_id;
	}
	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
}

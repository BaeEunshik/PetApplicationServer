package kr.co.petApplication.dto;

public class Store {
	private long id;
    private String name;
    private String contact;
    private Integer dog_size;
    private String store_information;
    private String operation_day;
    private String operation_time;
    private Integer parking;
    private Integer reservation;
    private String address;
    private Integer sigungu;
    private double latitude;
    private double longitude;
    private Integer category;
    private Integer hit;
    private Integer score_sum;
    private Integer score_count;
    private long member_id;
    
    public Store() {
    	
    }
    
	public Store(long id, String name, String contact, Integer dog_size, String store_information, String operation_day,
			String operation_time, Integer parking, Integer reservation, String address, Integer sigungu,
			double latitude, double longitude, Integer category, Integer hit, Integer score_sum, Integer score_count,
			long member_id) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.dog_size = dog_size;
		this.store_information = store_information;
		this.operation_day = operation_day;
		this.operation_time = operation_time;
		this.parking = parking;
		this.reservation = reservation;
		this.address = address;
		this.sigungu = sigungu;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.hit = hit;
		this.score_sum = score_sum;
		this.score_count = score_count;
		this.member_id = member_id;
	}

	public Store(long id, String name, String contact, Integer dog_size, String store_information, String operation_day,
			String operation_time, Integer parking, Integer reservation, String address, Integer sigungu,
			double latitude, double longitude, Integer category, Integer hit, Integer score_sum, Integer score_count) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.dog_size = dog_size;
		this.store_information = store_information;
		this.operation_day = operation_day;
		this.operation_time = operation_time;
		this.parking = parking;
		this.reservation = reservation;
		this.address = address;
		this.sigungu = sigungu;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.hit = hit;
		this.score_sum = score_sum;
		this.score_count = score_count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getDog_size() {
		return dog_size;
	}

	public void setDog_size(Integer dog_size) {
		this.dog_size = dog_size;
	}

	public String getStore_information() {
		return store_information;
	}

	public void setStore_information(String store_information) {
		this.store_information = store_information;
	}

	public String getOperation_day() {
		return operation_day;
	}

	public void setOperation_day(String operation_day) {
		this.operation_day = operation_day;
	}

	public String getOperation_time() {
		return operation_time;
	}

	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}

	public Integer getParking() {
		return parking;
	}

	public void setParking(Integer parking) {
		this.parking = parking;
	}

	public Integer getReservation() {
		return reservation;
	}

	public void setReservation(Integer reservation) {
		this.reservation = reservation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSigungu() {
		return sigungu;
	}

	public void setSigungu(Integer sigungu) {
		this.sigungu = sigungu;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getScore_sum() {
		return score_sum;
	}

	public void setScore_sum(Integer score_sum) {
		this.score_sum = score_sum;
	}

	public Integer getScore_count() {
		return score_count;
	}

	public void setScore_count(Integer score_count) {
		this.score_count = score_count;
	}

    
    
}

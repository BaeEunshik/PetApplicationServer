package kr.co.petApplication.dto;

public class Review {
	private long id;
	private long store_id;
	private long member_id;
	private String content;
	private String day;
	
	public Review() {
		
	}
	
	public Review(long id, long store_id, long member_id, String content, String day) {
		super();
		this.id = id;
		this.store_id = store_id;
		this.member_id = member_id;
		this.content = content;
		this.day = day;
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
	public long getMember_id() {
		return member_id;
	}
	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String date) {
		this.day = date;
	}
	
	
}

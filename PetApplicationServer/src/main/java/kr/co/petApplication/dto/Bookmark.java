package kr.co.petApplication.dto;

public class Bookmark {
	private long id;
    private long store_id;
    private long member_id;
    
    public Bookmark() {
    	
    }
    
	public Bookmark(long id, long store_id, long member_id) {
		super();
		this.id = id;
		this.store_id = store_id;
		this.member_id = member_id;
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
    
    
    
}

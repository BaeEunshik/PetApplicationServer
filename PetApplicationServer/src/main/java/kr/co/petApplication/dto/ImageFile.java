package kr.co.petApplication.dto;

public class ImageFile {
	private Long id;
	private String originName;
	private String savedName;
	private Long store_id;
	
	public ImageFile() {
		
	}
	
	public ImageFile(Long id, String originName, String savedName, Long store_id) {
		super();
		this.id = id;
		this.originName = originName;
		this.savedName = savedName;
		this.store_id = store_id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getSavedName() {
		return savedName;
	}
	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}
	public Long getStore_id() {
		return store_id;
	}
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	
}

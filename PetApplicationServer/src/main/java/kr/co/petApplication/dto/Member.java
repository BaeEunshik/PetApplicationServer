package kr.co.petApplication.dto;

public class Member {
	private long id;
	private String login_id;
	private String login_pw;
	private String name;
	
	public Member() {
		
	}
	
	public Member(long id, String login_id, String login_pw, String name) {
		super();
		this.id = id;
		this.login_id = login_id;
		this.login_pw = login_pw;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_pw() {
		return login_pw;
	}
	public void setLogin_pw(String login_pw) {
		this.login_pw = login_pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

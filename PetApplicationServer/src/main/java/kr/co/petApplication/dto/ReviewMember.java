package kr.co.petApplication.dto;

import java.util.ArrayList;

public class ReviewMember {
	private Review reviews;
	private Member members;
	
	public ReviewMember() {
		
	}
	
	public ReviewMember(Review reviews, Member members) {
		super();
		this.reviews = reviews;
		this.members = members;
	}
	public Review getReviews() {
		return reviews;
	}
	public void setReviews(Review reviews) {
		this.reviews = reviews;
	}
	public Member getMembers() {
		return members;
	}
	public void setMembers(Member members) {
		this.members = members;
	}
}

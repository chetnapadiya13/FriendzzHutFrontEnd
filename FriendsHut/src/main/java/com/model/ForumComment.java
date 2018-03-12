package com.model;

public class ForumComment{
	
	private String blogId;
	private String blogName;
	private String blogContent;
	private String createdDate;
	private String userName;
	public  String getBlogId() {
		return blogId;
	}
	public  void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public  String getBlogName() {
		return blogName;
	}
	public  void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public  String getBlogContent() {
		return blogContent;
	}
	public  void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public  String getCreatedDate() {
		return createdDate;
	}
	public  void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public  String getUserName() {
		return userName;
	}
	public  void setUserName(String userName) {
		this.userName = userName;
	}
	
}

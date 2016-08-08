package com.yimeicloud.study.javamail.entity;

public class Mail {
	// 发件人邮箱
	private String sender = "mrspring@126.com";
	// 发件人邮箱的用户名
	private String username = "ming";
	// 发件人邮箱的密码
	private String password = "909090";
	// 发送邮件的服务器地址
	private String host = "smtp.126.com";
	// 协议
	private String protocol = "smtp";
	// 主题
	private String subject = "邮件主题";
	// 内容
	private String content = "邮件内容";
	// 收件人
	private String receiver = "407201244@qq.com";
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}

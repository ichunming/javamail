package com.yimeicloud.study.javamail.sender;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.yimeicloud.study.javamail.entity.Mail;

public class Sendmail implements Runnable {

	// mail
	private Mail mail;
	
	public Sendmail() {
	}

	public Sendmail(Mail mail) {
		this.mail = mail;
	}
	
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.host", mail.getHost());
			prop.setProperty("mail.transport.protocol", mail.getProtocol());
			prop.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			System.out.println("尝试连接邮件服务器...");
			ts.connect(mail.getHost(), mail.getSender(), mail.getPassword());
			Message message = createEmail(session);
			System.out.println("邮件发送开始");
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
			System.out.println("邮件发送成功");
		} catch (Exception e) {
			System.out.println("邮件发送失败");
			throw new RuntimeException(e);
		}
	}
	
	private Message createEmail(Session session) throws Exception{
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mail.getSender()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiver()));
		
		// 主题
		message.setSubject(mail.getSubject());
		// 内容
		message.setContent(mail.getContent(), "text/html;charset=UTF-8");
		
		message.saveChanges();
		return message;
	}
}

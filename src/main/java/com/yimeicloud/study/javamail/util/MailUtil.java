package com.yimeicloud.study.javamail.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.yimeicloud.study.javamail.entity.Mail;

public class MailUtil {
	
	/**
	 * 发送邮件
	 * @param subject
	 * @param content
	 * @param receiver
	 * @return
	 */
	public static boolean send(String subject, String content, String receiver) {
		return sendWithAttach(subject, content, receiver, null);
	}
	
	/**
	 * 发送带有附件的邮件
	 * @param subject
	 * @param content
	 * @param receiver
	 * @param filenames
	 * @return
	 */
	public static boolean sendWithAttach(String subject, String content, String receiver, String[] filenames) {
		try {
			Mail mail = createMail(subject, content, receiver);
			Properties prop = new Properties();
			prop.setProperty("mail.host", mail.getHost());
			prop.setProperty("mail.transport.protocol", mail.getProtocol());
			prop.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			System.out.println("尝试连接邮件服务器...");
			ts.connect(mail.getHost(), mail.getSender(), mail.getPassword());
			Message message = createMessage(session, mail, filenames);
			System.out.println("邮件发送开始");
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
			System.out.println("邮件发送成功");
			return true;
		} catch (Exception e) {
			System.out.println("邮件发送失败");
			return false;
		}
	}
	
	/**
	 * 创建Mail对象
	 * @param subject
	 * @param content
	 * @param receiver
	 * @return
	 */
	private static Mail createMail(String subject, String content, String receiver) {
		return new Mail(subject, content, receiver);
	}
	
	/**
	 * 创建Message对象
	 * @param session
	 * @param mail
	 * @param filenames
	 * @return
	 * @throws Exception
	 */
	private static Message createMessage(Session session, Mail mail, String[] filenames) throws Exception{
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mail.getSender()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiver()));
		
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp = new MimeBodyPart();
		
		// subject
		message.setSubject(mail.getSubject());
		// content
		mbp.setContent(mail.getContent(), "text/html;charset=UTF-8");
        mp.addBodyPart(mbp);
        message.setContent(mp);
        
        // attachment
        if(null != filenames && filenames.length > 0) {
        	for(String filename : filenames) {
        		mbp = new MimeBodyPart();
                FileDataSource fds=new FileDataSource(filename); //得到数据源
                mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
                mbp.setFileName(fds.getName());  //得到文件名同样至入BodyPart  
                mp.addBodyPart(mbp);
                message.setContent(mp); //Multipart加入到信件
        	}
        }
        
		message.saveChanges();
		return message;
	}
}

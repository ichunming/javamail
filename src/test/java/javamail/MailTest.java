package javamail;

import com.yimeicloud.study.javamail.entity.Mail;
import com.yimeicloud.study.javamail.sender.Sendmail;

public class MailTest {
	public static void main(String[] args) {
		Sendmail sendmail = new Sendmail(new Mail());
		new Thread(sendmail).start();
	}
}

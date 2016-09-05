package javamail;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.yimeicloud.study.javamail.util.MailUtil;

public class MailTest {
	
	@Test
	public void test() {
		// send simple email
		//boolean result = MailUtil.send("Subject", "Content", "***@qq.com");
		
		// send email with attachment
		boolean result = MailUtil.send("Subject", "Content", "***@qq.com", new String[] {"test.txt"});
		assertTrue(result);
	}
}
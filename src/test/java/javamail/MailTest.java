package javamail;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.yimeicloud.study.javamail.util.MailUtil;

public class MailTest {
	
	@Test
	public void test() {
		boolean result = MailUtil.sendWithAttach("Subject", "Content", "***@qq.com", new String[] {"test.txt"});
		assertTrue(result);
	}
}
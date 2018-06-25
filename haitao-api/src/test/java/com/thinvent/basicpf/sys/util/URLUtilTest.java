package com.thinvent.basicpf.sys.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class URLUtilTest {
	
	@InjectMocks
	private URLUtil urlUtil;
	
	@Test
	public void getUrl() {
		URLUtil.getUrl();
	}
}

package com.thinvent.basicpf.sys.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.thinvent.basicpf.util.ThinventBaseExceptionHandler;
import com.thinvent.library.exception.ThinventBaseException;

public class ThinventBaseExceptionHandlerTest {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ThinventBaseExceptionHandler tbe = new ThinventBaseExceptionHandler();

	@Before
	public void setUp(){
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void userErrorHandler() throws Exception{
		tbe.userErrorHandler(request, new ThinventBaseException(404, "测试"), response);
	}

}

package com.thinvent.basicpf;

import org.junit.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.thinvent.basicpf.util.InterceptorConfig;

public class InterceptorConfigTest {

	@Test
	public void testAddInterceptors() {
		InterceptorConfig interceptorConfig = new InterceptorConfig();
		InterceptorRegistry InterceptorRegistry = new InterceptorRegistry();
		interceptorConfig.addInterceptors(InterceptorRegistry);
	}
}
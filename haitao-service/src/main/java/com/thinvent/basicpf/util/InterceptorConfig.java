package com.thinvent.basicpf.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.util.JWTConfig;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j;

@Configuration
@Log4j
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/error")
				.excludePathPatterns("/v2/**").excludePathPatterns("/swagger*/**");
		super.addInterceptors(registry);
	}

	private class Interceptor implements HandlerInterceptor {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			request.getHeader(Constants.TOKEN_KEY);
			// 判断是否携带token
			return true;
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			// Do nothing because of X and Y.
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
			// Do nothing because of X and Y.
		}
	}
}

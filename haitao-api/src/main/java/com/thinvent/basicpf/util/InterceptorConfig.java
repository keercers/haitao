package com.thinvent.basicpf.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.thinvent.basicpf.sys.adapt.IUserAdapt;
import com.thinvent.basicpf.sys.handler.impl.LogHandlerImpl;
import com.thinvent.library.Constants;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.util.CryptographyUtil;
import com.thinvent.library.util.JWTConfig;
import com.thinvent.library.util.LogUtil;
import com.thinvent.library.util.UUIDUtil;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private LogHandlerImpl logHandlerImpl;
	
	@Autowired
	private IUserAdapt userAdapt;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/**/*export*").excludePathPatterns("/file/upload*")
				.excludePathPatterns("/**/*import*").excludePathPatterns("/error").excludePathPatterns("/v2/**").excludePathPatterns("/swagger*/**");
		super.addInterceptors(registry);
	}

	private class Interceptor implements HandlerInterceptor {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			String tokenIn = request.getHeader(Constants.TOKEN_KEY);
			// 判断是否携带token
			if ("login".equals(LogUtil.stringIntercept(request.getRequestURI()))) {
				try {
					//保存登录日志
					if (CryptographyUtil.md5(request.getParameter("password")).equals(userAdapt.findUser(request.getParameter("loginName")).getPassword())) {
						logHandlerImpl.logSaveHandle(request, response, handler, userAdapt.findUser(request.getParameter("loginName")).getUserId());
					}
				} catch (Exception e) {
					log.info(e.getMessage(), e);
				}
				return true;
			}
			if (null != tokenIn && !"".equals(tokenIn)) {
				try {
					//保存日志
					logHandlerImpl.logSaveHandle(request, response, handler, JWTConfig.getUserByToken(tokenIn).getUserId());
				} catch (Exception e) {
					log.info(e.getMessage(), e);
				}
				try {
					// 验证token合法性
					Claims claims = JWTConfig.parseJWT(tokenIn);
					// token续约
					if ((claims.getExpiration().getTime() - System.currentTimeMillis()) < 1000 * 60 * 10) {
						String subject = claims.getSubject();
						String jwtUUID = UUIDUtil.getUuid();
						String tokenOut = JWTConfig.createJWT(jwtUUID, subject, Constants.JWT_TTL);
						response.addHeader(Constants.TOKEN_KEY, tokenOut);
					}
					return true;
				} catch (Exception e) {
					// token不合法
					log.info(e.getMessage(), e);
					response.sendError(TvtExceptionEnum.DATA_VER_RELOAD_ERROR.getIndex(),
							TvtExceptionEnum.DATA_VER_RELOAD_ERROR.getName());
				}
			}
			return false;
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

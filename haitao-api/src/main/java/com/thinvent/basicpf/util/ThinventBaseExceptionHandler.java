package com.thinvent.basicpf.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinvent.library.exception.ThinventBaseException;

import lombok.extern.slf4j.Slf4j;

@RestController
@ControllerAdvice
@Slf4j
public class ThinventBaseExceptionHandler {
	
	@ExceptionHandler(value = ThinventBaseException.class)
	@ResponseBody
	public void userErrorHandler(HttpServletRequest req, Exception e, HttpServletResponse res) throws IOException{
		log.error("error:"+e);
		ThinventBaseException baseEx = (ThinventBaseException)e;
		res.sendError(baseEx.getCode(), baseEx.getMsg());
	}

}

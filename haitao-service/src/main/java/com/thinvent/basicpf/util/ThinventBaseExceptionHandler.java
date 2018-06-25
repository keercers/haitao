package com.thinvent.basicpf.util;

import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.ThinventBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
@Slf4j
public class ThinventBaseExceptionHandler {

    @SuppressWarnings("deprecation")
	@ExceptionHandler(value = ThinventBaseException.class)
    @ResponseBody
    public void userErrorHandler(HttpServletRequest req, Exception e, HttpServletResponse res) throws IOException {
        log.info("error:" + e);
        ThinventBaseException baseEx = (ThinventBaseException) e;
        //res.sendError(baseEx.getCode(), baseEx.getMsg());
        res.setStatus(baseEx.getCode(), baseEx.getMsg());
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(baseEx.getMsg());
    }

    @SuppressWarnings("deprecation")
	@ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    public void daoErrorHandler(DataAccessException daoEx, HttpServletResponse res) throws IOException {
        log.info("error:" + daoEx);
        res.setStatus(TvtExceptionEnum.DATA_ACCESS_FAILURE.getIndex(), TvtExceptionEnum.DATA_ACCESS_FAILURE.getName());
        res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(TvtExceptionEnum.DATA_ACCESS_FAILURE.getName());
    }

}

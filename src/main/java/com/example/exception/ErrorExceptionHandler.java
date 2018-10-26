package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理(拦截)
 * ErrorExceptionHandler
 * 创建人:何雪平 
 * 时间：2017年1月7日-下午3:56:52 
 * @version 1.0.0
 *
 */
//此时是ControllerAdvice
@ControllerAdvice
public class ErrorExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);

	/**
	 * 运行时异常
	 * com.xkh.exception 
	 * 方法名：processException
	 * 创建人：何雪平 
	 * 时间：2017年1月7日-下午3:56:45 
	 * @param exception
	 * @return ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */
	@ExceptionHandler(value ={ RuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(RuntimeException exception) {
		ModelAndView m = new ModelAndView();
		m.addObject("msgException", exception.getMessage());
		m.setViewName("error/500");
		logger.error(exception.getMessage()+"=================");
		exception.printStackTrace();//最好加上这个
		return m;
	}


	/**
	 * 文件上传异常
	 * com.example.exception 
	 * 方法名：multipartException
	 * 创建人：yzk 
	 * 时间：2018年9月29日-下午5:37:38 
	 * @param exception
	 * @return ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */
	@ExceptionHandler(value ={ MultipartException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView multipartException(MultipartException exception) {
		ModelAndView m = new ModelAndView();
		m.addObject("msgException", exception.getMessage());
		m.setViewName("error/500");
		logger.error(exception.getMessage()+"=================");
		exception.printStackTrace();//最好加上这个
		return m;
	}
	
	
	// 这种处理方法，会造成对js，css等资源的过滤，不推荐
	/*
	 * @ExceptionHandler(value = NoHandlerFoundException.class) public
	 * ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)
	 * throws Exception { ModelAndView mav = new ModelAndView();
	 * mav.addObject("exception", e); mav.addObject("url", req.getRequestURL());
	 * mav.setViewName("error/404"); return mav; }
	 */
}

package com.example.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aliyun.mns.common.ServiceException;
import com.example.common.ResponseEnum;
import com.example.common.Result;



/**
 * 全局异常处理(拦截)
 * GlobalExceptionHandler
 * 创建人:何雪平 
 * 时间：2017年7月25日-上午11:37:11 
 * @version 1.0.0
 *
 */
//此时是RestControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 违反约束异常
	 * com.expert.exception 
	 * 方法名：constraintViolationException
	 * 创建人：何雪平 
	 * 时间：2017年8月25日-下午5:13:12 
	 * @param ex
	 * @return Result
	 * @exception 
	 * @since  1.0.0
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result<Object> constraintViolationException(ConstraintViolationException ex) {
		Result<Object> result = new Result<>();
		result.setStatus(false);
		result.setDesc(ResponseEnum.FAIL_CONSTRAINT_ERROR.desc()+ex.getMessage());
		result.setResult(ResponseEnum.FAIL_CONSTRAINT_ERROR.result());
		logger.error(ex.getMessage());
		ex.printStackTrace();//最好加上这个
		return result;
	}

	/**
	 * 非法参数异常
	 * com.expert.exception 
	 * 方法名：IllegalArgumentException
	 * 创建人：何雪平 
	 * 时间：2017年8月25日-下午5:13:22 
	 * @param ex
	 * @return Result
	 * @exception 
	 * @since  1.0.0
	 */
	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result<Object> IllegalArgumentException(IllegalArgumentException ex) {
		Result<Object> result = new Result<>();
		result.setStatus(false);
		result.setDesc(ResponseEnum.FAIL_ARGUMENT_ERROR.desc()+ex.getMessage());
		result.setResult(ResponseEnum.FAIL_ARGUMENT_ERROR.result());
		logger.error(ex.getMessage());
		ex.printStackTrace();//最好加上这个
		return result;
	}

	/**
	 * 映射处理器异常
	 * com.expert.exception 
	 * 方法名：noHandlerFoundException
	 * 创建人：何雪平 
	 * 时间：2017年8月25日-下午5:13:42 
	 * @param ex
	 * @return Result
	 * @exception 
	 * @since  1.0.0
	 */
	@ExceptionHandler(value = { NoHandlerFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Result<Object> noHandlerFoundException(NoHandlerFoundException ex) {
		Result<Object> result = new Result<>();
		result.setStatus(false);
		result.setDesc(ResponseEnum.FAIL_INVALID_ERROR.desc()+ex.getMessage());
		result.setResult(ResponseEnum.FAIL_INVALID_ERROR.result());
		logger.error(ex.getMessage());
		ex.printStackTrace();//最好加上这个
		return result;
	}

	/**
	 * 通用异常
	 * com.expert.exception 
	 * 方法名：unknownException
	 * 创建人：何雪平 
	 * 时间：2017年8月25日-下午5:13:56 
	 * @param ex
	 * @return Result
	 * @exception 
	 * @since  1.0.0
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<Object> unknownException(Exception ex) {
		Result<Object> result = new Result<>();
		result.setStatus(false);
		result.setDesc(ResponseEnum.FAIL_SYS_ERROR.desc()+ex.getMessage());
		result.setResult(ResponseEnum.FAIL_SYS_ERROR.result());
		logger.error(ex.getMessage());
		ex.printStackTrace();//最好加上这个
		return result;
	}

	  /**
	   * 业务逻辑异常
	   * com.expert.exception 
	   * 方法名：handleServiceException
	   * 创建人：何雪平 
	   * 时间：2017年8月25日-下午5:14:05 
	   * @param ex
	   * @return Result
	   * @exception 
	   * @since  1.0.0
	   */
	  @ExceptionHandler(ServiceException.class)
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  public Result<Object> handleServiceException(ServiceException ex) {
		  Result<Object> result = new Result<>();
		    result.setStatus(false);
			result.setDesc(ResponseEnum.FAIL_SERVER_ERROR.desc()+ex.getMessage());
			result.setResult(ResponseEnum.FAIL_SERVER_ERROR.result());
			logger.error(ex.getMessage());
			ex.printStackTrace();//最好加上这个
			return result;
	  }

	  /**
	   * 操作数据库出现异常:名称重复，外键关联
	   * com.expert.exception 
	   * 方法名：handleException
	   * 创建人：何雪平 
	   * 时间：2017年8月25日-下午5:14:15 
	   * @param ex
	   * @return Result
	   * @exception 
	   * @since  1.0.0
	   */
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  public Result<Object> handleException(DataIntegrityViolationException ex) {
		  Result<Object> result = new Result<>();
		    result.setStatus(false);
			result.setDesc(ResponseEnum.FAIL_DATABASE_ERROR.desc()+ex.getMessage());
			result.setResult(ResponseEnum.FAIL_DATABASE_ERROR.result());
			logger.error(ex.getMessage());
			ex.printStackTrace();//最好加上这个
			return result;
	  }


}

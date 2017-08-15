/**
 * 
 */
package com.yogu.core.server.exception.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Priority;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * validation框架校验错误抛出的异常 处理 <br>
 * 
 * JFan 2014年12月10日 下午4:05:46
 */
@Provider
@Priority(Priorities.USER)
public class ConstraintViolationExMapper implements ExceptionMapper<ConstraintViolationException> {

	private static final Logger logger = LoggerFactory.getLogger(ConstraintViolationExMapper.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(final ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

//		StringBuilder msg = new StringBuilder();
//		boolean next = false;
//		for (ConstraintViolation<?> cv : constraintViolations) {
//			if (next)
//				msg.append(" & ");
//			msg.append(cv.getMessage());
//			next = true;  
//		}

		String msg = "";
		String property = "";
		if (CollectionUtils.isNotEmpty(constraintViolations)) {
			List<ConstraintViolation<?>> cvs = new ArrayList<>(constraintViolations);
			Collections.sort(cvs, new CVComparator());

			for (ConstraintViolation<?> cv : cvs) {
				Path path = cv.getPropertyPath();
				// 最后一个节点（当前）
				if (null != path)
					for (Iterator<Node> iterator = path.iterator(); iterator.hasNext();) {
						Node node = iterator.next();
						if (null != node)
							property = node.getName();
					}
				msg = cv.getMessage();
				break;
			}
		}

		logger.error("validator#constraint | validator校验失败 | property: {}, msg: {}", property, msg);
		RestResult<String> entity = new RestResult<String>(ResultCode.PARAMETER_ERROR, msg);
		return Response.ok().entity(entity).type("application/json;charset=UTF-8").build();
	}

	// ####

	/**
	 * 对 ConstraintViolation<?>异常列表 进行排序
	 */
	private class CVComparator implements Comparator<ConstraintViolation<?>> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compare(ConstraintViolation<?> o1, ConstraintViolation<?> o2) {
			String msg1 = (null != o1 ? o1.getMessage() : "");
			String msg2 = (null != o2 ? o2.getMessage() : "");
			// msg 还是有可能为null
			if (null == msg1)
				msg1 = "";
			if (null == msg2)
				msg2 = "";
			// 字符串比较
			return msg1.compareTo(msg2);
		}

	}

}

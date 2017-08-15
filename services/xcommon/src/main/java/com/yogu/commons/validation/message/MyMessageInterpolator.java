package com.yogu.commons.validation.message;
///**
// * 
// */
//package com.vip.xapi.validation.message;
//
//import java.util.Locale;
//
//import javax.validation.MessageInterpolator;
//
///**
// * <br>
// * 
// * JFan 2014年12月10日 下午5:47:18
// */
//public class MyMessageInterpolator implements MessageInterpolator {
//
//	private MessageInterpolator defaultMessageInter;
//	
//
//	public MyMessageInterpolator(MessageInterpolator defaultMessageInter) {
//		this.defaultMessageInter = defaultMessageInter;
//	}
//
//	/*
//	 * （非 Javadoc）
//	 * 
//	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String, javax.validation.MessageInterpolator.Context)
//	 */
//	@Override
//	public String interpolate(String messageTemplate, Context context) {
//		return null;
//	}
//
//	/*
//	 * （非 Javadoc）
//	 * 
//	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String, javax.validation.MessageInterpolator.Context, java.util.Locale)
//	 */
//	@Override
//	public String interpolate(String messageTemplate, Context context, Locale locale) {
//		return null;
//	}
//	
//	public void setValidationMessageSource(MessageSource messageSource) {
//	    this.messageInterpolator = HibernateValidatorDelegate.buildMessageInterpolator(messageSource);
//	   }
//	
//	/**
//	 * Inner class to avoid a hard-coded Hibernate Validator 4.1+ dependency.
//	 */
//	private static class HibernateValidatorDelegate {
//
//		public static MessageInterpolator buildMessageInterpolator(MessageSource messageSource) {
//			return new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource));
//		}
//	}
//
//
//}

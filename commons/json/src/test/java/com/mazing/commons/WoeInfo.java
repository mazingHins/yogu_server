/**
 * 
 */
package com.mazing.commons;


/**
 * <br>
 * 
 * JFan 2014年12月11日 下午5:45:47
 */
public class WoeInfo {//implements JsonValue {

	private int parentid;
	private String name;
	private String url;
	private long woeid;
	private WoeType placeType;

//	/*
//	 * （非 Javadoc）
//	 * 
//	 * @see javax.json.JsonValue#getValueType()
//	 */
//	@Override
//	public ValueType getValueType() {
//		return ValueType.OBJECT;
//	}

	/**
	 * @return parentid
	 */
	public int getParentid() {
		return parentid;
	}

	/**
	 * @param parentid 要设置的 parentid
	 */
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url 要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return woeid
	 */
	public long getWoeid() {
		return woeid;
	}

	/**
	 * @param woeid 要设置的 woeid
	 */
	public void setWoeid(long woeid) {
		this.woeid = woeid;
	}

	/**
	 * @return placeType
	 */
	public WoeType getPlaceType() {
		return placeType;
	}

	/**
	 * @param placeType 要设置的 placeType
	 */
	public void setPlaceType(WoeType placeType) {
		this.placeType = placeType;
	}

}

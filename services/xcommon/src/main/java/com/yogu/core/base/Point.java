package com.yogu.core.base;

/**
 * 记录坐标位置（经纬度） <br>
 *
 * @author JFan 2015年8月5日 上午10:35:17
 */
public class Point {

	/** 经度 */
	private double lng;
	/** 纬度 */
	private double lat;

	// public Point() {
	// }

	public Point(double lng, double lat) {
		this.lng = lng;
		this.lat = lat;
	}

	/**
	 * @return lng
	 */
	public double getLng() {
		return lng;
	}

//	/**
//	 * @param lng 要设置的 lng
//	 */
//	public void setLng(double lng) {
//		this.lng = lng;
//	}

	/**
	 * @return lat
	 */
	public double getLat() {
		return lat;
	}

//	/**
//	 * @param lat 要设置的 lat
//	 */
//	public void setLat(double lat) {
//		this.lat = lat;
//	}

	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + lng + ", " + lat + "]";
	}

}

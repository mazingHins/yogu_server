/**
 * 
 */
package com.yogu.core.utils;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.yogu.commons.utils.Args;
import com.yogu.core.base.Point;

/**
 * 坐标 相关运算 <br>
 * 
 * <br>
 * JFan - 2015年6月8日 上午10:11:50
 */
public final class PointUtils {

	/**
	 * 方法2：根据经纬度，获取两点间的距离，单位：米<br>
	 * 这个方法误差稍大，但性能是 distanceInMeters() 的3倍左右。
	 */
	public static int distanceByLngLat(Point point1, Point point2) {
		Args.notNull(point1, "point1");
		Args.notNull(point2, "point2");
		return distanceByLngLat(point1.getLng(), point1.getLat(), point2.getLng(), point2.getLat());
	}

	/**
	 * 方法2：根据经纬度，获取两点间的距离，单位：米<br>
	 * 这个方法误差稍大，但性能是 distanceInMeters() 的3倍左右。
	 */
	public static int distanceByLngLat(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = lat1 * Math.PI / 180;
		double radLat2 = lat2 * Math.PI / 180;
		double a = radLat1 - radLat2;
		double b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;
		return (int) s;
	}

	/**
	 * 由'点'到'面'的计算(四边形) -- 应该是地图工具类来计算,牵扯到使用哪种MAP实现
	 * 
	 * @param point 当前点坐标
	 * @param howFar 多远(米)
	 * @param offset 一米的坐标偏移量(和不同的地图商有关)
	 * @return 一个面积区域的连续坐标点
	 */
	protected static Point[] point2Quadrilateral(Point point, int howFar, double offset) {
		double lng = point.getLng();
		double lat = point.getLat();
		return new Point[] { pointOffsetXy(howFar, offset, false, lng, true, lat)// 左上
				, pointOffsetXy(howFar, offset, true, lng, true, lat)// 右上
				, pointOffsetXy(howFar, offset, true, lng, false, lat)// 右下
				, pointOffsetXy(howFar, offset, false, lng, false, lat) // 左下
		};
	}

	/**
	 * 判断点是否在多边形内 http://www.cnblogs.com/relax/p/3507014.html
	 * 
	 * @param point
	 * @param polygon
	 * @return
	 */
	public static boolean isPointInPolygon(Point point, Point[] polygon) {
		if (polygon.length < 3)
			return false;

		int iSum = 0, iCount;
		double dLng1, dLng2, dLat1, dLat2, dLng;
		iCount = polygon.length;
		for (int i = 0; i < iCount - 1; i++) {
			if (i == iCount - 1) {
				dLng1 = polygon[i].getLng();
				dLat1 = polygon[i].getLat();
				dLng2 = polygon[0].getLng();
				dLat2 = polygon[0].getLat();
			} else {
				dLng1 = polygon[i].getLng();
				dLat1 = polygon[i].getLat();
				dLng2 = polygon[i + 1].getLng();
				dLat2 = polygon[i + 1].getLat();
			}
			double aLat = point.getLat();
			double aLng = point.getLng();
			// 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
			if (((aLat >= dLat1) && (aLat < dLat2)) || ((aLat >= dLat2) && (aLat < dLat1))) {
				if (Math.abs(dLat1 - dLat2) > 0) {
					// 得到 A点向左射线与边的交点的x坐标：
					dLng = dLng1 - ((dLng1 - dLng2) * (dLat1 - aLat)) / (dLat1 - dLat2);

					// 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
					if (dLng < aLng)
						iSum++;
				}
			}
		}

		if (iSum % 2 != 0)
			return true;
		return false;
	}

	// 2d 算法，判定是否在多边形内

	/**
	 * 判断当前位置是否在围栏内
	 */
	public static boolean inPolygon(double lng, double lat, List<double[]> pointList) {
		double[][] array = pointList.toArray(new double[pointList.size()][]);
		return inPolygon(lng, lat, array);
	}

	/**
	 * 判断当前位置是否在围栏内
	 */
	public static boolean inPolygon(double lng, double lat, double[][] pointList) {
		Point2D.Double point = new Point2D.Double(lng, lat);

		List<Point2D.Double> plist = new ArrayList<Point2D.Double>();
		for (double[] enclosure : pointList) {
			// #TODO hins 2015/10/13 update 因为在匹配门店配送范围规则的时候，数据来源是[lat,lng]，所以暂时x,y轴的值交换获取
			double polygonPoint_x = enclosure[1];
			double polygonPoint_y = enclosure[0];
			Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
			plist.add(polygonPoint);
		}

		PointUtils test = new PointUtils();
		return test.checkWithJdkGeneralPath(point, plist);
	}

	/**
	 * 返回一个点是否在一个多边形区域内
	 */
	private boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
		GeneralPath p = new GeneralPath();

		Point2D.Double first = polygon.get(0);
		p.moveTo(first.x, first.y);
		polygon.remove(0);
		for (Point2D.Double d : polygon) {
			p.lineTo(d.x, d.y);
		}

		p.lineTo(first.x, first.y);

		p.closePath();

		return p.contains(point);
	}

	// ####

	/**
	 * 对xy进行移位操作,得到一个新的坐标
	 */
	private static Point pointOffsetXy(int howFar, double offset, boolean addX, double lng, boolean addY, double lat) {
		double offsetXy = (offset * howFar);
		double lng_ = (addX ? lng + offsetXy : lng - offsetXy);
		double lat_ = (addY ? lat + offsetXy : lat - offsetXy);
		return new Point(lng_, lat_);
	}

	// #### -- Test

	public static void main000(String[] args) {
		String xy1 = "113.333261,23.137202";
		String xy2 = "113.344795,23.133347";
		String xy3 = "113.350652,23.1267";
		String xy4 = "113.344688,23.129858";// in

		String[] xys = { "113.339909,23.13235", "113.340124,23.128495", "113.34997,23.127731", "113.349934,23.131719" };
		double[][] xxyy = xy(xys);

		double[] xy = xy(xy1);
		System.out.println("1 >> " + inPolygon(xy[0], xy[1], xxyy));

		xy = xy(xy2);
		System.out.println("2 >> " + inPolygon(xy[0], xy[1], xxyy));

		xy = xy(xy3);
		System.out.println("3 >> " + inPolygon(xy[0], xy[1], xxyy));

		xy = xy(xy4);
		System.out.println("4 >> " + inPolygon(xy[0], xy[1], xxyy));
	}

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Point pt1 = new Point(116.395, 39.910);
			Point pt2 = new Point(116.394, 39.914);
			Point pt3 = new Point(116.403, 39.920);
			Point pt4 = new Point(116.402, 39.914);
			Point pt5 = new Point(116.410, 39.913);
			Point[] array = new Point[] { pt1, pt2, pt3, pt4, pt5 };
			Point p = new Point(116.400, 39.914);
			isPointInPolygon(p, array);
		}
		long time = System.currentTimeMillis() - t1;
		System.out.println("time1 : " + time);

		t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			Point pt1 = new Point(116.395, 39.910);
			Point pt2 = new Point(116.394, 39.914);
			Point pt3 = new Point(116.403, 39.920);
			Point pt4 = new Point(116.402, 39.914);
			Point pt5 = new Point(116.410, 39.913);
			Point[] array = new Point[] { pt1, pt2, pt3, pt4, pt5 };
			Point p = new Point(116.400, 39.914);
			isPointInPolygon(p, array);
		}
		time = System.currentTimeMillis() - t1;
		System.out.println("time2 : " + time);
	}

	private static double[] xy(String str) {
		String[] s = str.split("[,]");
		return new double[] { Double.parseDouble(s[0]), Double.parseDouble(s[1]) };
	}

	private static double[][] xy(String[] strs) {
		double[][] xy = new double[strs.length][];
		int index = 0;
		for (String str : strs)
			xy[index++] = xy(str);
		return xy;
	}

}

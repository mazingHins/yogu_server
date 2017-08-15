package com.yogu.core.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.base.Point;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

/**
 * 高德地图api
 *
 * @date 2016年10月8日 上午11:15:30
 * @author hins
 */
public class AmapUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AmapUtils.class);
	
	/**
	 * 计算2个坐标在高德地图上的步行距离，单位米<br>
	 * 如果2个坐标有多条路线，选取距离最少的
	 * @param point1 - 收货地址,经纬度
	 * @param point2 - 商家地址,经纬度
	 * @author hins
	 * @date 2016年10月8日 上午11:16:35
	 * @return int
	 */
	public static int distanceByLngLat(Point point1, Point point2) {
		String key = ConfigRemoteService.getConfig(ConfigGroupConstants.SERVER_CONFIG, "amapAccesskey");
		//  小数点不超过6位置
		double originLat = new BigDecimal(point2.getLat()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		double originLng = new BigDecimal(point2.getLng()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		double destinationLat = new BigDecimal(point1.getLat()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		double destinationLng = new BigDecimal(point1.getLng()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		Map<String, String> params = new HashMap<>(6);
		params.put("key", key);
		params.put("origin", originLng + "," + originLat);
		params.put("destination", destinationLng + "," + destinationLat);
		params.put("output", "JSON");
		
		String responce = HttpClientUtils.doGet("http://restapi.amap.com/v3/direction/walking", params);
		AmapWalkJson json = JsonUtils.parseObject(responce, AmapWalkJson.class);
		if(!json.getStatus().equals("1")){
			logger.error("utils#AmapUtils#distanceByLngLat | 高德步行路径规划接口请求结果-失败  | responce: {}", responce);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "计算距离失败");
		}
		
		// 以下代码，出现其他异常，直接抛出
		
		List<AmapWalkPathJson> paths = json.getRoute().getPaths();
		if(paths == null || paths.isEmpty()){
			logger.error("utils#AmapUtils#distanceByLngLat | 高德步行路径规划接口请求结果-没有步行路线  | responce: {}", responce);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "没有步行路线");
		}
		
		// 选取路线最短的
		int distance = 0;
		for (AmapWalkPathJson path : paths) {
			int tmp = Integer.valueOf(path.getDistance());
			if (tmp < distance || distance == 0)
				distance = tmp;
		}
		logger.info("utils#AmapUtils#distanceByLngLat | 计算2个坐标在高德地图上的步行距离success | originLat: {}, originLng: {}, destinationLat: {}, destinationLng: {}, distance: {}", originLat, originLng, destinationLat, destinationLng, distance);
		return distance;

	}
	
	/**
	 * 高德步行路径规划接口返回结果对象
	 *
	 * @date 2016年10月8日 上午11:40:17
	 * @author hins
	 */
	public static class AmapWalkJson implements Serializable {
		
		private static final long serialVersionUID = -1568391830670676808L;

		/**
		 * 返回状态 1：成功；0：失败
		 */
		private String status;
		
		/**
		 * 返回的状态信息
		 */
		private String info;
		
		/**
		 * 返回结果总数目
		 */
		private String count;
		
		/**
		 * 路线信息
		 */
		private AmapWalkRouteJson route;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public AmapWalkRouteJson getRoute() {
			return route;
		}

		public void setRoute(AmapWalkRouteJson route) {
			this.route = route;
		}
		
	}
	
	/**
	 * 高德步行路径规划接口-路线信息
	 *
	 * @date 2016年10月8日 上午11:42:57
	 * @author hins
	 */
	public static class AmapWalkRouteJson implements Serializable {
		
		private static final long serialVersionUID = -6647783814805601323L;

		/**
		 * 起点坐标
		 */
		private String origin;
		
		/**
		 * 终点坐标
		 */
		private String destination;
		
		/**
		 * 步行方案
		 */
		private List<AmapWalkPathJson> paths;

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = origin;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public List<AmapWalkPathJson> getPaths() {
			return paths;
		}

		public void setPaths(List<AmapWalkPathJson> paths) {
			this.paths = paths;
		}
		
	}
	
	/**
	 * 高德步行路径规划接口-步行方案
	 *
	 * @date 2016年10月8日 上午11:43:49
	 * @author hins
	 */
	public static class AmapWalkPathJson implements Serializable {
		
		private static final long serialVersionUID = -6214446670674027281L;

		/**
		 * 起点和终点的步行距离，单位米
		 */
		private String distance;
		
		/**
		 * 步行时间预计，单位秒
		 */
		private String duration;

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}
		
	}

}

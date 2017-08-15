package com.yogu.remote.store;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.alarm.AlarmSender;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;

/**
 * 菜品库存操作 <br>
 * 
 * @author JFan 2015年7月21日 上午9:56:59
 */
@Named
public class DishSurplusRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(DishSurplusRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	/**
	 * 
	 * 旧接口,建议不再调用<br>
	 * 
	 * 消费（扣库存）菜品；<br>
	 * 1：这里不校验菜品是否存在，如果菜不存在，当作库存为0<br>
	 * 2：只有库存不足才会失败
	 * 
	 * @param form 菜品来源（外部保证这是同一家门店的）；key：菜品dishKey、value：这个菜的数量
	 * @param day 指定这一天的库存(yyyyMMdd格式)
	 * @return 操作【异常】记录集；1：map.empty = true表示全部操作成功；2：操作失败才记录（key：dishKey，value：剩余数量）
	 */
	@Deprecated
	public Map<Long, Integer> consumeSurplus(Map<Long, Integer> form, int day) {
		Args.notEmpty(form, "'form'");

		try {
			StringBuffer url = new StringBuffer(host);
			url.append("/api/dishSurplus/consume?day=").append(day);
			for (Entry<Long, Integer> entry : form.entrySet())
				for (int index = 0; index < entry.getValue(); index++)
					url.append("&dishKey=").append(entry.getKey());
			logger.info("remote#dishSurplus#sonsume | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#sonsume | Error | date: {}, form: {}, message: {}", day, JsonUtils.toJSONString(form),
					e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据规格 扣库存, 新接口 <br>
	 * 
	 * 根据具体的规格key消费库存数据
	 * 
	 * 
	 * @param form 菜品来源 map ------key：菜品规格specKey, value：这个菜的数量
	 * @param day 指定这一天的库存(yyyyMMdd格式)
	 * @return 操作【异常】记录集；1：map.empty = true表示全部操作成功；2：操作失败才记录（key：specKey，value：剩余数量）
	 * @author sky 2016-02-23
	 */
	public Map<Long, Integer> consumeSpecSurplus(Map<Long, Integer> form, int day) {
		Args.notEmpty(form, "'form'");

		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSpecSurplus/consume?day=").append(day);
			for (Entry<Long, Integer> entry : form.entrySet())
				for (int index = 0; index < entry.getValue(); index++)
					url.append("&specKey=").append(entry.getKey());

			logger.info("remote#dishSurplus#consumeSpecSurplus | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#consumeSpecSurplus | Error | date: {}, form: {}, message: {}", day,
					JsonUtils.toJSONString(form), e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 还原（释放）菜品规格库存<br>
	 * 这里并不知道需要释放的数量是否正确，由调用者确保。<br>
	 * 例如：上次扣2个库存，订单取消后却要求释放5个，这里不知道是否正确<br>
	 * 注意：date这天，菜品的库存必须存在（不会自动创建），否则释放失败
	 * 
	 * @param form 菜品来源（外部保证这是同一家门店的）；key：菜品规格specKey、value：这个菜的数量
	 * @param day 指定这一天的库存
	 * @author sky 2016-02-23
	 */
	public boolean releaseSpecSurplusBySpecKey(Map<Long, Integer> form, int day) {
		Args.notEmpty(form, "'form'");

		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSpecSurplus/release?day=").append(day);
			for (Entry<Long, Integer> entry : form.entrySet())
				for (int index = 0; index < entry.getValue(); index++)
					url.append("&specKey=").append(entry.getKey());
			logger.info("remote#dishSurplus#releaseSpecSurplusBySpecKey | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#releaseSpecSurplusBySpecKey | Error | date: {}, form: {}, message: {}", day,
					JsonUtils.toJSONString(form), e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 
	 * 旧接口,建议不再调用<br>
	 * 
	 * 
	 * 还原（释放）菜品库存<br>
	 * 这里并不知道需要释放的数量是否正确，由调用者确保。<br>
	 * 例如：上次扣2个库存，订单取消后却要求释放5个，这里不知道是否正确<br>
	 * 注意：date这天，菜品的库存必须存在（不会自动创建），否则释放失败
	 * 
	 * @param form 菜品来源（外部保证这是同一家门店的）；key：菜品dishKey、value：这个菜的数量
	 * @param date 指定这一天的库存
	 */
	@Deprecated
	public boolean releaseSurplusByDishKey(Map<Long, Integer> form, Date date) {
		Args.notNull(date, "'date'");
		Args.notEmpty(form, "'form'");

		int day = DateUtils.currentTimeDay(date);
		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSurplus/release?day=").append(day);
			for (Entry<Long, Integer> entry : form.entrySet())
				for (int index = 0; index < entry.getValue(); index++)
					url.append("&dishKey=").append(entry.getKey());
			logger.info("remote#dishSurplus#releaseSurplusByDishKey | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#releaseSurplusByDishKey | Error | date: {}, form: {}, message: {}", day,
					JsonUtils.toJSONString(form), e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 旧接口,建议不再调用<br>
	 * 
	 * 还原（释放）菜品库存<br>
	 * 这里并不知道需要释放的数量是否正确，由调用者确保。<br>
	 * 例如：上次扣2个库存，订单取消后却要求释放5个，这里不知道是否正确<br>
	 * 注意：date这天，菜品的库存必须存在（不会自动创建），否则释放失败
	 * 
	 * @param form 菜品来源（外部保证这是同一家门店的）；key：菜品dishId、value：这个菜的数量
	 * @param day 指定这一天的库存
	 */
	@Deprecated
	public boolean releaseSurplusByDishId(Map<Long, Integer> form, int day) {
		Args.notEmpty(form, "'form'");

		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSurplus/release/byId?day=").append(day);
			for (Entry<Long, Integer> entry : form.entrySet())
				for (int index = 0; index < entry.getValue(); index++)
					url.append("&dishId=").append(entry.getKey());
			logger.info("remote#dishSurplus#releaseSurplusByDishId | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#releaseSurplusByDishId | Error | date: {}, form: {}, message: {}", day,
					JsonUtils.toJSONString(form), e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * 还原（释放）菜品库存<br>
	 * 这里并不知道需要释放的数量是否正确，由调用者确保。<br>
	 * 例如：上次扣2个库存，订单取消后却要求释放5个，这里不知道是否正确<br>
	 * 注意：date这天，菜品的库存必须存在（不会自动创建），否则释放失败
	 * 
	 * @param form 菜品来源（外部保证这是同一家门店的）；key：规格specId、value：这个菜的数量
	 * @param day 指定这一天的库存
	 */
	public boolean releaseSurplusBySpecId(Map<Long, Integer> form, int day) {
		Args.notEmpty(form, "'form'");

		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSurplus/release/bySpecId?day=").append(day);
			for (Entry<Long, Integer> entry : form.entrySet())
				for (int index = 0; index < entry.getValue(); index++)
					url.append("&specId=").append(entry.getKey());
			logger.info("remote#dishSurplus#releaseSurplusBySpecId | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			logger.info("remote#dishSurplus#releaseSurplusBySpecId | request | url: '{}', result: {}", json);
		} catch (Exception e) {
			logger.error("remote#dishSurplus#releaseSurplusBySpecId | Error | day: {}, form: {}, message: {}", day, JsonUtils.toJSONString(form),
					e.getMessage(), e);

			AlarmSender.sendAlarm(CommonConstants.ORDER_DOMAIN, "释放库存失败", JsonUtils.toJSONString(form));
		}
		return true;
	}

	/**
	 * 查询美食库存。<br>
	 * 
	 * @author Hins
	 * @date 2015年10月27日 下午3:54:39
	 * 
	 * @param form 菜品来源（外部保证这是同一家门店的）；key：菜品dishKey、value：这个菜的数量
	 * @param day - 查询日期yyyyMMdd
	 * @return 库存记录集；key：菜品ID，value：剩余数量；map.empty = 表示获取不到库存；调用失败返回null
	 */
	public Map<Long, Integer> findDishSurplus(Set<Long> dishKeys, int day) {
		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSurplus/listDishSurplus?day=").append(day);
			for (Long dishKey : dishKeys) {
				url.append("&dishKey=").append(dishKey);
			}

			logger.info("remote#dishSurplus#findDishSurplus | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#findDishSurplus | Error | date: {}, dishKeys: {}, message: {}", day,
					JsonUtils.toJSONString(dishKeys), e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 批量获取门店下 某些规格的 库存数据
	 * 
	 * @param storeId 门店id
	 * @param day 指定库存日期
	 * @param specKeys 规格keys
	 * @return key=规格key，value=库存 的map
	 * @author sky 2016-02-23
	 */
	public Map<Long, Integer> findSpecSurplus(long storeId, Set<Long> specKeys, int day) {
		try {
			StringBuilder url = new StringBuilder(host);
			url.append("/api/dishSpecSurplus/listSpecSurplus?day=").append(day);
			url.append("&storeId=").append(storeId);
			for (Long specKey : specKeys) {
				url.append("&specKey=").append(specKey);
			}

			logger.info("remote#dishSurplus#findSpecSurplus |  request | storeId: {}, url: '{}'", storeId, url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#findSpecSurplus | Error | date: {}, storeId: {}, dishKeys: {}, message: {}", day, storeId,
					JsonUtils.toJSONString(specKeys), e.getMessage(), e);
		}
		return null;
	}

}

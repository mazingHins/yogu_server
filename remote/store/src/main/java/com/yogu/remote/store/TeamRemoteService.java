package com.yogu.remote.store;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.base.dto.TeamPayBuy;
import com.yogu.services.store.base.dto.TeamPayRecord;

/**
 *	团购远程服务类 
 * @author east
 * @date 2017年5月10日 下午5:45:57
 */
@Named
public class TeamRemoteService {
	private static final Logger logger = LoggerFactory.getLogger(TeamRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;
	
	/**
	 * 根据teamPayId获取TeamPayBuy
	 * TODO
	 * @param teamPayId
	 * @return    
	 * @author east
	 * @date 2017年5月11日 下午3:05:11
	 */
	public TeamPayBuy getByTeamPayId(long teamPayId){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("teamPayId", teamPayId+"");
			String json = HttpClientUtils.doGet(host + "/api/team/get/teamPay", params);
			RestResult<TeamPayBuy> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TeamPayBuy>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("TeamRemoteService#createTeamPayBuy | 创建团拼购买表失败", e);
			throw new ServiceException(ResultCode.FAILURE, "创建团拼购买表失败");
		}
	}
	
	/**
	 * 根据buyId获取TeamPayBuy
	 * TODO
	 * @param teamPayId
	 * @return    
	 * @author east
	 * @date 2017年5月11日 下午3:05:11
	 */
	public TeamPayBuy getByTeamPayBuyId(long buyId){
		try {
			Map<String, String> params = new HashMap<String, String>(2);
			params.put("buyId", buyId+"");
			String json = HttpClientUtils.doGet(host + "/api/team/get/teamPayBuy", params);
			RestResult<TeamPayBuy> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TeamPayBuy>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("TeamRemoteService#createTeamPayBuy | 创建团拼购买表失败", e);
			throw new ServiceException(ResultCode.FAILURE, "创建团拼购买表失败");
		}
	}
	
	/**
	 * 根据teamPayId创建TeamPayBuy并返回
	 * @param teamPayId
	 * @return    
	 * @author east
	 * @date 2017年5月11日 下午3:05:23
	 */
	public TeamPayBuy createTeamPayBuy(long teamPayId){
		try {
			Map<String, Object> params = new HashMap<String, Object>(2);
			params.put("teamPayId", teamPayId);
			String json = HttpClientUtils.doPost(host + "/api/team/create/teamPayBuy", params);
			RestResult<TeamPayBuy> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TeamPayBuy>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("TeamRemoteService#createTeamPayBuy | 创建团拼购买表失败", e);
			throw new ServiceException(ResultCode.FAILURE, "创建团拼购买表失败");
		}
	}
	
	public TeamPayRecord createTeamPayRecord(long buyId, long uid, long orderNo){
		try {
			Map<String, Object> params = new HashMap<String, Object>(4);
			params.put("buyId", buyId);
			params.put("uid", uid);
			params.put("orderNo", orderNo);
			String json = HttpClientUtils.doPost(host + "/api/team/create/teamPayRecord", params);
			
			RestResult<TeamPayRecord> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TeamPayRecord>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("TeamRemoteService#createTeamPayBuy | 创建团拼购买表失败", e);
			throw new ServiceException(ResultCode.FAILURE, "创建团拼购买表失败");
		}
	}
	
	/**
	 * 根据buyid获取一个拼团的购买人数
	 * @param buyId
	 * @return    
	 * @author east
	 * @date 2017年5月27日 下午12:19:24
	 */
	public List<TeamPayRecord> listTeamPayRecord(long buyId){
		try {
			Map<String, String> params = new HashMap<String, String>(2);
			params.put("buyId", buyId + "");
			String json = HttpClientUtils.doGet(host + "/api/team/list/teamPayRecord", params);
			
			RestResult<List<TeamPayRecord>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<TeamPayRecord>>>() {});
			if(result.getObject() == null)
				return Collections.emptyList();
			
			return result.getObject();
		} catch (Exception e) {
			throw new ServiceException(ResultCode.FAILURE, "获取拼团购买记录失败");
		}
	}
	
	/**
	 * 获取过期未消费的团购记录
	 * @return    
	 * @author east
	 * @date 2017年5月16日 下午4:04:13
	 */
	public List<TeamPayRecord> listTeamPayRecordByExpire(){
		try {
			String json = HttpClientUtils.doGet(host + "/api/team/expire");
			RestResult<List<TeamPayRecord>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<TeamPayRecord>>>() {});
			if(result.getObject() == null)
				return Collections.emptyList();
			return result.getObject();
		} catch (Exception e) {
			throw new ServiceException(ResultCode.FAILURE, "获取拼团购买记录失败");
		}
	}
	
	/**
	 * 根据recordId获取TeamPayRecord
	 * @param teamPayId
	 * @return    
	 * @author east
	 * @date 2017年5月11日 下午3:05:11
	 */
	public TeamPayRecord getTeamPayRecord(long recordId){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("recordId", recordId+"");
			String json = HttpClientUtils.doGet(host + "/api/team/get/teamPayRecord", params);
			RestResult<TeamPayRecord> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TeamPayRecord>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("TeamRemoteService#createTeamPayBuy | 获取拼团购买记录失败", e);
			throw new ServiceException(ResultCode.FAILURE, "获取拼团购买记录失败");
		}
	}
	
	/**
	 * 根据团购规则id和团的状态获取总共拼了多少个团
	 * @param buyId
	 * @return    
	 * @author east
	 * @date 2017年5月26日 下午7:09:57
	 */
	public List<TeamPayBuy> listTeamPay(long teamPayId){
		try {
			Map<String, String> params = new HashMap<String, String>(2);
			params.put("teamPayId", teamPayId + "");
			String json = HttpClientUtils.doGet(host + "/api/team/list/teamPay", params);
			
			RestResult<List<TeamPayBuy>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<TeamPayBuy>>>() {});
			if(result.getObject() == null)
				return Collections.emptyList();
			
			return result.getObject();
		} catch (Exception e) {
			throw new ServiceException(ResultCode.FAILURE, "获取用户拼团购买记录失败");
		}
	}

	/**
	 * 获取一个用户在一个团购规则里面的所有参与次数
	 * @param teamPayId
	 * @param uid
	 * @return    
	 * @author east
	 * @date 2017年5月27日 下午12:18:23
	 */
	public List<TeamPayRecord> listAllByBuyIdsAndUid(long teamPayId, long uid) {
		try {
			Map<String, String> params = new HashMap<String, String>(2);
			params.put("teamPayId", teamPayId + "");
			params.put("uid", uid + "");
			String json = HttpClientUtils.doGet(host + "/api/team/listAllByBuyIdsAndUid", params);
			
			RestResult<List<TeamPayRecord>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<TeamPayRecord>>>() {});
			if(result.getObject() == null)
				return Collections.emptyList();
			
			return result.getObject();
		} catch (Exception e) {
			throw new ServiceException(ResultCode.FAILURE, "获取用户拼团购买记录失败");
		}
	}
}

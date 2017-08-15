package com.yogu.services.store.activity.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yogu.services.store.ticket.vo.BuyTicketsVO;

/**
 * 创建票订单流程中，store域相关操作<br>
 * 如验证票、库存、扣库存
 * 
 * @author east
 * @date 2017年2月28日 下午6:57:22
 */
public class TicketCreateOrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 543969541421162L;

	private Map<Long, List<BuyTicketsVO>> ticket = new HashMap<Long, List<BuyTicketsVO>>(); // 存放真实的已分配好的ticket相关信息的结果集,key=ticketRuleId, value是
													// ticketRuleId下具体子票的信息(比如购买2张ticketRuleId的票, 那么list中是两个子票的信息)
													// add by Sky 2017-03-01

	/**
	 * 扣除库存的结果，1：map.empty = true表示全部操作成功；2：操作失败才记录（key：票ID，value：剩余数量）
	 */
	private Map<Long, Integer> surpluss;

	public Map<Long, List<BuyTicketsVO>> getTicket() {
		return ticket;
	}

	public void setTicket(Map<Long, List<BuyTicketsVO>> ticket) {
		this.ticket = ticket;
	}

	public Map<Long, Integer> getSurpluss() {
		return surpluss;
	}

	public void setSurpluss(Map<Long, Integer> surpluss) {
		this.surpluss = surpluss;
	}
	
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


}

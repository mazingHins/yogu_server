package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 团购未成功的BO
 * 
 * @author east
 * @date 2017年5月12日 下午3:53:31
 */
public class TeamNotSuccessBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3872294756136659819L;

	private long uid;

	private long orderNo;

	private long buyId;

	private String remark;

	private short teamType;

	public long getUid() {
		return uid;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public long getBuyId() {
		return buyId;
	}

	public String getRemark() {
		return remark;
	}

	public short getTeamType() {
		return teamType;
	}

	public TeamNotSuccessBO() {
	};

	public TeamNotSuccessBO(long uid, long orderNo, long buyId, String remark, short teamType) {
		this.uid = uid;
		this.orderNo = orderNo;
		this.buyId = buyId;
		this.remark = remark;
		this.teamType = teamType;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private long uid;

		private long orderNo;

		private long buyId;

		private String remark;
		
		private short teamType;

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setBuyId(long buyId) {
			this.buyId = buyId;
			return this;
		}

		public Builder setRemark(String remark) {
			this.remark = remark;
			return this;
		}

		public Builder setTeamType(short teamType) {
			this.teamType = teamType;
			return this;
		}
		
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.TEAM_NOT_SUCCESS,
					new TeamNotSuccessBO(uid, orderNo, buyId, remark, teamType));
		}
	}
}

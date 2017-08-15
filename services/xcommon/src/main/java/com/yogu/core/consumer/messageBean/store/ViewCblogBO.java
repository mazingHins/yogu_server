package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 用户查看cblog的BO
 *
 * @date 2017年1月11日 上午10:46:02
 * @author hins
 */
public class ViewCblogBO implements Serializable {

	private static final long serialVersionUID = -6613792562043098969L;

	/** 操作者ID */
	private long uid;

	/** 门店ID */
	private long sinfoId;

	public long getUid() {
		return uid;
	}

	public long getSinfoId() {
		return sinfoId;
	}

	public ViewCblogBO() {
	}

	ViewCblogBO(long uid, long sinfoId) {
		this.uid = uid;
		this.sinfoId = sinfoId;
	}

	public static ViewCblogBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		/** 操作者ID */
		private long uid;

		/** 门店ID */
		private long sinfoId;

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setSinfoId(long sinfoId) {
			this.sinfoId = sinfoId;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public ViewCblogBO build() {
			return new ViewCblogBO(uid, sinfoId);
		}

		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.VIEW_CBLOG, new ViewCblogBO(uid, sinfoId));
		}

	}
}

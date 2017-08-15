package com.yogu.services.store.ticket.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TicketInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7376745998207404732L;
		private long ticketRuleId;// 票规则id
		private int num;// 数量
		private int[] seats;// 座位号,暂不实现

		public TicketInfo() {
			super();
		}

		public int[] getSeats() {
			return seats;
		}

		public void setSeats(int[] seats) {
			this.seats = seats;
		}

		public long getTicketRuleId() {
			return ticketRuleId;
		}

		public void setTicketRuleId(long ticketRuleId) {
			this.ticketRuleId = ticketRuleId;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}
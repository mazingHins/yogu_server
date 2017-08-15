package com.yogu.services.store.ticket.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 购买时预分配票的信息载体, 包含ticketNo,ticketId信息, 在下单时需要
 * 
 * @author sky
 *
 */
public class BuyTicketsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 453761330640955702L;
	private long ticketId;
	private int seat;

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

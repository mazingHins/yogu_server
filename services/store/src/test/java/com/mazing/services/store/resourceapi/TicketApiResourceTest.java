package com.mazing.services.store.resourceapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.consumer.messageBean.store.TicketRefundBO;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.services.store.activity.vo.TicketCreateOrderVO;
import com.yogu.services.store.ticket.vo.AssignTicketBO;
import com.yogu.services.store.ticket.vo.TicketInfo;

public class TicketApiResourceTest extends HttpResourceTest {
	private static long ticketRuleId = 51002;
	private static long adminId = 100003;

	public TicketApiResourceTest() {
		host = "http://storeapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}

	@Test
	public void testChangeSale() {

		ApiReq<RestResult<?>> req = build("api/ticket/ticketRule/changeSale.do");
		req.putPost("ticketRuleId", "" + ticketRuleId);
		req.putPost("isStop", "1");
		req.putPost("adminId", "" + adminId);

		RestResult<?> result = req.doPost();

		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}

	@Test
	public void testRefundNotify() {

		ApiReq<RestResult<?>> req = build("api/ticket/refund/notify.do");

		long orderNo = 1703680196356639L;
		short refundStatus = 1;// 1：申请退款 2：退款成功
		short refundType = 2;// 1:整单退 2：部分退
		String ticketIds = "2";
		long uid = 106801;

		TicketRefundBO bo = new TicketRefundBO();
		bo.setOrderNo(orderNo);
		bo.setRefundStatus(refundStatus);
		bo.setRefundType(refundType);
		bo.setTicketIds(ticketIds);
		bo.setUid(uid);

		req.putPost("params", JsonUtils.toJSONString(bo));

		RestResult<?> result = req.doPost();

		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}

	@Test
	public void testPaySuccessNotify() {
		ApiReq<RestResult<?>> req = build("api/ticket/paySuccess/notify");

		long orderNo = 11;
		long uid = 22;
		req.putGet("orderNo", "" + orderNo);
		req.putGet("uid", "" + uid);

		RestResult<?> result = req.doGet();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}

	@Test
	public void testSaveTicket() {
		ApiReq<RestResult<?>> req = build("api/ticket/saveTicket.do");

		Date now = new Date();

		String start = DateUtils.formatDate(DateUtils.nextDay(now, -1), DateUtils.YYYY_MM_DD_HH_mm_ss);
		String end = DateUtils.formatDate(DateUtils.nextDay(now, 5), DateUtils.YYYY_MM_DD_HH_mm_ss);
		req.putPost("ticketName", "贵族vip票");
		req.putPost("ticketEnName", "High level22");
		req.putPost("price", "100");
		req.putPost("originalPrice", "150");
		req.putPost("limitBuyCount", "4");
		req.putPost("createTotal", "100");
		req.putPost("img", "");
		req.putPost("canShare", "0");
		req.putPost("canTransfer", "0");
		req.putPost("adminId", "100003");
		req.putPost("startDay", start);
		req.putPost("endDay", end);

		RestResult<?> result = req.doPost();

		assertTrue(result.getCode() == ResultCode.SUCCESS);

	}

	@Test
	public void testCreateNewBatch() {

		ApiReq<RestResult<?>> req = build("api/ticket/newBatch.do");

		long ticketRuleId = 2002;

		req.putPost("createTotal", "10");
		req.putPost("ticketRuleId", "" + ticketRuleId);
		req.putPost("adminId", "" + adminId);

		RestResult<?> result = req.doPost();

		assertTrue(result.getCode() == ResultCode.SUCCESS);

	}

	@Test
	public void testConsumeSurplus() {

		ApiReq<RestResult<?>> req = build("api/ticket/surplus/consumeTicket.do");

		AssignTicketBO buyInfo = new AssignTicketBO();

		long actId = 14;

		long orderNo = 1122;

		long storeId = 10401;

		long uid = 100003;

		long ticketRuleId_1 = 2002;

		long ticketRuleId_2 = 3014;

		List<TicketInfo> list = new ArrayList<>();

		// 购买2张
		TicketInfo ticket = new TicketInfo();
		ticket.setNum(5);
		ticket.setTicketRuleId(ticketRuleId_1);

		// 购买4张
		TicketInfo ticket2 = new TicketInfo();
		ticket2.setNum(100);
		ticket2.setTicketRuleId(ticketRuleId_2);

		list.add(ticket);
		list.add(ticket2);

		buyInfo.setActId(actId);
		buyInfo.setOrderNo(orderNo);
		buyInfo.setStoreId(storeId);
		buyInfo.setUid(uid);
		buyInfo.setList(list);

		req.putPost("buyInfo", JsonUtils.toJSONString(buyInfo));

		RestResult<?> result = req.doPost();
		assertTrue(result.getCode() == ResultCode.SUCCESS);

	}

	private static final int N = 5;

	private static int num = 2;

	private static int num2 = 1;

	volatile int count = 0;

	private static long ticketRuleId1 = 6002;// 45个库存, 已售0;
	private static long ticketRuleId2 = 51002;// 11个库存, 已售3;

	@Test
	public void testConcurrentConsumeChildSurplus() {
		// 并发消费 存储在redis中的ticketId 库存数据

		CountDownLatch doneSignal = new CountDownLatch(N);
		CountDownLatch startSignal = new CountDownLatch(1);// 开始执行信号

		for (int i = 1; i <= N; i++) {
			new Thread(new Worker2(i, doneSignal, startSignal)).start();// 线程启动了
		}
		System.out.println("begin------------");
		startSignal.countDown();// 开始执行啦
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// 等待所有的线程执行完毕
		System.out.println("Ok, success count=" + count);

	}

	class Worker2 implements Runnable {
		private final CountDownLatch doneSignal;
		private final CountDownLatch startSignal;
		private int beginIndex;

		Worker2(int beginIndex, CountDownLatch doneSignal, CountDownLatch startSignal) {
			this.startSignal = startSignal;
			this.beginIndex = beginIndex;
			this.doneSignal = doneSignal;
		}

		private AssignTicketBO getBuyInfo(int beginIndex) {
			AssignTicketBO buyInfo = new AssignTicketBO();
			long actId = 14;
			long orderNo = beginIndex;
			long storeId = 10401;

			buyInfo.setActId(actId);
			buyInfo.setOrderNo(orderNo);
			buyInfo.setStoreId(storeId);
			buyInfo.setUid(adminId);

			List<TicketInfo> list = new ArrayList<>();
			TicketInfo ticket = new TicketInfo();

			ticket.setTicketRuleId(ticketRuleId1);
			ticket.setNum(num);
			list.add(ticket);

			TicketInfo ticket2 = new TicketInfo();

			ticket2.setTicketRuleId(ticketRuleId2);
			ticket2.setNum(num2);
			list.add(ticket2);

			buyInfo.setList(list);

			return buyInfo;
		}

		public void run() {
			try {

				startSignal.await(); // 等待开始执行信号的发布

				// 生成购买信息
				AssignTicketBO buyInfo = getBuyInfo(beginIndex);

				Map<String, String> params = new HashMap<>(2);
				params.put("buyInfo", JsonUtils.toJSONString(buyInfo));

				String json = HttpClientUtils.doPost(host + "/api/ticket/surplus/consumeTicket.do", params);

				RestResult<TicketCreateOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TicketCreateOrderVO>>() {
				});

				if (result != null && result.getObject().getSurpluss().isEmpty()) {
					count++;
				}

				System.out.println(Thread.currentThread().getName() + ", 当前线程消费结果 : {}" + json);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				doneSignal.countDown();
			}
		}
	}

}

package com.yogu.services.order.base.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yogu.services.order.base.dto.vo.OrderByDateVO;
import com.yogu.services.order.base.dto.vo.OrderForExcelVO;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.entry.TinyOrderPO;
import com.yogu.services.order.constants.OrderSearchCategory;

/**
 * mz_order 表的只读接口
 * 
 * @author Hins
 * @date 2015年11月18日 下午7:24:19
 */
public interface OrderReadonlyDao {
	
	/**
	 * 根据主键读取记录<br>
	 * 此方法查询主库
	 * 
	 */
	public OrderPO getById(long orderId);
	
	/**
	 * 根据订单编号和下单人获取记录<br>
	 * 此方法查询主库
	 * 
	 * @param uid - 下单人
	 * @param orderNo - 订单编号
	 * @return
	 */
	public OrderPO getByOrderNoUid(long uid, long orderNo);

	/**
	 * 根据订单编号获取记录<br>
	 * 此方法查询主库
	 * 
	 * @param orderNo - 订单编号
	 * @return
	 */
	public OrderPO getByOrderNo(long orderNo);
	
	/**
	 * 根据条件查询记录，结果按创建时间倒序排序（越先创建越前）<br>
	 * 2016-6-15 modify by hins 内容：新增查询条件（payType），因为进行中的订单、历史订单不能出现米星付的订单
	 * 
	 * @param uid - 用户ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param status - 订单状态列表
	 * @param storeId - 订单所属门店ID(非必传)
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderPO> listUserOngoing(long uid, List<Short> payType, List<Short> status, Long storeId);
	
	/**
	 * 根据条件查询记录，结果按创建时间倒序排序（越先创建越前）<br>
	 * 2016-6-15 modify by hins 内容：新增查询条件（payType），因为进行中的订单、历史订单不能出现米星付的订单
	 * 2017-01-14 modify by hins 内容：因为现在有许多用户米星付订单有上千条进行中的订单，所以新增一个分页
	 * 
	 * @param uid - 用户ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param status - 订单状态列表
	 * @param storeId - 订单所属门店ID(非必传)
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * 
	 * @author hins
	 * @date 2017年1月14日 上午10:33:43
	 * @return List<OrderPO>
	 */
	public List<OrderPO> listUserOngoingByPage(long uid, List<Short> payType, List<Short> status, Long storeId, int offset, int pageSize);
	
	/**
	 * 用户查询历史订单，结果按创建时间倒序排序（越先创建越前）<br>
	 * 2016-6-15 modify by hins 内容：新增查询条件（payType），因为进行中的订单、历史订单不能出现米星付的订单
	 * 
	 * @author Hins
	 * @date 2015年9月1日 下午3:40:56
	 * 
	 * @param uid - 用户ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param status - 订单状态列表
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * @return
	 */
	public List<OrderPO> listUserHistory(long uid, List<Short> payType, List<Short> status, int offset, int pageSize);

	/**
	 * 商家按搜索类别搜索订单
	 * 
	 * @author Felix
	 * @date 2015年9月9日
	 * 
	 * @param storeId - 商家ID
	 * @param content - 搜索内容
	 * @param category - 搜索类别
	 * @param payType - 支付类型，参考枚举PayType，米星付版本新增加 by hins
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listByField(long storeId, String content, OrderSearchCategory category, List<Short> payType);
	
	/**
	 * 分页查询订单状态在status列表下配送员id为deliverId的商家未完成的订单列表 版本2<br>
	 * 
	 * 
	 * <b>deliverId 配送员id 可以为空</b> <br>
	 * 该接口主要用于兼容 ‘待收货确认 订单列表’ 规则： 配送员只能看到自己配送的订单
	 * 
	 * @param deliverId 配送员id,可以为null-->表示不做配送员id查询条件限制
	 * @param storeId - 商家ID
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param sequence 订单排序号
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<OrderPO> listStoreOngoing2(Long deliverId, long storeId, Short pickType, List<Short> status, long sequence, int pageSize);

	/**
	 * 分页查询订单状态在status列表下配送员id为deliverId的商家未完成的订单列表<br>
	 * 
	 * <b>deliverId 配送员id 可以为空</b> <br>
	 * 
	 * 该接口主要用于兼容 ‘待收货确认 订单列表’ 规则： 配送员只能看到自己配送的订单
	 * 
	 * @param deliverId 配送员id,可以为null-->表示不做配送员id查询条件限制
	 * @param storeId - 商家ID
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * @return 订单列表，若无，返回empty
	 * 
	 * @author sky 2015-11-10
	 */
	public List<OrderPO> listStoreOngoingOrdersByDeliverId(Long deliverId, long storeId, Short pickType, List<Short> status, int offset,
			int pageSize);
	
	/**
	 * 分页查询订单状态在status列表下的商家未完成的订单列表。
	 * 
	 * @author Hins
	 * @date 2015年10月15日 下午8:56:45
	 * 
	 * @param storeId - 商家ID
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listStoreOngoing(long storeId, Short pickType, List<Short> status, int offset, int pageSize);

	/**
	 * 分页查询订单状态在status列表下的商家完成的订单列表。<br>
	 * 查询指定支付类型的订单，按照创建时间倒序排序
	 * 
	 * @author Hins
	 * @date 2015年11月4日 下午4:43:46
	 * 
	 * @param storeId - 商家ID
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param payType - 支付类型：参照枚举PayType
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listStoreFinishByPayType(long storeId, List<Short> status, short payType, int offset, int pageSize);

	/**
	 * 分页查询订单状态在status列表下的商家完成的订单列表。<br>
	 * 按照完成时间倒序排序
	 * 
	 * @author Hins
	 * @date 2015年9月8日 下午4:41:10
	 * 
	 * @param storeId - 商家ID
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listStoreFinish(long storeId, List<Short> status, List<Short> payType, int offset, int pageSize);

	/**
	 * 根据商家 ID、配送方式、订单状态 查询符合条件的 商家订单列表, 用于商家订单的管理和查询 (兼容版本)
	 * 
	 * @param storeId 商家ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param pickType 配送方式,可以为null,为null表示查询所有配送方式
	 * @param status 订单状态,为null表示查询所有状态
	 * @param sequence 订单序列号
	 * @param pageSize 最大返回查询多少条数据
	 * @param orderBy 排序字段
	 * @return
	 */
	List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus2(long storeId, List<Short> payType, Short pickType, Short status, long sequence, int pageSize,
			String orderBy);
	
	/**
	 * 根据商家 ID、配送方式、订单状态 查询符合条件的 商家订单列表, 用于商家订单的管理和查询
	 * 
	 * @param storeId 商家ID
	 * @param pickType 配送方式,可以为null,为null表示查询所有配送方式
	 * @param status 订单状态,为null表示查询所有状态
	 * @param offset 起始记录
	 * @param pageSize 最大返回查询多少条数据
	 * @orderBy 排序字段
	 * @return 返回符合条件的记录
	 * 
	 * @author sky
	 */
	List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus(long storeId, Short pickType, Short status, int offset, int pageSize,
			String orderBy);
	
	/**
	 * 查询预计送达时间超过time时间，且订单状态等于status。
	 * 
	 * @author Hins
	 * @date 2015年11月6日 下午7:46:47
	 * 
	 * @param start - 超时时间绝对值(预计送达时间大于time)，开始时间
	 * @param end - 超时时间绝对值(预计送达时间大于time)，结束时间
	 * @param status - 订单状态
	 * @return
	 */
	public List<OrderPO> listStatusAndTimeOut(Date start, Date end, short status);

	/**
	 * 查询商家确认收货时间在某时间段内&&订单状态等于status的列表。
	 * 
	 * @author Hins
	 * @date 2015年10月9日 下午4:08:22
	 * 
	 * @param beginTime - 商家确认收货开始时间段
	 * @param endTime - 商家确认收货结束时间段
	 * @param status - 订单状态
	 * @return 记录列表，若无，返回empty list
	 */
	public List<OrderPO> listStoreConfirm(Date beginTime, Date endTime, long status);

	/**
	 * 根据 商家ID 、订单状态查询符合条件的订单列表，用于后台管理系统
	 * 
	 * @param storeId 商家ID
	 * @param status 订单状态，可以为null，为null时查询所有状态的订单
	 * @param offset 起始记录
	 * @param pageSize 最大返回查询多少条数据
	 * @return 返回符合条件的记录，以创建时间倒序排列
	 */
	List<OrderPO> listOrderByStoreId(long storeId, Short status, int offset, int pageSize);

	/**
	 * 根据订单状态 和 订单创建时间 的范围 获取数据
	 * 
	 * @param status 订单状态
	 * @param time 指定的订单创建时间
	 * @return
	 * @author felix
	 */
	public List<TinyOrderPO> listByStatusAndCreateTime(short status, Date time);

	/**
	 * 查询待接单的订单, 被退回的展示在上面, 新提交的展示在下面 1, 退回的订单, 送达时间越早越靠前 2, 新提交订单, 下单时间越早越靠前
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param offset 下标偏移量
	 * @param pageSize 每页显示数目
	 * @return
	 */
	@Deprecated  // by ten 2015/11/30，早应该标识，这里补上
	public List<OrderPO> listPendingAcceptOrders(long storeId, short status, int offset, int pageSize);

	/**
	 * 查询待接单的订单(版本2)
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param orderBeginTime 订单开始时间
	 * @param pageSize 每页显示数目
	 * @return
	 */
	public List<OrderPO> listPendingAcceptOrders2(long storeId, short status, Date orderBeginTime, int pageSize);

	/**
	 * 查询商家退款中订单列表
	 * 
	 * @modify Hins
	 * @date 2015年12月1日 下午4:53:44
	 * 
	 * @param storeId - 餐厅ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param refundTypes - 款申请对象类型(可多个，请参照枚举RefundType)
	 * @param status - 退款状态（可多个，请参照枚举RefundStatus）
	 * @param refundCreateTime - 退款申请时间
	 * @param pageSize - 每页大小
	 * @return
	 */
	public List<OrderPO> listStoreRefundOrders(long storeId, short payType, List<Short> refundTypes,
			List<Short> status, Date refundCreateTime, int pageSize);

	/**
	 * 查询待接单的订单, 被退回的展示在上面, 新提交的展示在下面 (版本3) 1, 退回的订单, 送达时间越早越靠前 2, 新提交订单, 下单时间越早越靠前
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param returnOrderBeginTime 被退回的订单的开始时间
	 * @param newOrderBeginTime 新单的订单开始时间
	 * @param pageSize 每页显示数目
	 * @return
	 * 
	 * @author felix
	 */
	public List<OrderPO> listPendingAcceptOrders3(long storeId, short status, Date returnOrderBeginTime, Date newOrderBeginTime,
			int pageSize);
	
	/**
	 * 根据门店内所有订单状态查询退回订单
	 * @param storeId 门店ID
	 * @param status 订单状态状态
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @return
	 * 
	 * @author felix
	 */
	public List<OrderPO> listIsBackPendingAcceptOrders(long storeId, short status, List<Short> payType);
	
	/**
	 * 根据订单状态、是否退回和订单开始时间查询订单，按订单开始时间升序排序
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param newOrderBeginTime 订单开始时间
	 * @param pageSize 每页订单数
	 * @return
	 * 
	 * @author felix
	 */
	public List<OrderPO> listNewPendingAcceptOrders(long storeId, short status, List<Short> payType, Date newOrderBeginTime, int pageSize);
	
	
	/**
	 * 分页查询订单状态在status列表下配送员id为deliverId的商家未完成的订单列表<br>
	 * 2015-12-09 modify by hins 只查询商家未确认送达的订单列表，按照sequence顺序排序
	 * 
	 * 
	 * <b>deliverId 配送员id 可以为空</b> <br>
	 * 该接口主要用于兼容 ‘待收货确认 订单列表’ 规则： 配送员只能看到自己配送的订单<br>
	 * 此方法查询主库
	 * 
	 * @param deliverId 配送员id,可以为null-->表示不做配送员id查询条件限制
	 * @param storeId - 商家ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param sequence - 订单序号
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<OrderPO> listOnlyStoreDelivery(Long deliverId, List<Short> payType, long storeId, Short pickType, long status, long sequence, int pageSize);

	/**
	 * 分页查询订单状态等于status，配送员ID为deliverId的商家已确认送达的订单列表<br>
	 * 按照sequence顺序排序
	 * 
	 * @author Hins
	 * @date 2015年12月9日 下午8:07:45
	 * 
	 * @param deliverId 配送员id,可以为null-->表示不做配送员id查询条件限制
	 * @param storeId - 商家ID
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param sequence - 订单序号
	 * @param pageSize - 分页大小
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listOnlyStoreConfirm(Long deliverId, long storeId, List<Short> payType, Short pickType, long status, long sequence, int pageSize);
	
	
	// --------------------------- 统计订单方法 start --------------------
	/**
	 * 根据订单状态获取 订单总数
	 * 
	 * @param statusEGT - 订单状态(大于或等于)
	 * @param statusELT - 订单状态(小于或等于)
	 * @param uid 用户id
	 * @return
	 */
	public int statUserOrdersCountByStatus(Short statusEGT, Short statusELT, long uid);

	/**
	 * 获取指定时间time 之前 状态为status、支付方式为payType的订单总数
	 * 
	 * 
	 * @param status 订单状态
	 * @param payType 支付方式
	 * @param time 指定查询时间
	 * @return 订单总数
	 * @author sky
	 */
	public int statCountByStatusAndPayTypeBeforeTime(short status, short payType, Date time);

	/**
	 * 统计商家的已结束的订单条数 统计条件：订单状态大于已配送和符合storeId的订单
	 * 
	 * @param storeId - 商家ID
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param payType - 订单支付类型列表（米星付版本新增加的）
	 * @return 返回符合的订单总数，若没有，返回0
	 */
	public int statStoreFinishOrder(long storeId, List<Short> status, List<Short> payType);

	/**
	 * 统计用户的历史订单条数
	 * 
	 * @param uid - 用户ID
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 *  payType - 订单支付类型列表（米星付版本新增加的）
	 * @return 返回符合的订单总数，若没有，返回0
	 */
	public int statUserOrder(long uid, List<Short> status, List<Short> payType);

	/**
	 * 统计门店指定日期最大的订单序列号
	 * 
	 * @author Hins
	 * @date 2015年9月6日 下午4:26:31
	 * 
	 * @param storeId - 门店ID
	 * @param serviceDay - 订单服务日期
	 * @return - 当天最大的订单序列号
	 */
	public int statMaxSerial(long storeId, int serviceDay);
	
	/** 
	 * 订单统计, 此方法统计下列数据
	 *	1, 当天现金支付订单数
	 *	2, 当天完成线上支付订单数
	 *
	 * @param start 一天开始时间
	 * @param end 一天结束时间
	 * @param payType 支付类型 线上支付/现金支付 (可为null, null默认查询所有支付类型)
	 * @param status 查询的状态列表 (可为null, null默认查询所有订单状态)
	 * @return
	 */
	public int statOrderPayType(Date start, Date end, List<Short> payType, List<Short> status);
	
	/**
	 * 订单统计, 此方法统计下列数据
	 * 1, 一天内完成线上支付或使用现金支付的订单总金额
	 * 
	 * @param start 一天开始时间
	 * @param end 一天结束时间
	 * @param payType 支付类型 线上支付/现金支付 (可为null, null默认查询所有支付类型)
	 * @param status 查询的状态列表 (可为null, null默认查询所有订单状态)
	 * @return
	 */
	public long statOrderFeeSum(Date start, Date end, List<Short> payType, List<Short> status);
	
	/**
	 * 订单统计, 一天内未选择支付方式的订单(未支付)
	 * @param start 一天开始时间
	 * @param end 一天结束时间
	 * @param payType 支付类型 线上支付/现金支付
	 * @return
	 */
	public int statOrderWithoutPayType(Date start, Date end);
	
	// --------------------------- 统计订单方法 end --------------------
	
	/**
	 * 查询某时间段内，已完成的订单数量和费用<br>
	 * 返回值：totalNumber-订单总数量；totalFee-订单总费用
	 * 
	 * @author Hins
	 * @date 2015年11月19日 上午10:49:23
	 * 
	 * @param start - 开始查询时间
	 * @param end - 结束查询时间
	 * @param status - 订单状态
	 * @param payType 支付类型(不允许empty)
	 * @return
	 */
	Map<String, Object> statFinishTotal(Date start, Date end, List<Short> status, List<Short> payType);
	
	/**
	 * 分页获取指定时间time 之前 状态为status、支付方式为payType的订单<br>
	 * 
	 * 如: time时间之前的未支付(线上支付方式)的订单
	 * 
	 * @param status 订单状态
	 * @param payType 支付方式
	 * @param offset 分页下标
	 * @param pageSize 分页大小
	 * @param time 指定查询时间
	 * @return
	 * @author sky
	 */
	public List<OrderPO> listByStatusAndPayTypeBeforeTime(short status, short payType, int offset, int pageSize, Date time);
	
	/**
	 * 获取交易中订单(线上支付)的总金额, 交易中为:已接单、制作中、待收货
	 * 
	 * @param storeId 门店ID
	 * @param payType 支付类型
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param status 状态
	 * @return
	 * 
	 * @author felix
	 * @date 2015-11-27
	 */
	long statDealingOrderAmount(long storeId, short payType, Date start, Date end, List<Short> status);
	
	
	/**
	 * 判断门店下是否有指定状态的订单
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表
	 * @return
	 */
	public OrderPO getIfHasOrderInStatus(long storeId, List<Short> status);

	/**
	 *
	 * 分页获取所有订单
	 *
	 * @author ben
	 * @date 2015年11月25日 下午4:53:41
	 * @param uid 下单的用户ID，可为0
	 * @param storeId 餐厅ID，可以为0，和uid可以同时生效
	 * @param payType 支付类型（米星付和其他的订单分开查询和展示 2016/7/13 add by hins）
	 * @param offset 起始位置
	 * @param pageSize 页长
	 * @return 所有订单列表
	 */
	List<OrderPO> listAllOrders(long uid, long storeId, List<Short> payType, int offset, int pageSize);

	/**
	 * 根据条件统计餐厅的金额、订单总数
	 * @param storeId 餐厅ID
	 * @param start 开始时间，根据create_time查询
	 * @param end 结束时间，根据 create_time 查询，范围 [start, end]
	 * @param status 指定要查询的状态列表，可以为null
	 * @param payType 支付类型，如果为0，不作限制
	 * @return 返回：totalFee=订单总额(long), num=订单数量
	 * @author ten, 2015/12/17
	 */
	Map<String, Object> statStoreOrderByCondition(long storeId, Date start, Date end,
												  List<Short> status, short payType);
	

	/**
	 * 根据用户IMID 查询订单
	 * @param imId 用户IMID
	 * @param status 订单状态集
	 * @param storeId 门店ID
	 * @return
	 */
	public List<OrderPO> listUserOngoingByImId(long imId, List<Short> status, long storeId, Long orderNo);

	/**
	 * 获取交易中订单(线上支付)的实际支付金额, 交易中为:已接单、制作中、待收货
	 * 此方法只读从库
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 已接单、制作中、待收货
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式
	 * @return
	 * 
	 * @author felix
	 */
	public long sumDealingActualFee(long storeId, List<Short> status, Date start, Date end, short payType);
	
	/**
	 * 统计餐厅的销售排行榜
	 * @param startTime 开始时间，从这个时间点开始统计（包含startTime）
	 * @param payType 支付类型
	 * @return 返回统计的数据: storeId,storeName,totalFee,num，按totalFee倒序排列
	 * @author ten 2016/2/29
	 */
	List<Map<String, Object>> statStoreSaleRank(Date startTime, short payType);

	/**
	 * 统计美食的销售排行榜
	 * @param startTime 开始时间，从这个时间点开始统计（包含startTime）
	 * @param payType 支付类型
	 * @return 返回统计的数据: dishKey,totalFee,num，按totalFee倒序排列
	 * @author ten 2016/2/29
	 */
	List<Map<String, Object>> statDishSaleRank(Date startTime, short payType);

	/**
	 * 统计指定支付类型每个月的销售额、订单数，从 2016-01-01 开始，
	 * 仅统计在线支付的金额，并且status=(35,40)的订单 {@link com.yogu.core.enums.order.OrderStatus}
	 * 2016/7/13 add by hins 为了http://w.mazing.com/www/index.php?m=story&f=view&storyID=163
	 * 增加一个订单金额字段，排除订单金额<=totalFee的
	 * 
	 * @param payType - 支付类型 {@link com.yogu.core.enums.pay.PayType}
	 * @param time1 - 订单创建的开始时间
	 * @param time2 - 订单创建的结束时间
	 * @return 返回统计的数据: month,total（分）,num,按month从小到大排列
	 * @author ten 2016/3/3
	 */
	List<Map<String, Object>> statOrderByMonth(List<Short> payType, int totalFee, String time1,String time2);
	
	/**
	 * 统计总的优惠券数据，从 2016-01-01 开始，仅统计在线支付的金额，并且status=(35,40)的数据
	 * @return 返回统计的数据:coupon_bear_type, total, discount, num, 按coupon_bear_type从小到大排列
	 * @author jack 2016/4/6
	 */
	List<Map<String, Object>> statTotalCoupon(List<Short> payType, String endTime);
	
	/**
	 * 统计每月的优惠券数据，从 2016-01-01 开始，仅统计在线支付的金额，并且status=(35,40)的数据
	 * 2016/7/14 add by hins 为了区分米星付/线上支付，分开统计。限制了查询时间段
	 * 
	 * @return 返回统计的数据:month, coupon_bear_type, total, discount, num，按month从小到大排列
	 * @author jack 2016/4/6
	 */
	List<Map<String, Object>> statCouponByMonth(List<Short> payType, String time1,String time2);
	
	/**
	 * 统计指定支付类型最近9周的销售额、订单数，从 2016-01-01 开始<br>
	 * 2016/7/14 hins 修改了jack的方法
	 * 2016/7/13 add by hins 为了http://w.mazing.com/www/index.php?m=story&f=view&storyID=163
	 * 增加一个订单金额字段，排除订单金额<=totalFee的
	 * 
	 * @param payType - 支付类型
	 * @param time1 - 订单创建的开始时间
	 * @param time2 - 订单创建的结束时间
	 * @author hins
	 * @date 2016年7月14日 下午4:26:45
	 * @return 每周的数据列表明若无，返回empty
	 */
	List<OrderByDateVO> statOrderByWeek(List<Short> payType, int totalFee, String time1,String time2);
	
	/**
	 * 查询指定时间范围内的餐厅已完成的订单数据
	 * @param storeId 门店id
	 * @param startTime 导出开始时间
	 * @param endTime 导出结束时间
	 * @return 返回统计的数据：{@link com.yogu.services.order.base.dto.vo.OrderForExcelVO}
	 * @author jack 2016/3/11
	 * @param payType 
	 */
	List<OrderForExcelVO> listOrderByStoreIdAndTime(long storeId, Date startTime, Date endTime, short payType);
	
	/**
	 * 查询用户进行中的订单总数
	 * @param uid 用户id
	 * @param status 订单状态列表
	 * @return 返回符合条件订单的总数
	 * @author jack 2016-05-05
	 */
	public int statUserOngoingOrderCount(long uid,  List<Short> payType, List<Short> status);
	
	/**
	 * 统计门店在某状态下的订单数
	 * @param storeId 餐厅iD
	 * @param status 状态
	 * @return
	 */
	int countStoreOrders(long storeId, short status);
	
	/**
	 * 获取餐厅在某个时间内需要完成的订单数
	 * 
	 * @param storeId 餐厅ID
	 * @param status 状态列表 数组形式
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	int countStoreOrdersByDeliveryTime(long storeId, short[] status, Date startTime, Date endTime);
	
	/**
	 * 根据订单id，查询订单<br>
	 * 方法支持批量查询，并且不排序
	 * 
	 * @param orderIds 订单id，可多个
	 * @author hins
	 * @date 2016年7月12日 下午6:10:03
	 * @return 订单列表，若无，返回empty
	 */
	List<OrderPO> listByOrderIds(List<Long> orderIds);
	
	/**
	 * 根据订单id，统计实付金额<br>
	 * 此方法暂时用于统计进行中订单退款金额
	 * 
	 * @param orderIds - 订单id
	 * @param payType - 支付类型
	 * @author hins
	 * @date 2016年8月19日 下午3:19:31
	 * @return 实付金额，单位分
	 */
	long stateActualFeeByOrderIds(List<Long> orderIds, short payType);
	
	/**
	 * 根据订单id和承担方类型，统计优惠金额
	 * 
	 * @param orderIds - 订单id
	 * @param payType - 支付类型
	 * @param couponBearType - 优惠券承担方类型
	 * @author hins
	 * @date 2016年8月19日 下午3:26:25
	 * @return 优惠金额，单位分
	 */
	long stateCouponFeeByOrderIds(List<Long> orderIds, short payType, short couponBearType);
	
	/**
	 * 统计指定收货时间内、订单状态、餐厅、支付类型的应付订单金额<br>
	 * 方法暂时用于：统计餐厅已完成订单（线上/米星付）的应付金额 2016/8/18 modify by hins
	 * 
	 * @param storeId 门店ID
	 * @param status 状态
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付类型
	 * @author hins
	 * @date 2016年9月20日 下午7:02:32
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> statSettlingTotalFee(long[] storeId, List<Short> status, Date start, Date end, Short payType);
	
	/**
	 * 统计指定收货时间内、订单状态、餐厅、支付类型的实付订单金额<br>
	 * 方法暂时用于：统计餐厅已完成订单（线上/米星付）的实付金额 2016/8/18 modify by hins
	 * 
	 * @param storeId 门店ID
	 * @param payType 支付类型
	 * @param start 确认收货开始时间
	 * @param end 确认收货结束时间
	 * @param status 订单状态
	 * @author hins
	 * @date 2016年9月20日 下午7:03:23
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> statSettlingActualFee(long[] storeId, List<Short> status, Date start, Date end, Short payType);
	
	/**
	 * 获取交易中订单(线上支付)的总金额, 交易中为:已接单、制作中、待收货
	 * 
	 * @param storeId 门店ID
	 * @param payType 支付类型
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param status 状态
	 * @return
	 * 
	 * @author felix
	 * @date 2015-11-27
	 */
	public List<Map<String, Object>> statDealingOrderTotalFee(long[] storeId, short payType, Date start, Date end, List<Short> status);
	
	/**
	 * 获取交易中订单(线上支付)的实际支付金额, 交易中为:已接单、制作中、待收货
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 已接单、制作中、待收货
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式
	 * @return
	 * 
	 * @author felix
	 */
	public List<Map<String, Object>> statDealingActualFee(long[] storeId, List<Short> status, Date start, Date end, short payType);
	
	/**
	 * 获取指定开始时间，订单状态，优惠券承担方，支付类型，商家的优惠总费用
	 * 2016/9/20 add by hins：该方法是查询订单开始时间！！此方法只用于对账统计 - 切记
	 * 
	 * @param storeId - 餐厅id
	 * @param status - 订单状态
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式（可空）
	 * @param couponBearType 优惠券承担方类型
	 * @author hins
	 * @date 2016年9月20日 下午4:21:34
	 * @return 返回统计的数据: storeId,discountFee
	 */
	List<Map<String, Object>> statCouponFeeByInStoreOrderBegin(List<Long> storeId, List<Short> status, Date start, Date end, Short payType, short couponBearType);
	
	/**
	 * 获取指定开始时间，订单状态，优惠券承担方，支付类型，商家的优惠总费用
	 * 2016/9/20 add by hins：该方法是查询订单完成时间！！此方法只用于对账统计 - 切记
	 * 
	 * @param storeId - 餐厅id
	 * @param status - 订单状态
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式（可空）
	 * @param couponBearType 优惠券承担方类型
	 * @author hins
	 * @date 2016年9月20日 下午6:19:37
	 * @return 返回统计的数据: storeId,discountFee
	 */
	List<Map<String, Object>> statCouponFeeByStoreFinish(List<Long> storeId, List<Short> status, Date start, Date end, Short payType, short couponBearType);
	
	/**
	 * 获取指定开始时间，订单状态，优惠券承担方，支付类型，商家的优惠总费用
	 * 2016/9/20 add by hins：该方法是查询订单开始时间！！此方法只用于对账统计 - 切记
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 已接单、制作中、待收货
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式
	 * @return
	 * 
	 * @author felix
	 */
	public long sumDealingDiscountFee(long storeId, List<Short> status, Date start, Date end, short payType, short couponBearType);
	
	/**
	 * 根据订单开始时间（即支付时间）、订单状态、支付类型获取餐厅所有的订单<br>
	 * 不分页，不排序，该方法用于生成每日报表，谨慎使用
	 * 
	 * @param storeId - 餐厅id
	 * @param begin - 订单开始时间（开始值）
	 * @param end - 订单开始时间（结束值）
	 * @param status - 订单状态
	 * @param payType - 支付类型
	 * @author hins
	 * @date 2016年9月20日 上午11:08:21
	 * @return 符合的数据列表，若无，返回empty list
	 */
	public List<OrderPO> listByStoreBeginTimeAndStatus(long storeId, Date begin, Date end, List<Short> status, List<Short> payType);
	
	/**
	 * 获取指定开始时间，订单状态，优惠券承担方，支付类型，商家的优惠总费用
	 * 
	 * @param storeId - 餐厅id
	 * @param status - 订单状态
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式（可空）
	 * @param couponBearType 优惠券承担方类型
	 * @author hins
	 * @date 2016年9月27日 下午7:55:07
	 * @return long
	 */
	long statCouponFeeByStoreOrderBegin(long storeId, List<Short> status, Date start, Date end, Short payType, short couponBearType);
	
	
}

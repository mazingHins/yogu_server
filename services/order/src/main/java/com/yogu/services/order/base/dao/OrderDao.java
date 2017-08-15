package com.yogu.services.order.base.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yogu.services.order.base.dao.param.ChangePayTypePOJO;
import com.yogu.services.order.base.dao.param.UpdateOrderPayPOJO;
import com.yogu.services.order.base.dao.param.UpdateOrderToUserConfirmByMazingPayPOJO;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.entry.TinyOrderPO;
import com.yogu.services.order.constants.OrderSearchCategory;

/**
 * mz_order 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface OrderDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(OrderPO po);

	// --------------------------- 获取单个订单对象查询方法 start --------------------
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

	// --------------------------- 获取单个订单对象查询方法 end--------------------

	// --------------------------- 修改订单方法 start --------------------
	/**
	 * 更新支付类型 暂时用于货到付款订单，执行结果更新订单状态为待结单。<br>
	 * 方法用于现金支付。
	 * 
	 * @param po - 修改支付类型的轻量化orderPO对象
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updatePayTypeByCash(ChangePayTypePOJO po);

	/**
	 * 更新订单评论
	 * 
	 * @param orderId - 订单ID
	 * @param commentId - 评论ID
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateCommentOrder(long orderId, long commentId, short thisStatus, short status);

	/**
	 * 更新订单新状态
	 * 
	 * @param orderId - 订单ID
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateByParams(long orderId, short thisStatus, short status);

	/**
	 * 更新订单状态为退款
	 * 
	 * @author Hins
	 * @date 2016年1月19日 下午8:25:10
	 * 
	 * @param orderId - 订单ID
	 * @param thisStatus - 订单当前状态（查询条件）
	 * @param newStatus - 订单新状态
	 * @param rejectRemark - 退款原因（可空）
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateToRefund(long orderId, short thisStatus, short newStatus, String rejectRemark);

	/**
	 * 同时更新订单的状态、配送员
	 * 
	 * @param orderId - 订单ID
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @param deliverId 配送员id
	 * @param isStoreExpress 是否商家自行配送
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 * @author sky 2015/11/10
	 */
	public int updateStatusAndDeliverId(long orderId, short thisStatus, short status, long deliverId, short isStoreExpress);

	/**
	 * 更新订单状态。<br>
	 * 方法操作：修改订单状态，修改时间
	 * @param orderId - 订单id
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @author hins
	 * @date 2016年10月11日 下午3:15:11
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateStatus(long orderId, short thisStatus, short status);

	/**
	 * 退款成功，更新订单状态和完成时间
	 * 
	 * @author Hins
	 * @date 2015年11月7日 下午4:35:20
	 * 
	 * @param orderId - 订单ID
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateRefundSunccess(long orderId, short thisStatus, short status);

	/**
	 * 修改用户确认收货
	 * 
	 * @author Hins
	 * @date 2015年10月13日 下午4:22:51
	 * 
	 * @param orderId - 订单ID
	 * @param thisStatus - 订单旧状态（查询条件）
	 * @param status - 订单状态（修改状态）
	 * @param time - 收货时间
	 * @param finishTime - 订单完成时间
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateUserConfirm(long orderId, short thisStatus, short status, Date time, Date finishTime);

	/**
	 * 修改用户确认收货<br>
	 * 方法只提供给米星付支付成功后调用，请谨慎使用<br>
	 * 因为会修改sequence，orderBeginTime
	 * @param pojo
	 * @author hins
	 * @date 2016年6月16日 下午5:02:21
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateUserConfirmByMazingPay(UpdateOrderToUserConfirmByMazingPayPOJO pojo);

	/**
	 * 定时任务自动确认收货
	 * @param orderId 订单ID
	 * @param thisStatus 修改前状态
	 * @param status 修改后状态
	 * @param time 更新时间
	 * @param finishTime
	 * @param sysConfirm
	 * @return
	 */
	public int updateAutoConfirm(long orderId, short thisStatus, short status, Date time, short sysConfirm);

	/**
	 * 更新订单为支付状态
	 * 
	 * @param orderId - 订单ID
	 * @param payNo - 支付流水号
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int payOrder(ChangePayTypePOJO po);

	/**
	 * 修改订单支付流水号，支付类型，支付方式，修改时间<br>
	 * 执行：实际支付金额=订单金额-discountFee；优惠金额=discountFee；modify by hins 先这样简单的修改实际支付金额和优惠金额吧！如果以后加上其他优惠再说
	 * 该方法不验证(订单金额-discountFee是否小于0！！！！)
	 * @author Hins
	 * @date 2015年8月18日 下午4:42:32
	 * 
	 * @param pojo - 修改对象
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updatePayNo(UpdateOrderPayPOJO pojo);

	/**
	 * 修改订单支付流水，订单状态，支付方式，支付类型，优惠金额，实际支付金额，修改时间<br>
	 * 执行：实际支付金额不执行(totalFee-discountFee)!!因为可能涉及到更换优惠券，已经计算的实付金额很难再用sql语句计算；<br>
	 * 该方法现在主要用于：线上支付订单，且使用优惠金额>订单金额，不需要支付
	 * 
	 * @author Hins
	 * @date 2015年12月31日 下午4:57:08
	 * 
	 * @param pojo - 修改对象
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updatePayNoAndSuccess(UpdateOrderPayPOJO pojo);

	/**
	 * 增加订单的打印次数，执行语句set 打印次数=原打印次数+1
	 * 
	 * @author Hins
	 * @date 2015年9月5日 下午9:55:57
	 * 
	 * @param orderId - 订单ID
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int addPrint(long orderId);

	/**
	 * 商家确认收货，修改商家确认收货时间。finishTime订单完成时间非必传，若传了，表示订单已完成
	 * 
	 * @author Hins
	 * @date 2015年9月7日 下午4:29:27
	 * 
	 * @param orderId - 订单ID
	 * @param time - 收货时间
	 * @param finishTime - 订单完成时间（可空）
	 * @return - 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateStoreConfirmTime(long orderId, Date time, Date finishTime);

	/**
	 * 商家拒绝订单,并记录拒单备注
	 * 
	 * @author Felix
	 * @date 2015年9月7日
	 * 
	 * @param orderId - 订单Id
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @param rejectComment - 拒单备注
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateRejectStatus(long orderId, short thisStatus, short status, String rejectComment);

	/**
	 * 商家退回订单时的更新操作
	 * 
	 * @author Felix
	 * @date 2015年10月13日 下午1:38:00
	 * 
	 * @param orderId 订单ID
	 * @param prevStatus 更改前订单状态
	 * @param curStatus 更改后订单状态
	 * @param backTime 退回时间
	 * @param storeConfirmTime 商家确认送达时间（若时间为空，则将商家确认送达时间清空）
	 * @return 更新操作数
	 */
	public int updateStoreReturn(long orderId, short prevStatus, short curStatus, Date backTime, Date storeConfirmTime);

	/**
	 * 更新订单状态成取消，并记录取消原因
	 * 
	 * @author Hins
	 * @date 2015年11月23日 下午8:51:07
	 * 
	 * @param orderId - 订单ID
	 * @param thisStatus - 订单当前状态
	 * @param status - 订单新状态
	 * @param cancelSource - 取消的操作来源：1-用户取消；2-商家取消；3-米星管理员取消；4-定时任务取消(取值范围参考枚举：CancelOrderSource)
	 * @param rejectRemark - 取消原因（可以为空）
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateToCancel(long orderId, short thisStatus, short status, short cancelSource, String rejectRemark);

	// --------------------------- 修改订单方法 end--------------------

	// --------------------------- 查询统计订单方法 start --------------------
	/**
	 * 根据订单状态获取 订单总数<br>
	 * 此方法查询主库
	 * 
	 * @param statusEGT - 订单状态(大于或等于)
	 * @param statusELT - 订单状态(小于或等于)
	 * @param uid 用户id
	 * @return
	 */
	public int statUserOrdersCountByStatus(Short statusEGT, Short statusELT, long uid);

	/**
	 * 获取指定时间time 之前 状态为status、支付方式为payType的订单总数<br>
	 * 此方法查询主库<br>
	 * 2016/9/13 modify by hins：新增参数endTime，可以查询指定时间的数据，删除了之前指定支付类型的参数（因为支付类型不起作用，不参与查询）
	 * 
	 * @param status 订单状态
	 * @param startTime 创建时间-开始
	 * @param endTime 创建时间-结束
	 * @return 订单总数
	 * @author sky
	 */
	public int statCountByStatusAndPayTypeBeforeTime(short status, Date startTime, Date endTime);

	/**
	 * 统计商家的已结束的订单条数 统计条件：订单状态大于已配送和符合storeId的订单<br>
	 * 此方法查询主库
	 * 
	 * @param storeId - 商家ID
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @return 返回符合的订单总数，若没有，返回0
	 */
	public int statStoreFinishOrder(long storeId, List<Short> status);

	/**
	 * 统计用户的历史订单条数<br>
	 * 此方法查询主库
	 * 
	 * @param uid - 用户ID
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @return 返回符合的订单总数，若没有，返回0
	 */
	public int statUserOrder(long uid, List<Short> status);

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

	// --------------------------- 查询统计订单方法 end --------------------

	// --------------------------- 查询订单列表方法 start--------------------

	/**
	 * 根据 商家ID 、订单状态查询符合条件的订单列表，用于后台管理系统<br>
	 * 此方法查询主库
	 * 
	 * @param storeId 商家ID
	 * @param status 订单状态，可以为null，为null时查询所有状态的订单
	 * @param offset 起始记录
	 * @param pageSize 最大返回查询多少条数据
	 * @return 返回符合条件的记录，以创建时间倒序排列
	 */
	List<OrderPO> listOrderByStoreId(long storeId, Short status, int offset, int pageSize);

	/**
	 * 查询商家确认收货时间在某时间段内&&订单状态等于status的列表。<br>
	 * 此方法查询主库
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
	 * 查询预计送达时间超过time时间，且订单状态等于status。<br>
	 * 此方法查询主库
	 * 
	 * @author Hins
	 * @date 2015年11月6日 下午7:46:47
	 * 
	 * @param start - 超时时间绝对值(预计送达时间大于time)，开始时间
	 * @param end - 超时时间绝对值(预计送达时间大于time)，结束时间
	 * @param status - 订单状态
	 * @param list -支付类型
	 * @return
	 */
	public List<OrderPO> listStatusAndTimeOut(Date start, Date end, short status, List<Short> list);

	/**
	 * 查询预计送达时间超过time时间，且订单状态在status列表内。<br>
	 * 
	 * @param start - 超时时间绝对值(预计送达时间大于time)，开始时间
	 * @param end - 超时时间绝对值(预计送达时间大于time)，结束时间
	 * @param status - 订单状态
	 * @return
	 */
	public List<OrderPO> listInStatusAndTimeOut(Date start, Date end, List<Short> status);

	/**
	 * 查询订单开始时间超过time时间，且订单状态等于status的列表
	 * 
	 * @param start - 超时时间绝对值(预计送达时间大于time)，开始时间
	 * @param end - 超时时间绝对值(预计送达时间大于time)，结束时间
	 * @param status - 订单状态
	 * @return
	 */
	public List<OrderPO> listByStatusAndBeginTimeOut(Date start, Date end, short status);

	/**
	 * 根据商家 ID、配送方式、订单状态 查询符合条件的 商家订单列表, 用于商家订单的管理和查询<br>
	 * 此方法查询主库
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
	List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus(long storeId, Short pickType, Short status, int offset, int pageSize, String orderBy);

	/**
	 * 根据商家 ID、配送方式、订单状态 查询符合条件的 商家订单列表, 用于商家订单的管理和查询 (兼容版本)<br>
	 * 此方法查询主库
	 * 
	 * @param storeId 商家ID
	 * @param pickType 配送方式,可以为null,为null表示查询所有配送方式
	 * @param status 订单状态,为null表示查询所有状态
	 * @param sequence 订单序列号
	 * @param pageSize 最大返回查询多少条数据
	 * @param orderBy 排序字段
	 * @return
	 */
	List<OrderPO> listOrdersByStoreIdAndPickTypeWithStatus2(long storeId, Short pickType, Short status, long sequence, int pageSize, String orderBy);

	/**
	 * 分页查询订单状态在status列表下的商家完成的订单列表。<br>
	 * 按照完成时间倒序排序<br>
	 * 此方法查询主库
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
	 * 分页查询订单状态在status列表下的商家完成的订单列表。<br>
	 * 查询指定支付类型的订单，按照创建时间倒序排序<br>
	 * 此方法查询主库
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
	 * 分页查询订单状态在status列表下的商家未完成的订单列表。<br>
	 * 此方法查询主库
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
	@Deprecated
	public List<OrderPO> listStoreOngoing(long storeId, Short pickType, List<Short> status, int offset, int pageSize);

	/**
	 * 分页查询订单状态在status列表下配送员id为deliverId的商家未完成的订单列表<br>
	 * 
	 * <b>deliverId 配送员id 可以为空</b> <br>
	 * 
	 * 该接口主要用于兼容 ‘待收货确认 订单列表’ 规则： 配送员只能看到自己配送的订单<br>
	 * 此方法查询主库
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
	public List<OrderPO> listStoreOngoingOrdersByDeliverId(Long deliverId, long storeId, Short pickType, List<Short> status, int offset, int pageSize);

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
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param sequence - 订单序号
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<OrderPO> listOnlyStoreDelivery(Long deliverId, long storeId, Short pickType, long status, long sequence, int pageSize);

	/**
	 * 分页查询订单状态等于status，配送员ID为deliverId的商家已确认送达的订单列表<br>
	 * 按照sequence顺序排序
	 * 
	 * @author Hins
	 * @date 2015年12月9日 下午8:07:45
	 * 
	 * @param deliverId 配送员id,可以为null-->表示不做配送员id查询条件限制
	 * @param storeId - 商家ID
	 * @param pickType - 提现方式（可不传，若传递，则作为查询条件）
	 * @param status - 订单状态列表（参照OrderStatus枚举）
	 * @param sequence - 订单序号
	 * @param pageSize - 分页大小
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listOnlyStoreConfirm(Long deliverId, long storeId, Short pickType, long status, long sequence, int pageSize);

	/**
	 * 商家按搜索类别搜索订单<br>
	 * 此方法查询主库
	 * 
	 * @author Felix
	 * @date 2015年9月9日
	 * 
	 * @param storeId - 商家ID
	 * @param content - 搜索内容
	 * @param category - 搜索类别
	 * @return 订单列表，若无，返回empty
	 */
	public List<OrderPO> listByField(long storeId, String content, OrderSearchCategory category);

	/**
	 * 用户查询历史订单<br>
	 * 此方法查询主库
	 * 
	 * @author Hins
	 * @date 2015年9月1日 下午3:40:56
	 * 
	 * @param uid - 用户ID
	 * @param status - 订单状态列表
	 * @param offset - 分页下标
	 * @param pageSize - 分页大小
	 * @return
	 */
	public List<OrderPO> listUserHistory(long uid, List<Short> status, int offset, int pageSize);

	/**
	 * 根据条件查询记录，不排序<br>
	 * 此方法查询主库
	 * 
	 * @param uid - 用户ID
	 * @param status - 订单状态列表
	 * @param storeId - 订单所属门店ID(非必传)
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderPO> listUserOngoing(long uid, List<Short> status, Long storeId);

	/**
	 * 根据用户IMID 查询订单
	 * @param imId 用户IMID
	 * @param status 订单状态集
	 * @param storeId 门店ID
	 * @return
	 */
	public List<OrderPO> listUserOngoingByImId(long imId, List<Short> status, long storeId, Long orderNo);

	/**
	 * 分页获取指定时间time 之前 状态为status、支付方式为payType的订单<br>
	 * 
	 * 如: time时间之前的未支付(线上支付方式)的订单<br>
	 * 此方法查询主库<br>
	 * 2016/9/13 modify by hins：新增参数endTime，可以查询指定时间的数据，删除了之前指定支付类型的参数（因为支付类型不起作用，不参与查询）
	 * 
	 * @param status 订单状态
	 * @param offset 分页下标
	 * @param pageSize 分页大小
	 * @param time 指定查询时间
	 * @return
	 * @author sky
	 */
	public List<OrderPO> listByStatusAndPayTypeBeforeTime(short status, int offset, int pageSize, Date startTime, Date endTime);

	/**
	 * 根据订单状态 和 订单创建时间 的范围 获取数据<br>
	 * 此方法查询主库
	 * 
	 * @param status 订单状态
	 * @param time 指定的订单创建时间
	 * @return
	 * @author felix
	 */
	public List<TinyOrderPO> listByStatusAndCreateTime(short status, Date time);

	/**
	 * 查询待接单的订单, 被退回的展示在上面, 新提交的展示在下面 1, 退回的订单, 送达时间越早越靠前 2, 新提交订单, 下单时间越早越靠前<br>
	 * 此方法查询主库
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param offset 下标偏移量
	 * @param pageSize 每页显示数目
	 * @return
	 */
	@Deprecated // by ten 2015/11/30，早应该标识，这里补上
	public List<OrderPO> listPendingAcceptOrders(long storeId, short status, int offset, int pageSize);

	/**
	 * 查询待接单的订单(版本2)<br>
	 * 此方法查询主库
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param orderBeginTime 订单开始时间
	 * @param pageSize 每页显示数目
	 * @return
	 */
	public List<OrderPO> listPendingAcceptOrders2(long storeId, short status, Date orderBeginTime, int pageSize);

	/**
	 * 查询商家退款中订单列表<br>
	 * 此方法查询主库
	 * 
	 * @modify Hins
	 * @date 2015年12月1日 下午4:53:44
	 * 
	 * @param storeId - 餐厅ID
	 * @param refundTypes - 款申请对象类型(可多个，请参照枚举RefundType)
	 * @param status - 退款状态（可多个，请参照枚举RefundStatus）
	 * @param refundCreateTime - 退款申请时间
	 * @param pageSize - 每页大小
	 * @return
	 */
	public List<OrderPO> listStoreRefundOrders(long storeId, List<Short> refundTypes, List<Short> status, Date refundCreateTime, int pageSize);

	/**
	 * 查询待接单的订单, 被退回的展示在上面, 新提交的展示在下面 (版本3) 1, 退回的订单, 送达时间越早越靠前 2, 新提交订单, 下单时间越早越靠前<br>
	 * 此方法读取主库
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
	public List<OrderPO> listPendingAcceptOrders3(long storeId, short status, Date returnOrderBeginTime, Date newOrderBeginTime, int pageSize);

	/**
	 * 判断门店下是否有指定状态的订单<br>
	 * 此方法读取主库
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表
	 * @return
	 */
	public OrderPO getIfHasOrderInStatus(long storeId, List<Short> status);

	/**
	 * 根据门店内所有订单状态查询退回订单<br>
	 * 此方法查询主库
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态状态
	 * @return
	 * 
	 * @author felix
	 */
	public List<OrderPO> listIsBackPendingAcceptOrders(long storeId, short status);

	/**
	 * 根据订单状态、是否退回和订单开始时间查询订单，按订单开始时间升序排序<br>
	 * 此方法查询主库
	 * 
	 * @param storeId 门店ID
	 * @param status 订单状态
	 * @param newOrderBeginTime 订单开始时间
	 * @param pageSize 每页订单数
	 * @return
	 * 
	 * @author felix
	 */
	public List<OrderPO> listNewPendingAcceptOrders(long storeId, short status, Date newOrderBeginTime, int pageSize);

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
	 * 分页查询所有订单, 此方法用于修复IMID数据, 限制使用
	 * 
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Deprecated
	public List<OrderPO> listByPage(int offset, int pageSize);

	/**
	 * 根据订单开始时间（即支付时间）、订单状态、支付类型获取所有的订单<br>
	 * 不分页，不排序，该方法用于生成每日报表，谨慎使用
	 * 
	 * @param storeId - 餐厅id，支持批量
	 * @param begin - 订单开始时间（开始值）
	 * @param end - 订单开始时间（结束值）
	 * @param status - 订单状态
	 * @param payType - 支付类型
	 * @author hins
	 * @date 2016年9月19日 下午6:06:13
	 * @return 符合的数据列表，若无，返回empty list
	 */
	public List<OrderPO> listByBeginTimeAndStatus(List<Long> storeIds, Date begin, Date end, List<Short> status, List<Short> payType);

	/**
	 * 根据服务日期查询所有已开始的订单<br>
	 * 不分页，不排序
	 * 该方法用户修复报表数据，谨慎使用
	 * 
	 * @author Hins
	 * @date 2015年12月30日 下午2:38:59
	 * 
	 * @param serviceDay - 服务日期
	 * @return 符合的数据列表，若无，返回empty list
	 */
	public List<OrderPO> listByServiceDay(int serviceDay);

	/**
	 * 更新IMID数据, 限制使用
	 * 
	 * @param orderId
	 * @param imId
	 */
	@Deprecated
	public void updateImid(long orderId, long imId);
	// --------------------------- 查询订单方法 end --------------------

	/**
	 * 获取未到预计送达时间, 但时间已经过了一半的订单
	 * @param status 订单状态列表
	 * @param time 时间
	 * @param adhead 提前时间 单位秒
	 */
	public List<OrderPO> listOverMidDeliveryTime(List<Short> status, Date time, int ahead);

	/**
	 * 时间到了该配送的时间还未配送, 通知BD
	 * 比如订单预计20160303 12:00送达，配送需要2小时，那么20160303 10:00还未配送的话就要发通知
	 * 
	 * @param status 订单状态列表 (可为多个)
	 * @param time
	 * @return
	 */
	public List<OrderPO> listShouldHasDeliveredOrders(List<Short> status, Date time);

	/**
	 * 获取成功下单的用户id列表，并统计下单的餐厅总数
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<OrderPO> listSuccessOrderedUids(int offset, int pageSize);

	/**
	 * 获取用户成功订单的订单id列表
	 * @param uid
	 * @return
	 */
	public List<Long> listSuccessOrderIds(long uid);

	/**
	 * 查询指定的订单状态和订单完成时间段内的订单数据，结果无序<br>
	 * 此方法现在只提供给fixFinishPayBizStatus（修复指定时间到现在已收货/已完成的订单，在pay域支付记录bizStatus(从默认值修改成已确认收货，因为T+N任务的时候会验证支付记录的biz！！！)）使用
	 * 
	 * @param status - 订单状态
	 * @param finishTimeBegin - 完成时间的开始值
	 * @param finishTimeEnd - 完成时间的结束值
	 * @author hins
	 * @date 2016年9月1日 下午3:13:11
	 * @return 订单列表，若无，返回empty list
	 */
	@Deprecated
	public List<OrderPO> listByStatusAndFinishTime(List<Short> status, List<Short> payType, Date finishTimeBegin, Date finishTimeEnd);

	/**
	 * 修改商家备注
	 * 
	 * @param storeId - 门店id
	 * @param orderId - 订单id
	 * @param newStoreRemark - 新的备注
	 * @param oldStoreRemark - 旧的备注（查询条件）
	 * @author hins
	 * @date 2016年8月25日 下午6:09:41
	 * @return 更新行数
	 */
	public int updateStoreRemark(long storeId, long orderId, String newStoreRemark, String oldStoreRemark);

	/**
	 * 统计指定收货时间内、订单状态、餐厅、支付类型的应付订单金额<br>
	 * 方法暂时用于：统计餐厅已完成订单（线上/米星付）的应付金额 2016/8/18 modify by hins
	 * 
	 * @param storeId 门店ID
	 * @param payType 支付类型
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param status 状态
	 * 
	 * @author felix
	 * @date 2015-11-27
	 */
	long statSettlingTotalFee(long storeId, short payType, Date start, Date end, List<Short> status);

	/**
	 * 统计指定收货时间内、订单状态、餐厅、支付类型的实付订单金额<br>
	 * 方法暂时用于：统计餐厅已完成订单（线上/米星付）的实付金额 2016/8/18 modify by hins
	 * 
	 * @param storeId 门店ID
	 * @param payType 支付类型
	 * @param start 确认收货开始时间
	 * @param end 确认收货结束时间
	 * @param status 订单状态
	 * 
	 * @author hins
	 * @date 2016年8月18日 上午11:34:00
	 * @return long
	 */
	long statSettlingActualFee(long storeId, List<Short> status, Date start, Date end, short payType);

	/**
	 * 获取指定收货时间内、订单状态、餐厅、支付类型、优惠券承担方的优惠金额<br>
	 * 此方法只读从库
	 * 此方法只用于对账统计 - 切记，暂时用于：统计餐厅已完成（线上/米星付）的平台优惠金额
	 * 
	 * @param storeId 门店ID
	 * @param status 状态
	 * @param start 确认收货开始时间
	 * @param end 确认收货结束时间
	 * @param payType - 支付类型
	 * @param couponBearType - 优惠券承担方
	 * @author hins
	 * @date 2016年8月18日 上午11:41:30
	 * @return long
	 */
	long statSettlingDiscountFee(long storeId, List<Short> status, Date start, Date end, short payType, short couponBearType);

	/**
	 * 关联顺丰配送表，统计已完成订单finish_time在某个时间段内的用户承担配送费.
	 * 此方法只用于对账-切记
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 确认收货，评论
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式
	 * @param expressStatus 配送状态
	 * @author hins
	 * @date 2016年10月26日 下午2:39:39
	 * @return long
	 */
	long statSettlingUserDeliveryFee(long storeId, List<Short> status, Date start, Date end, short payType, List<Short> expressStatus);

	/**
	 * 关联顺丰配送表，统计已完成订单finish_time在某个时间段内的商家承担配送费.
	 * 此方法只用于对账-切记
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 确认收货，评论
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式
	 * @param expressStatus 配送状态
	 * @author hins
	 * @date 2016年10月26日 下午2:39:39
	 * @return long
	 */
	long statSettlingStoreDeliveryFee(long storeId, List<Short> status, Date start, Date end, short payType, List<Short> expressStatus);

	/**
	 * 获取交易中订单(线上支付)的总金额, 交易中为:已接单、制作中、待收货<br>
	 * 2016-11-14 modify by hins 内容：查询时间从原来支付时间->接单时间
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
	 * 获取交易中订单(线上支付)的实际支付金额, 交易中为:已接单、制作中、待收货<br>
	 * 2016-11-14 modify by hins 内容：查询时间从原来支付时间->接单时间
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
	 * 获取交易中订单(线上支付)的总平台优惠金额, 交易中为:已接单、制作中、待收货
	 * 此方法只读从库
	 * 此方法只用于对账统计 - 切记
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 已接单、制作中、待收货
	 * @param start 订单开始时间
	 * @param end 订单结束时间
	 * @param payType 支付方式
	 * @param couponBearType 优惠券承担方
	 * @return
	 * 
	 * @author felix
	 */
	public long sumDealingDiscountFee(long storeId, List<Short> status, Date start, Date end, short payType, short couponBearType);

	/**
	 * 批量获取餐厅交易中订单(线上支付)的总平台优惠金额, 交易中为:已接单、制作中、待收货<br>
	 * 2016-11-14 modify by hins 内容：查询时间从原来支付时间->接单时间
	 * 此方法只读从库
	 * 此方法只用于对账统计 - 切记
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 已接单、制作中、待收货
	 * @param start 订单开始时间
	 * @param end 订单结束时间
	 * @param payType 支付方式
	 * @param couponBearType 优惠券承担方
	 * @return
	 * @author hins
	 * @date 2016年11月4日 下午12:00:21
	 * @return key:
	 * 		   storeId    餐厅id
	 * 		   discountFee    优惠金额，单位分
	 */
	public List<Map<String, Object>> statCouponFeeInStoreOrderBegin(List<Long> storeId, List<Short> status, Date start, Date end, short payType, short couponBearType);

	/**
	 * 关联顺丰配送表，统计交易中订单order_begin_time在某个时间段内的用户承担配送费.<br>
	 * 2016-11-14 modify by hins 内容：查询时间从原来支付时间->接单时间
	 * 此方法只用于对账-切记
	 * 
	 * @param storeId 门店ID
	 * @param status 状态列表 - 已接单、制作中、待收货
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param payType 支付方式
	 * @param expressStatus 配送状态
	 * @author hins
	 * @date 2016年10月26日 下午2:39:39
	 * @return long
	 */
	public long sumDealingUserDeliveryFee(long storeId, List<Short> status, Date start, Date end, short payType, List<Short> expressStatus);

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
	 * 查询指定预计送达时间、第三方配送类型的列表，结果无序
	 * 
	 * @param beginTime - 预计送达开始时间
	 * @param endTime - 预计送达结束时间
	 * @param isThirdExpress - 是否第三方配送
	 * @param orderStatus - 订单状态
	 * @author hins
	 * @date 2016年10月28日 上午10:50:43
	 * @return 符合的数据列表，若无，返回empty list
	 */
	public List<OrderPO> listDeliveryTimeAndExpress(Date beginTime, Date endTime, short isThirdExpress, List<Short> orderStatus);
	/**
	 * 更新订单状态->接单
	 * @param orderId - 订单ID
	 * @param newStatus - 订单当前状态
	 * @param newStatus - 订单新状态
	 * @param acceptTime - 接单时间（不能null）
	 * @author hins
	 * @date 2016年11月14日 下午6:43:48
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updateToAccept(long orderId, short oldStatus, short newStatus, Date acceptTime);
	
}

/**
 * 
 */
package com.mazing.mysql;

import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class GenerateCode {

	/**
	 * 
	 * 生成认证模块中的餐厅资质证件和营业执照相关实体
	 *
	 * @author ben
	 * @date 2015年11月27日 上午11:19:28
	 * @throws Exception
	 */
	@Test
	public void generateStoreAudit() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		// merchant

		url = urlHost + "/mz_merchant?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// audit
		// tables = new String[] { "mz_store_audit_business_license", "mz_store_audit_catering_certification"};
		tables = new String[] { "mz_team_pay_goods"};
		pack = "com.mazing.services.store.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		System.out.println("all Done!");
	}

	/**
	 * 主函数，以Maven Test形式运行
	 */
	@Test
	public void generate() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		// config

		url = urlHost + "/mz_config?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_area", "mz_country_ext", "mz_config", "mz_id_gen", "mz_schedule_job", "mz_sensitive_word", "mz_find_pick", "mz_find_pick_tag", "mz_customize_pool", "mz_customize_pool_tag",
				"mz_version_upgrade", "mz_adver_activity", "mz_adver_bar", "mz_business_district" };
		pack = "com.mazing.services.config.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_filter_flavor", "mz_filter_operating", "mz_hot_search" };
		pack = "com.mazing.services.config.search";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// merchant

		url = urlHost + "/mz_merchant?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_store", "mz_store_details", "mz_store_details_pic", "mz_store_news"//
				, "mz_store_pic", "mz_store_service_range", "mz_store_service_range_track" };
		pack = "com.mazing.services.store.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		tables = new String[] { "mz_store_sf","mz_store_sf_track","mz_sf_fee_config"};
		pack = "com.mazing.services.store.delivery";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// --audit
		tables = new String[] { "mz_store_audit_user", "mz_store_audit_facility", "mz_store_audit_health", "mz_store_audit" };
		pack = "com.mazing.services.store.audit";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		// --recommend
		tables = new String[] { "mz_recommend_version", "mz_recommend_block", "mz_recommend_item" };
		pack = "com.mazing.services.store.recommend";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// --invite
		tables = new String[] { "mz_store_invite_code" };
		pack = "com.mazing.services.store.invite";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_dish", "mz_dish_category", "mz_dish_day_surplus", "mz_dish_pic", "mz_dish_tag", "mz_dish_tag_mp",
				"mz_dish_track" };
		pack = "com.mazing.services.store.dish";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_dish_flavor_mp", "mz_dish_user_fav_mp", "mz_store_operating_mp", "mz_store_user_fav_mp" };
		pack = "com.mazing.services.store.mapping";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// news 资讯
		tables = new String[] { "mz_news_block", "mz_news_block_fav", "mz_news_storeinfo", "mz_news_storeinfo_fav", "mz_news_store_storeinfo_mp" };
		pack = "com.mazing.services.store.news";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		//ticket 票
		tables = new String[] { "mz_ticket_rule", "mz_ticket_store_mp", "mz_ticket", "mz_ticket_batch" , "mz_ticket_refund_record"};
		pack = "com.mazing.services.store.ticket";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		
		// user

		url = urlHost + "/mz_user?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_user", "mz_user_map", "mz_user_nickname", "mz_user_profile", "mz_user_setting" };
		pack = "com.mazing.services.user.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_user_address", "mz_user_site_message", "mz_android_profile", "mz_push_statistic" };
		pack = "com.mazing.services.user.business";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_user_login_log", "mz_user_oper_log", "mz_user_oper_log_detail" };
		pack = "com.mazing.services.user.log";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		tables = new String[] { "mz_user_customize_tag", "mz_user_sys_tag", "mz_user_tag_raise_log"};
		pack = "com.mazing.services.user.tag";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// order

		url = urlHost + "/mz_order?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_order", "mz_order_detail", "mz_order_track" };
		pack = "com.mazing.services.order.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		url = urlHost + "/mz_order?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_order_comment", "mz_order_refund" };
		pack = "com.mazing.services.order.business";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		url = urlHost + "/mz_order?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_store_order_report", "mz_order_report" };
		pack = "com.mazing.services.order.report";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// statistics
		tables = new String[] { "mz_user_statistics" };
		pack = "com.mazing.services.order.statistics";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		// sf
		tables = new String[] { "mz_third_express_base", "mz_sf_express", "mz_third_express_order_track" };
		pack = "com.mazing.services.order.express";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		// pay
		
		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_pay_record"};
		pack = "com.mazing.services.pay.pay";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_refund_record", "mz_alipay_refund_batch", "mz_alipay_refund_batch_detail", "mz_alipay_refund_notify" };
		pack = "com.mazing.services.pay.refund";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_express_balance", "mz_pay_express_record", "mz_express_balance_statement" };
		pack = "com.mazing.services.pay.express";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_store_payment_record", "mz_store_balance_transfer_log_detail"};
		pack = "com.mazing.services.pay.balance";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		

		// activity

		url = urlHost + "/mz_activity?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		tables = new String[] { "mz_coupon", "mz_coupon_batch", "mz_coupon_bear_type", "mz_coupon_group", "mz_coupon_order_statement",
				"mz_coupon_rule", "mz_coupon_type", "mz_coupon_obtain_record", "mz_user_gain_statement", "mz_coupon_bag",
				"mz_coupon_bag_rule", "mz_coupon_bag_obtain_record", "mz_coupon_bag_gain_statement","mz_coupon_blacklist","mz_coupon_expire_notify_record" };
		pack = "com.mazing.services.activity.coupon.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		
		tables = new String[] { "mz_order_gift", "mz_order_gift_gain_statement" };
		pack = "com.mazing.services.activity.coupon.order";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_act_coupon"};
		pack = "com.mazing.services.activity.coupon.statistics";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		
		
		
		System.out.println("all Done!");
	}

	// @Test
	public void generateRole() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		// store
		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_store_withdraw" };
		pack = "com.mazing.services.pay.withdraw";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	// @Test
	public void generateUserAccount() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		// store
		url = urlHost + "/mz_user?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_user_account" };
		pack = "com.mazing.services.user.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void testWithdrawNotifyDetails() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_alipay_withdraw_batch", "mz_alipay_withdraw_batch_detail", "mz_alipay_withdraw_notify" };
		pack = "com.mazing.services.pay.withdraw";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void geneOrderCallPayJob() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_order?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";

		tables = new String[] { "mz_order_rerun_pay_job" };
		pack = "com.mazing.services.order.business";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void geneMessage() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_user?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_system_message", "mz_user_site_message" };
		pack = "com.mazing.services.user.business";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genImIndex() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_user?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_user_imid_index" };
		pack = "com.mazing.services.user.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genRequestTable() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_pay?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_balance_change_request" };
		pack = "com.mazing.services.pay.balance";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genStoreTagRelated() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_config?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_store_tag_category", "mz_store_tag" };
		pack = "com.mazing.services.config.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genStoreTag() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_merchant?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_store_tag_mp" };
		pack = "com.mazing.services.store.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genDishSpec() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_merchant?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_dish_spec", "mz_dish_spec_snapshot", "mz_spec_supplement" };
		pack = "com.mazing.services.store.dish";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genMzWxUserMp() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_user?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_wx_user_mp" };
		pack = "com.mazing.services.user.base";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}

	@Test
	public void genDishGroup() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String un = "root";
		String ps = "12345678";
		String urlHost = "jdbc:mysql://10.0.0.10:3306";
		boolean daoImpl = false;// 是否生成DAO实现类，false则生成package-info来代替

		String url = "";
		String pack = "";
		String user = "Mazing";
		String path = "c:\\java-" + daoImpl;
		String[] tables = {};

		url = urlHost + "/mz_merchant?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
		// pack = "com.mazing.services.merchant.base";
		// tables = new String[] { "mz_merchant" };
		// MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);

		tables = new String[] { "mz_dish_group", "mz_dish_group_mp" };
		pack = "com.mazing.services.store.dish";
		MyEntityUtils.run(daoImpl, un, ps, url, pack, user, path, false, tables);
		System.out.println("all Done!");
	}
}

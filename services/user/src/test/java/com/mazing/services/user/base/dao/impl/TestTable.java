package com.mazing.services.user.base.dao.impl;

import com.yogu.services.user.base.dao.impl.UserDaoImpl;
import com.yogu.services.user.base.dao.impl.UserProfileDaoImpl;

/**
 * 输出用户帐号在哪个表
 * 
 * @author ten 2015/11/10.
 */
public class TestTable {

	public static void main(String[] args) {
		System.out.println(UserDaoImpl.getTableName("86", "18613144840"));
		System.out.println(UserProfileDaoImpl.getTableName(117251));
		System.out.println(UserProfileDaoImpl.getTableName(117396));
		//getUserPhonesWhoBuysTheDish();
	}

	/**
	 * 生成导出 '购买了某个菜品的 用户的手机号码' 的命令
	 * 
	 * @author sky 2016-03-31
	 */
	private static void getUserPhonesWhoBuysTheDish() {
		// 查询购买了“聚福盆菜(大盘)、聚福盆菜(中盘)” 的用户的手机号码
		// 先查出用户的uid,然后根据uid找出对应的手机号码
		// SELECT uid FROM mz_order AS t1, mz_order_detail AS t2 WHERE t1.status IN (35,40) AND t1.order_id=t2.order_id AND t2.dish_key IN
		// (23598,23597);
		String uids = "105414,109262,105313,109709,109654,106020,108459,109868,109381,105914,109737,109490,109893,110104,109508,109796,109858,109187,109402,109729,109352,109761,105914,110109,110116,106754,110468,110301,110371,109403,109680,104206,109899,110489,109540,110484,109494,110307,110609,109761,110579,100307,109904,110820,110683,110731,110424,106675,110679,110868,109695,105914,110817,110728,110591,110739,110742,110744,107144,110756,110761,110764,110734,108525,111207,109530,111333,111421,110670,107162,109433,109831,109509,109503,109596,109539,109625,109890,109789,109886,110022,109771,109583,109762,110009,109407,109841,109804,109723,110219,105447,109802,109857,109297,110454,106767,110507,110450,110610,109494,110334,110656,110004,110443,110466,110368,110221,110484,110433,110604,105804,110644,110535,110587,109761,110804,109383,106688,110829,110545,110363,109161,108332,104350,110769,110720,100307,100307,109589,110727,110841,110603,110854,110861,110715,110785,110835,106611,110798,111102,108525,111317,110367,109873,109545,107252,111435,105414";
		String[] uidArr = uids.split(",");

		// 生成导出命令
		for (String uid : uidArr) {
			String tableName = UserProfileDaoImpl.getTableName(Long.valueOf(uid));

			String sql = "mysql -h127.0.0.1 -umazing2 -pVXIHVqv-j56ke7P -e 'select passport from " + tableName + " where uid=" + uid
					+ " '>>/data/backup/mysql/sky/passport.txt";
			System.out.println(sql);
		}
	}

}

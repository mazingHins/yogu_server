package com.yogu;

import java.util.Calendar;
import java.util.Date;

/**
 * 定时任务 工具类
 * 
 * @author sky
 *
 */
public class TasksUtil {

	/**
	 * 计算 给定时长的 向后 起始时间节点(当前时间 -时长 = 向后时间)
	 * 
	 * @param duration
	 * @return
	 */
	public static Date caculateBackwardsStartTime(int duration) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -duration);
		return now.getTime();
	}

}

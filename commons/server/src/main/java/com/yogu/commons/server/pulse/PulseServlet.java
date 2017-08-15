/**
 * 
 */
package com.yogu.commons.server.pulse;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.JsonUtils;

/**
 * 监控、心跳检测Servlet<br>
 * <br>
 * 外部约5s调用一次，返回0表示正常。其他表示异常 <br>
 * 
 * JFan 2014年12月15日 下午3:15:34
 */
public class PulseServlet extends HttpServlet {

	private static final long serialVersionUID = -700030827407811116L;

	private PulseService pulseService;

	private Map<Integer, String> help;
	private String helpText;

	/**
	 * 系统运行状态值 <br>
	 * 
	 * -2：系统关闭中<br>
	 * -1：系统启动中<br>
	 * 0：系统正常<br>
	 * */
	public static Integer state;

	/** 多少毫秒之内不会重复检测 */
	private long nextTimeMun;
	/** 最后一次检测的时间 */
	private long lastTime;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		state = -1;

		super.init(config);

		// 初始化多久间隔内部会重复检测状态，默认15秒
		// 即：15秒内所拿到的状态是旧值，这样做的目的是防止刷爆
		long mtmLong;
		String ntmStr = config.getInitParameter("nextTimeMun");
		try {
			mtmLong = Long.parseLong(ntmStr);
			if (0 > mtmLong)
				mtmLong = 15000;
		} catch (NumberFormatException e) {
			mtmLong = 15000;
		}
		nextTimeMun = mtmLong;

		//
		pulseService = MainframeBeanFactory.getBean(PulseService.class);
		Args.notNull(pulseService, "'pulseService'");

		Map<Integer, String> help = pulseService.help();
		help.put(-2, "系统关闭中");
		help.put(-1, "系统启动中");
		help.put(0, "ok");// 系统正常
		this.help = help;

		helpText = JsonUtils.toJSONString(help);

		// 检测
		check();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String out;
		String query = req.getQueryString();
		// show state CodeMessage
		if (null != query && "help".equals(query)) {
			out = helpText;

			// check state
		} else {
			// 判断是否需要检测
			if (nextTimeMun <= (System.currentTimeMillis() - lastTime)) {
				synchronized (state) {
					if (nextTimeMun <= (System.currentTimeMillis() - lastTime)) {
						check();
					}
				}
			}

			out = help.get(state);
		}

		// 设置编码是耗时的操作，如果ok，才设置转换
		if (0 != state) {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
		}

		// 输出状态值
		resp.getOutputStream().write(out.getBytes());
		resp.getOutputStream().flush();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		state = -2;

		super.destroy();
	}

	private void check() {
		state = pulseService.newestState();

		// 只有正常的，才刷新间隔时间，上面不正常的由其自测
		lastTime = System.currentTimeMillis();
	}

}

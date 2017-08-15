<%@ page contentType="text/html; charset=UTF-8"%>

<%@page import="com.x.service.impl.MemcachedServiceImpl"%>
<%@page import="com.x.log4j.DailyRollingFileAppender"%>
<%@page import="com.x.factory.MainframeBeanFactory"%>
<%@page import="org.apache.log4j.helpers.NullEnumeration"%>
<%@page import="org.springframework.util.AntPathMatcher"%>
<%@page import="org.apache.log4j.spi.LoggerRepository"%>
<%@page import="org.apache.log4j.spi.RootLogger"%>
<%@page import="org.apache.log4j.Appender"%>
<%@page import="org.apache.log4j.Category"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.apache.log4j.Level"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>

<%!
// 验证身份存储到MC中，key为随机生成
MemcachedServiceImpl mc = MainframeBeanFactory.getBeanFactory().getBean(MemcachedServiceImpl.class);
int exp = MemcachedServiceImpl.EXPIRY_HALF_HOUR;

Logger hc = Logger.getLogger("hc");
AntPathMatcher antMatcher = new AntPathMatcher();// Ant表达式，用于搜索
RootLogger root = (RootLogger) hc.getRootLogger();// Root对象
LoggerRepository defaultHierarchy = hc.getLoggerRepository();

// 获取排序Comparator对象
// 有可能品牌排序使用旧的中间层jar包，导致没有此类存在
Comparator comparator = getComparator();
private Comparator getComparator() {
    try {
        Class clz = Class.forName("com.x.log4j.util.LoggerComparator");
        if(null != clz) {
            Object o = clz.newInstance();
            return (Comparator) o;
        }
    } catch(Exception e) {
    }
    return null;
}

// 取得len长度的随机字符串
// 小于等于0则默认长度1
char[] Az = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
private String getStr(int len) {
    Random r = new Random();
    String s = "";
    int i = 0;
    do {
        s += Az[r.nextInt(52)];
        i += 1;
    } while (i <= len);
	return s;
}

// 返回其中Appender的名称列表，如果自身没有，则找一次父的Appender。
// 返回内容例子：'A','B'    (包括'和,)
private String getCategoryNames(Category c) {
    if (null == c)
        return "''";
    Enumeration<?> allAppenders = c.getAllAppenders();
    if (allAppenders instanceof NullEnumeration) {
        return getCategoryNames(c.getParent());
    } else {
        String r = "'";
        int i = 0;
        for (; allAppenders.hasMoreElements();) {
            if(i++ > 0)
                r += ',';
            r += ((Appender) allAppenders.nextElement()).getName();
        }
        return (r += "'");
    }
}
%>

<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	
	String skey = request.getParameter("skey");
	if(null == skey)
	    skey = "skey_" + getStr(13);

	// 前奏：验证身份
	// 首先访问地址：http://localhost:8080/vips-mobile/log4j.jsp?q7&7q=mblog
	// 然后输入密码：在下面
	Object s = mc.get(skey);
	if(null == s) {
	    if(!"q7&7q=mblog".equals(request.getQueryString())) {
	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	return;
	    } else {
	        String str = getStr(8);
	        mc.merge(skey, exp, str);
	        response.getWriter().write("<form method=\"post\" action=\"log4j.jsp\"><input type=\"hidden\" name=\"skey\" value=\"");
	        response.getWriter().write(skey);
	        response.getWriter().write("\"><input type=\"password\" name=\"");
	        response.getWriter().write(str);
	        response.getWriter().write("\"><input type=\"submit\" value=\"提交\"></form>");
	        response.getWriter().flush();
	    }
	    return;
	}
	
	if(!"OK".equals(s)) {
		String sp = request.getParameter(s.toString());
		if(!"VipsM0bile#".equals(sp)) {
		    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	return;
		} else {
		    mc.merge(skey, exp, "OK");
		}
	}
	
	// 开始处理请求
	String message = null;
	String subType = request.getParameter("subType");
	String pattern = request.getParameter("pattern");
	pattern = (null == pattern ? "" : pattern.trim());
	
	if(null != subType) {
	    // 修改日志设置
	    if("1".equals(subType)) {
	        // 获取需要设置的级别，强制默认DEBUG
		    String levelStr = request.getParameter("levelStr");
		    if(null == levelStr || "".equals(levelStr))
		        levelStr = "DEBUG";
		    // 获取需要设置additivity的值
		    boolean additivity = ("1".equals(request.getParameter("additivityStr")));
		    String loggerName = request.getParameter("loggerName");
		    message = "【"+loggerName+"】->【"+levelStr+"】【"+additivity+"】";
		    try {
		//修改rootlogger
		if("rootLogger".equals(loggerName)){
			root.setLevel(Level.toLevel(levelStr));
		}else{
		    // 修改指定的logger
	        Logger logger = defaultHierarchy.getLogger(loggerName);
	    	if(null != logger){
	    		logger.setLevel(Level.toLevel(levelStr));
	    		logger.setAdditivity(additivity);
	    		message += "修改成功！";
	   		}else{
	       		message = loggerName + "不存在！";
	    	}
	    } 
		    } catch(Exception e) {
		        message += "修改失败：" + e.getMessage();
		    }
		
		// flush 提交buffered数据
	    } else if("8".equals(subType)) {
	        String appenderName = request.getParameter("appenderName");
	        DailyRollingFileAppender.flush(appenderName);
	        
		// 查询日志（暂无）
	    } else if("9".equals(subType)) {
	    
	    // 提交了subType，却无法识别，直接404
	    } else {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    	return;
	    }
	}

	// 展示logger列表
	List<Logger> loggerList = new ArrayList<Logger>();
    Enumeration<?> currentLoggers = defaultHierarchy.getCurrentLoggers();
    while (currentLoggers.hasMoreElements()) {
        Object obj = currentLoggers.nextElement();
        Logger log = (Logger) obj;
        
        if(!"".equals(pattern) && !antMatcher.matchStart(pattern, log.getName()))
            continue;
        
        loggerList.add(log);
    }
    Logger[] loggerArray = loggerList.toArray(new Logger[loggerList.size()]);
    if(null != comparator)
        Arrays.sort(loggerArray, comparator);
%>

<html>
	<head>
		<title>日志配置</title>
		<script type="text/javascript">
			<%if(null != message) {%>alert("<%=message%>");<%}%>
			
			function updLogInfo(loggerName, hashcode) {
				var levelStr = document.getElementById(loggerName+"_levelStr").value;
				var additivityStr = document.getElementById(loggerName+"_additivityStr").value;
				var action = ("log4j.jsp" + (hashcode ? "#"+hashcode : ""));
				var search = document.getElementById("search").value;
				subDate(action, "1", levelStr, additivityStr, loggerName, search, "");
			}
			
			function searchLog() {
				var str = document.getElementById("search").value;
				subDate("log4j.jsp", "9", "", "", "", str, "");
			}
			
			function fulshLog(appenderName, hashcode) {
				var search = document.getElementById("search").value;
				var action = ("log4j.jsp" + (hashcode ? "#"+hashcode : ""));
				subDate(action, "8", "", "", "", search, appenderName);
			}
			
			function subDate(action, subType, levelStr, additivityStr, loggerName, pattern, appenderName) {
				document.getElementById("pattern").setAttribute("value", pattern);
				document.getElementById("subType").setAttribute("value", subType);
				document.getElementById("levelStr").setAttribute("value", levelStr);
				document.getElementById("loggerName").setAttribute("value", loggerName);
				document.getElementById("appenderName").setAttribute("value", appenderName);
				document.getElementById("additivityStr").setAttribute("value", additivityStr);
				var myform = document.getElementById("myform");
				myform.setAttribute("action", action);
				myform.submit();
			}
		</script>
	</head>
	<body>
		<div>
			AntPathMatcher:<input id="search" value="<%=pattern%>" />
			<input type="button" value="查询" onclick="javascript:searchLog();" />  : com.x.*
		</div>
		<table border="1" style="border-collapse: collapse">
			<tr bgcolor="eaf1ff">
				<td style="text-align: center;width: 70%;">包</td>
				<td style="text-align: center;width: 10%;">日志级别</td>
				<td style="text-align: center;width: 10%;">Additivity</td>
				<td style="text-align: center;width: 5%;">修改</td>
				<td style="text-align: center;width: 5%;">flush</td>
			</tr>
			
			<%
			String rootName = "rootLogger";
			Level rootLevel = root.getLevel(); 
			String rootLevelStr = (null != rootLevel ? rootLevel.toString() : null);
			%>
			<tr>
				<td><%=rootName%></td>
				<td>
					<select id="<%=rootName%>_levelStr">
						<%if(null == rootLevelStr) {%><option value=""></option><%}%>
						<option value="OFF"   <%if("OFF".equals(rootLevelStr)){%><%="selected='selected'"%><%}%>  >OFF</option>
						<option value="FATAL" <%if("FATAL".equals(rootLevelStr)){%><%="selected='selected'"%><%}%>>FATAL</option>
						<option value="ERROR" <%if("ERROR".equals(rootLevelStr)){%><%="selected='selected'"%><%}%>>ERROR</option>
						<option value="WARN"  <%if("WARN".equals(rootLevelStr)){%><%="selected='selected'"%><%}%> >WARN</option>
						<option value="INFO"  <%if("INFO".equals(rootLevelStr)){%><%="selected='selected'"%><%}%> >INFO</option>
						<option value="DEBUG" <%if("DEBUG".equals(rootLevelStr)){%><%="selected='selected'"%><%}%>>DEBUG</option>
						<option value="ALL"   <%if("ALL".equals(rootLevelStr)){%><%="selected='selected'"%><%}%>  >ALL</option>
					</select>
				</td>
				<td>
					<select id="<%=rootName%>_additivityStr" disabled="disabled">
						<option value="1"  <%if(root.getAdditivity()){%><%="selected='selected'"%><%}%>>TRUE</option>
						<option value="0" <%if(!root.getAdditivity()){%><%="selected='selected'"%><%}%>>FALSE</option>
					</select>
				</td>
				<td><input type="button" value="修改" onclick="javascript:updLogInfo('<%=rootName%>', '<%=rootName.hashCode()%>');"/></td>
				<td></td>
			</tr>
			
			<%
			for (Logger log : loggerArray) {
			    String name = log.getName();
			    Level level = log.getLevel();
			    String levelStr = (null != level ? level.toString() : null);
			%>
			<tr id="<%=name.hashCode()%>">
				<td><%=name%></td>
				<td>
					<select id="<%=name%>_levelStr">
						<%if(null == levelStr) {%><option value=""></option><%}%>
						<option value="OFF"   <%if("OFF".equals(levelStr)){%><%="selected='selected'"%><%}%>  >OFF</option>
						<option value="FATAL" <%if("FATAL".equals(levelStr)){%><%="selected='selected'"%><%}%>>FATAL</option>
						<option value="ERROR" <%if("ERROR".equals(levelStr)){%><%="selected='selected'"%><%}%>>ERROR</option>
						<option value="WARN"  <%if("WARN".equals(levelStr)){%><%="selected='selected'"%><%}%> >WARN</option>
						<option value="INFO"  <%if("INFO".equals(levelStr)){%><%="selected='selected'"%><%}%> >INFO</option>
						<option value="DEBUG" <%if("DEBUG".equals(levelStr)){%><%="selected='selected'"%><%}%>>DEBUG</option>
						<option value="ALL"   <%if("ALL".equals(levelStr)){%><%="selected='selected'"%><%}%>  >ALL</option>
					</select>
				</td>
				<td>
					<select id="<%=name%>_additivityStr">
						<option value="1"  <%if(log.getAdditivity()){%><%="selected='selected'"%><%}%>>TRUE</option>
						<option value="0" <%if(!log.getAdditivity()){%><%="selected='selected'"%><%}%>>FALSE</option>
					</select>
				</td>
				<td><input type="button" value="修改" onclick="javascript:updLogInfo('<%=name%>', '<%=name.hashCode()%>');"/></td>
				<td><input type="button" value="<%=getCategoryNames(log)%>" onclick="javascript:fulshLog(<%=getCategoryNames(log)%>, '<%=name.hashCode()%>');"/></td>
			</tr>
			<%}%>
		</table>
			
		<!--  -->
		<form id="myform" method="post" action="log4j.jsp">
			<input type="hidden" id="pattern" name="pattern">
			<input type="hidden" id="subType" name="subType">
			<input type="hidden" id="levelStr" name="levelStr">
			<input type="hidden" id="loggerName" name="loggerName">
			<input type="hidden" id="appenderName" name="appenderName">
			<input type="hidden" id="additivityStr" name="additivityStr">
			<input type="hidden" name="skey" value="<%=skey%>">
		</form>
	</body>
</html>
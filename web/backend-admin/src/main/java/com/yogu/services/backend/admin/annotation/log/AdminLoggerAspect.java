package com.yogu.services.backend.admin.annotation.log;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.RestResult;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.dto.AdminOperationLog;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 管理员日志记录切面
 * @author ten 2015/12/28.
 */
@Aspect
@Component
public class AdminLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(AdminLoggerAspect.class);

    //标注该方法体为后置通知，当目标方法执行成功后执行该方法体
    @AfterReturning(value = "within(com.yogu.services.backend.admin..*) && @annotation(adminLog)", returning = "obj")
    public void addLogSuccess(JoinPoint jp, AdminLog adminLog, Object obj) {

//        Object[] parames = jp.getArgs();//获取目标方法体参数
        logger.info("admin#aspect#AdminLog | 记录方法执行成功的日志 | signature: {}, targetClass: {}", jp.getSignature().toString(),
                jp.getTarget().getClass().getName());
        try {
            processLog(jp, adminLog, obj);
        }
        catch (Exception e) {
            logger.error("admin#aspect#AdminLog | 记录日志异常", e);
        }
    }

    /**
     * 执行记录日志的操作
     * @param jp
     * @param adminLog
     * @param result
     * @throws Exception
     */
    private void processLog(JoinPoint jp, AdminLog adminLog, Object result) throws Exception {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Method method = ms.getMethod();
        String type = adminLog.type(); // 日志类型，为空时取 MenuResource 的值
        if (StringUtils.isEmpty(type) && method.isAnnotationPresent(MenuResource.class)) {
            MenuResource menuResource = method.getAnnotation(MenuResource.class);
            type = menuResource.value();
        }

        if (doLog(adminLog, result)) {
            // 执行结果的信息
            String resultMessage = getResultMessage(adminLog, result);
            // 参数
            String arguments = getArguments(jp, method, adminLog);
            // ip
            String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
            // get admin id from AdminContext
            logger.info("admin#aspect#AdminLog | 参数列表 | admin: {}, type: {}, arguments: {}, ip: {}, result: {}", AdminContext.getAccountId(),
                    type, arguments, ip, resultMessage);
            // save log
            AdminLogger adminLogger = MainframeBeanFactory.getBean(AdminLogger.class);
            AdminOperationLog log = new AdminOperationLog();
            log.setIp(ip);
            log.setOperationType(type);
            log.setResult(resultMessage);
            log.setArguments(arguments);
            log.setUid(AdminContext.getAccountId());
            adminLogger.saveAdminLog(log);
        }
        else {
            logger.info("admin#aspect#AdminLog | DONT LOG ...............");
        }

    }

    /**
     * 读取所有参数的值
     * @param jp
     * @param method
     * @param adminLog
     * @return 返回本次操作的参数，格式：k1=v1, k2=v2 ...
     */
    private String getArguments(JoinPoint jp, Method method, AdminLog adminLog) throws Exception {
        boolean all = AdminLog.ALL.equals(adminLog.args());
        Set<String> argSet = null;
        if (all || StringUtils.isEmpty(adminLog.args())) {
            argSet = Collections.emptySet();
        }
        else {
            argSet = new HashSet<>();
            String[] args = adminLog.args().split(",");
            for (String arg : args) {
                if (StringUtils.isNotBlank(arg)) {
                    argSet.add(arg.trim());
                }
            }
        }

        // 参数
        Map<String, Object> params = getJointPointParam(jp);

        StringBuilder buf = new StringBuilder(512);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String name = entry.getKey();
            String notNullName = (name == null ? "" : name);
            Object value = entry.getValue();
            if ((value instanceof HttpServletRequest) || (value instanceof HttpServletResponse)
                    || (notNullName.indexOf("MODEL_KEY_PREFIX") >= 0)
                    || notNullName.indexOf("_AUTH") > 0) {
                ; // skip HttpServletRequest, HttpServletResponse
            } else if (all || argSet.contains(name)) {
                // 在配置里的参数才会记录到日志
                if (buf.length() > 0)
                    buf.append(", ");
                String lowerName = name.toLowerCase();
                if (lowerName.indexOf("password") >= 0 || lowerName.indexOf("appkey") >= 0) {
                    // 不输出密码之类的信息
                    value = "***";
                } else if (value != null && lowerName.indexOf("passport") >= 0) {
                    value = LogUtil.hidePassport(value.toString());
                }
                buf.append(name).append("=").append(value);
            }

        }


        if (buf.length() > 512) {
            // 限制长度
            buf.setLength(512);
        }
        return buf.toString();
    }


    /**
     * 判断是否需要写日志
     * @param adminLog 注解
     * @param result 方法执行结果
     * @return true=需要记录日志
     */
    private boolean doLog(AdminLog adminLog, Object result) {
        if (adminLog.level() == AdminLog.LEVEL_NONE_EXCEPTION) {
            return true;
        }
        if (result != null && result instanceof RestResult) {
            return ((RestResult) result).isSuccess();
        }
        return false;
    }

    /**
     * 获取要记录的日志信息
     * @param adminLog 注解
     * @param result 方法执行结果
     * @return 返回日志信息，不为null
     */
    private String getResultMessage(AdminLog adminLog, Object result) {
        String message = "";
        if (result != null) {
            if (result instanceof RestResult) {
                RestResult rr = (RestResult) result;
                message = "code: " + rr.getCode() + ", message: " + rr.getMessage();
            } else if (result instanceof ModelAndView) {
                ModelAndView view = (ModelAndView) result;
                if ("/error".equals(view.getViewName())
                        || "/exception".equals(view.getViewName())) {
                    message = "失败，转到页面：" + view.getViewName();
                } else {
                    message = "成功，转到页面：" + view.getViewName();
                }
            } else if (result instanceof String) {
                String view = (String) result;
                if ("/error".equals(view)
                        || "/exception".equals(view)) {
                    message = "失败，转到页面：" + view;
                } else {
                    message = "成功，转到页面：" + view;
                }
            }
        }
        if (StringUtils.isNotBlank(adminLog.extraMessage())) {
            if (StringUtils.isNotEmpty(message)) {
                message += ", ";
            }
            message += ", extra: " + adminLog.extraMessage();
        }
        if (message.length() > 255) {
            // 限制255
            message = message.substring(0, 255);
        }
        return message;
    }

    /**
     * 获取连接点对应方法的请求参数列表(参数名-参数值)
     *
     * @param jp
     *            - 切面连接点
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Map<String, Object> getJointPointParam(JoinPoint jp) throws IllegalArgumentException, IllegalAccessException {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Method method = ms.getMethod();

        List<Class<?>> paramTypes = Arrays.asList(method.getParameterTypes());
        List<String> paramNames = getMethodParamNames(method);
        // List<String> paramNames = MethodParamNamesScaner.getParamNames(method);
        Object[] args = jp.getArgs();
        int size = paramNames.size();
        int length = args.length;

        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < size && i < length; i++) {
            Class<?> clazz = paramTypes.get(i);
            String name = paramNames.get(i);
            Object value = args[i];

            if (isJavaClass(clazz)) {
                params.put(name, value);
            }
            else {
                if ((value instanceof HttpServletResponse)) {
                    continue;
                }
                Map<String, Object> param = getClassAttrValueMap(clazz, name, value);
                params.putAll(param);
            }
        }

        return params;
    }

    /**
     * 获取方法形参名
     *
     * @param method
     * @return
     */
    public static List<String> getMethodParamNames(Method method) {
        ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = discoverer.getParameterNames(method);
        return Arrays.asList(params);
    }

    /**
     * 获取对象属性列表(属性名-属性值) 支持递归
     *
     * @param clazz
     *            - 类类型
     * @param name
     *            - 对象名
     * @param value
     *            - 对象值
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Map<String, Object> getClassAttrValueMap(Class<?> clazz, String name, Object value) throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> attrs = new HashMap<String, Object>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Class<?> _clazz = field.getType();
            String _name = field.getName();
            field.setAccessible(true);
            Object _value = field.get(value);

            if (isJavaClass(_clazz)) {
                attrs.put(_name, _value);
            }
            // 暂不递归获取
            // else {
            // Map<String, Object> attr = getClassAttrValueMap(_clazz, _name, _value);
            // attrs.putAll(attr);
            // }
        }

        return attrs;
    }

    /**
     * 判断是否Java原生类(非用户自定义类)
     *
     * @param clazz
     *            - 类类型
     * @return
     */
    public static boolean isJavaClass(Class<?> clazz) {
        return clazz != null && clazz.getClassLoader() == null;
    }

    @AfterThrowing(pointcut="within(com.yogu.services.backend.admin..*) && @annotation(adminLog)", throwing="ex")
    public void addLogException(JoinPoint jp, AdminLog adminLog, Exception ex){
        logger.error("admin#aspect#addLogException | 记录方法执行异常的日志 | signature: {}, targetClass: {}, message: {}",
                jp.getSignature().toString(),
                jp.getTarget().getClass().getName(), ex.getMessage());
    }
}

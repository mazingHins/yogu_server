package com.yogu.services.backend.admin.annotation.log;

/**
 * 注解 AdminLog 的拦截器
 * @author ten 2015/10/6.
 */
//public class AdminLogInterceptionService implements InterceptionService {
//
//    private static final Logger logger = LoggerFactory.getLogger(AdminLogInterceptionService.class);
//
//    @Override
//    public Filter getDescriptorFilter() {
//        return new Filter() {
//            @Override
//            public boolean matches(final Descriptor d) {
//                final String clazz = d.getImplementation();
//                return clazz.startsWith("com.mazing.services.backend.admin");
//            }
//        };
//    }
//
//    @Override
//    public List<MethodInterceptor> getMethodInterceptors(Method method) {
//        if (method.isAnnotationPresent(AdminLog.class)) {
//
//            List<MethodInterceptor> list = new ArrayList<>(1);
//            MethodInterceptor mi = new MethodInterceptor() {
//                @Override
//                public Object invoke(MethodInvocation methodInvocation) throws Throwable {
//                    Object o = methodInvocation.proceed();
//                    processLog(methodInvocation, o);
//                    return o;
//                }
//            };
//            list.add(mi);
//            return list;
//        }
//        return null;
//    }
//
//    /**
//     * 执行日志记录
//     * @param methodInvocation
//     * @param result 方法的执行结果
//     */
//    private void processLog(MethodInvocation methodInvocation, Object result) {
//        logger.info("admin#intercetor#AdminLog | before processing AdminLog");
//        Method method = methodInvocation.getMethod();
//        AdminLog adminLog = method.getAnnotation(AdminLog.class);
//        String type = adminLog.type(); // 日志类型，为空时取 MenuResource 的值
//        if (StringUtils.isEmpty(type) && method.isAnnotationPresent(MenuResource.class)) {
//            MenuResource menuResource = method.getAnnotation(MenuResource.class);
//            type = menuResource.value();
//        }
//
//        if (doLog(adminLog, result)) {
//            // 执行结果的信息
//            String resultMessage = getResultMessage(adminLog, result);
//            // 参数
//            String arguments = getArguments(methodInvocation, method, adminLog);
//            // ip
//            String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
//            // get admin id from AdminContext
//            logger.info("admin#intercetor#AdminLog | 参数列表 | admin: {}, type: {}, arguments: {}, ip: {}, result: {}", AdminContext.getAccountId(),
//                    type, arguments, ip, resultMessage);
//            // TODO by ten 2015/10/6 save log
//            AdminLogger adminLogger = MainframeBeanFactory.getBean(AdminLogger.class);
//            AdminOperationLog log = new AdminOperationLog();
//            log.setIp(ip);
//            log.setOperationType(type);
//            log.setResult(resultMessage);
//            log.setArguments(arguments);
//            log.setUid(AdminContext.getAccountId());
//            adminLogger.saveAdminLog(log);
//        }
//        else {
//            logger.info("admin#intercetor#AdminLog | DONT LOG ...............");
//        }
//    }
//
//    /**
//     * 判断是否需要写日志
//     * @param adminLog 注解
//     * @param result 方法执行结果
//     * @return true=需要记录日志
//     */
//    private boolean doLog(AdminLog adminLog, Object result) {
//        if (adminLog.level() == AdminLog.LEVEL_NONE_EXCEPTION) {
//            return true;
//        }
//        if (result != null && result instanceof RestResult) {
//            return ((RestResult) result).isSuccess();
//        }
//        return false;
//    }
//
//    /**
//     * 获取要记录的日志信息
//     * @param adminLog 注解
//     * @param result 方法执行结果
//     * @return 返回日志信息，不为null
//     */
//    private String getResultMessage(AdminLog adminLog, Object result) {
//        String message = "";
//        if (result != null && result instanceof RestResult) {
//            RestResult rr = (RestResult) result;
//            message = "code: " + rr.getCode() + ", message: " + rr.getMessage();
//        }
//        else if (result != null && result instanceof Viewable) {
//            Viewable viewable = (Viewable) result;
//            if ("/error".equals(viewable.getTemplateName())
//                || "/exception".equals(viewable.getTemplateName())) {
//                message = "失败，转到页面：" + viewable.getTemplateName();
//            }
//            else {
//                message = "成功，转到页面：" + viewable.getTemplateName();
//            }
//        }
//        if (StringUtils.isNotBlank(adminLog.extraMessage())) {
//            if (StringUtils.isNotEmpty(message)) {
//                message += ", ";
//            }
//            message += ", extra: " + adminLog.extraMessage();
//        }
//        if (message.length() > 255) {
//            // 限制255
//            message = message.substring(0, 255);
//        }
//        return message;
//    }
//
//    /**
//     * 读取所有参数的值
//     * @param mi
//     * @param method
//     * @param adminLog
//     * @return 返回本次操作的参数，格式：k1=v1, k2=v2 ...
//     */
//    private String getArguments(MethodInvocation mi, Method method, AdminLog adminLog) {
//        boolean all = AdminLog.ALL.equals(adminLog.args());
//        Set<String> argSet = null;
//        if (all || StringUtils.isEmpty(adminLog.args())) {
//            argSet = Collections.emptySet();
//        }
//        else {
//            argSet = new HashSet<>();
//            String[] args = adminLog.args().split(",");
//            for (String arg : args) {
//                if (StringUtils.isNotBlank(arg)) {
//                    argSet.add(arg.trim());
//                }
//            }
//        }
//        int len = (mi.getArguments() == null ? 0 : mi.getArguments().length);
//        StringBuilder buf = new StringBuilder(len * 16 + 16);
//        String[] paramNames = MethodParamNamesScaner.getMethodParamNames(method);
////        logger.info("------- names len: " + paramNames.length + ", arguments len=" + len);
//
//        if (paramNames.length == len) {
//            for (int i = 0; i < len; i++) {
//                String name = StringUtils.trimToEmpty(paramNames[i]);
//                Object value = mi.getArguments()[i];
//                if (value instanceof HttpServletRequest || value instanceof HttpServletResponse) {
//                    ; // skip HttpServletRequest, HttpServletResponse
//                }
//                else if (all || argSet.contains(name)) {
//                    // 在配置里的参数才会记录到日志
//                    if (buf.length() > 0)
//                        buf.append(", ");
//                    String lowerName = name.toLowerCase();
//                    if (lowerName.indexOf("password") >= 0 || lowerName.indexOf("appkey") >= 0) {
//                        // 不输出密码之类的信息
//                        value = "***";
//                    }
//                    else if (value != null && lowerName.indexOf("passport") >= 0) {
//                        value = LogUtil.hidePassport(value.toString());
//                    }
//                    buf.append(name).append("=").append(value);
//                }
//
//            }
//        }
//
//        if (buf.length() > 512) {
//            // 限制长度
//            buf.setLength(512);
//        }
//        return buf.toString();
//    }
//
//    @Override
//    public List<ConstructorInterceptor> getConstructorInterceptors(Constructor<?> constructor) {
//        return null;
//    }
//}

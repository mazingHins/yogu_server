package com.yogu.services.backend.admin.annotation.authority;

/**
 * 权限拦截器
 * @author ten 2015/10/17.
 */
//public class AuthorityInterceptionService implements InterceptionService {
//
//    private static final Logger logger = LoggerFactory.getLogger(AuthorityInterceptionService.class);
//
//    private Authorizable authorizable = null;
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
//    /**
//     * 返回 Authorizable 实例
//     * @return
//     */
//    private Authorizable getAuthorizable() {
//        if (authorizable == null) {
//            authorizable = MainframeBeanFactory.getBean(Authorizable.class);
//        }
//        return authorizable;
//    }
//
//    /**
//     * 判断是否有权限，如果没有任何类实现 Authorizable，默认是通过权限判断
//     * @param adminId 管理员ID
//     * @param uri url地址
//     * @return true=有权限
//     */
//    private boolean hasAuthorizable(long adminId, String uri) {
//        return getAuthorizable().hasAuthorization(adminId, uri);
//    }
//
//    @Override
//    public List<MethodInterceptor> getMethodInterceptors(final Method method) {
//        // 对只有 MenuResource 注解的方法做处理
//        if (method.isAnnotationPresent(MenuResource.class)
//                && method.isAnnotationPresent(Path.class)) {
//            List<MethodInterceptor> list = new ArrayList<>(1);
//            MethodInterceptor mi = new MethodInterceptor() {
//                @Override
//                public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
//                    // 如果没有权限，根据方法的返回类型，返回对应的显示错误的值
//                    AuthorizeResult result = process(method);
//                    if (result.isOk())
//                        return methodInvocation.proceed();
//                    return result.getReturnValue();
//                }
//            };
//            list.add(mi);
//            return list;
//        }
//        return null;
//    }
//
//    /**
//     * 处理权限。如果没有权限，根据方法的返回类型，返回对应的显示错误的值。
//     * 对于 RestResult 类型，返回 ResultCode.UNAUTHORIZED_ACCESS，
//     * 对于 Viewable 类型，返回 exception 对应的 view。
//     * 其他类型，返回 null。
//     * @param method
//     * @return result.isOk()=true表示权限验证通过，否则是不通过
//     */
//    private AuthorizeResult process(final Method method) {
//        logger.info("admin#intercetor#authority | before 权限处理 | adminId: {}", AdminContext.getAccountId());
//        String value = AdminContext.getHttpServletRequest().getRequestURI();
//
//        // 是否通过权限检查，默认 true
//        boolean pass = true;
//        // 返回值
//        Object returnValue = null;
//
//        if (method.isAnnotationPresent(AllowAnonymous.class)) {
//            ; // 允许匿名访问，不验证权限
//        }
//        else {
//            // 验证权限 ten
//            pass = hasAuthorizable(AdminContext.getAccountId(), value);
//
//            // 处理没有权限时的返回值
//            if (pass == false) {
//                Class<?> returnType = method.getReturnType();
//                if (returnType != null) {
//                    if (returnType == RestResult.class) {
//                        logger.info("admin#intercetor#authority | 返回类型是 RestResult");
//                        returnValue = new RestResult(ResultCode.UNAUTHORIZED_ACCESS, "你没有权限访问");
//                    } else if (returnType == Viewable.class) {
//                        logger.info("admin#intercetor#authority | 返回类型是 Viewable");
//                        Map<String, Object> model = new HashMap<>(4);
//                        model.put("message", "你没有权限访问");
//                        returnValue = new Viewable("/exception", model);
//                    } else if (returnType == Void.class) {
//                        logger.info("admin#intercetor#authority | 返回类型是 void");
//                    } else {
//                        logger.warn("admin#intercetor#authority | 返回类型是 [未知]");
//                    }
//                }
//            }
//
//        }
//        logger.info("admin#intercetor#authority | 权限验证结果 | path: {}, pass: {}", value, pass);
//
//        return new AuthorizeResult(pass, returnValue);
//    }
//
//    @Override
//    public List<ConstructorInterceptor> getConstructorInterceptors(Constructor<?> constructor) {
//        return null;
//    }
//
//    /**
//     * 权限验证后的返回类型
//     */
//    static class AuthorizeResult {
//        /**
//         * 是否通过权限验证
//         */
//        private boolean ok = false;
//        /**
//         * 返回值
//         */
//        private Object returnValue = null;
//
//        AuthorizeResult(boolean ok) {
//            this.ok = ok;
//        }
//
//        AuthorizeResult(boolean ok, Object object) {
//            this.ok = ok;
//            this.returnValue = object;
//        }
//
//        public boolean isOk() {
//            return ok;
//        }
//
//        public Object getReturnValue() {
//            return returnValue;
//        }
//    }
//}

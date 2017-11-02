<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.yogu.commons.server.context.MainframeBeanFactory" %>
<%@ page import="com.yogu.commons.utils.resource.MenuItem" %>
<%@ page import="com.yogu.services.backend.admin.service.MenuService" %>
<%@ page import="com.yogu.services.backend.admin.util.AppConstants" %>
<%@ page import="com.yogu.services.backend.admin.util.HtmlMenuGenerator" %>
<%@ page import="com.yogu.services.backend.admin.context.AdminContext" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar" th:fragment="sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/tpl/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
                <p><%=AdminContext.getName()%></p>
                <!-- Status -->
                <%--<a href="#"><i class="fa fa-circle text-success"></i> Online</a>--%>
            </div>
        </div>

        <!-- search form (Optional) -->
        <!--
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
        </form>
        -->
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">HEADER</li>
            <li><a href="javascript:void(0)" onClick="gotodashboard();"><i class="fa fa-link"></i>前往新后台</a></li>
            <!-- Optionally, you can add icons to the links -->
            <!--
            <li class="active"><a href="#"><i class='fa fa-link'></i> <span>Link</span></a></li>
            <li><a href="#"><i class='fa fa-link'></i> <span>Another Link</span></a></li>
            <li class="treeview">
                <a href="#"><i class='fa fa-link'></i> <span>Multilevel</span> <i class="fa fa-angle-left pull-right"></i></a>
                <ul class="treeview-menu">
                    <li><a href="#">Link in level 2</a></li>
                    <li><a href="#">Link in level 2</a></li>
                </ul>
            </li>
            -->
            <%
                MenuService menuService = MainframeBeanFactory.getBean(MenuService.class);
                MenuItem item = menuService.getMenuTree(AdminContext.getAccountId(), AppConstants.APP_ID_DEFAULT);
                String html = HtmlMenuGenerator.outputHtml(item);
                // response.getWriter().println(html);
            %>
            <%=html%>
        </ul><!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>

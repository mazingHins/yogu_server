<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Main Footer -->
<footer class="main-footer" th:fragment="footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
        Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2015 <a href="http://www.mazing.com" target="_blank">www.mazing.com </a>.</strong> All rights reserved.
</footer>
<script>
function logout(){
	sessionStorage.remove('mz_member_token');
}
	
function gotodashboard(url = ''){
	// /oauth/token?token={{token}}&redirect={{url}}
	var link = 'https://dashboard.mazing.com/oauth/token';	
	var token = sessionStorage.getItem('mz_member_token') || '';
	if(token){
		link += '?token=' + encodeURIComponent(token);
	}
	
	if(url){
		link += '&redirect=' + url.replace('https://dashboard.mazing.com','');
	}
	
	window.location.href = link;
}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>异常统一界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
	background: #f3f3e1;
}

.wrap {
	margin: 0 auto;
	width: 1000px;
}

.logo {
	margin-top: 50px;
}

.logo h1 {
	font-size: 130px;
	color: #8F8E8C;
	text-align: center;
	margin-bottom: 1px;
	text-shadow: 1px 1px 6px #fff;
}

.logo p {
	color: rgb(228, 146, 162);
	font-size: 30px;
	margin-top: 1px;
	text-align: center;
}

.logo p span {
	color: lightgreen;
}

.sub a {
	color: white;
	background: #8F8E8C;
	text-decoration: none;
	padding: 7px 120px;
	font-size: 13px;
	font-family: arial, serif;
	font-weight: bold;
	-webkit-border-radius: 3em;
	-moz-border-radius: .1em;
	-border-radius: .1em;
}

.footer {
	color: #8F8E8C;
	position: absolute;
	right: 20px;
	bottom: 20px;
}

.footer a {
	color: rgb(228, 146, 162);
}
</style>
</head>
<body>
	<div class="wrap">
		<div class="logo">
			<h1>出错啦!</h1>
			<p>
				错误详情:
				<!-- it.message 是 jersey 的风格 -->
				<c:out value="${message}" escapeXml="false" />
			</p>
			<div class="sub">
				<p>
					<a href="#" onClick="javascript:window.history.back(-1);">返回</a>
				</p>
			</div>
		</div>
	</div>
	<div class="footer">
		<marquee behavior="scroll" scrollamount="3" direction="left"
			width="100%">
			<div id="fun_time"></div>
		</marquee>
	</div>
</body>
<script src="/js/jquery-1.10.2.js"></script>
<script>
	var messages = [
			"其实我不是一定要等你，只是等上了，就等不了别人了。——《朝露若颜》",
			"如果世界上曾经有那个人出现过，其他人都会变成将就！而我不愿意将就。——《何以笙箫默》",
			"我不能给你全世界，但是，我的世界，全部给你。——《念念不忘》",
			"第一步，抬头。第二步，闭眼。这样，眼泪就都流进心里了。我想起来了我从来没有不喜欢你，那些让你伤心的难听话，全是我撒谎。——《岁月是朵两生花》",
			"言希，没有我在家等着你，不要，忘了回家的路。——《十年一品温如言》",
			"一生一世……不离不弃！你是我的……我亦是你的。——《独步天下》",
			"我知道她想要什么，可是我给不了她。静婉，我给过你的，再也给不了别人。——《来不及说我爱你》",
			"我已经暗恋他很久了，如果我不走到他的前面，他永远不会看见我。——《被时光掩埋的秘密》",
			"时间没有等我，是你忘了带我走。——《夏至未至》", "岁岁长相伴，白头不相离。——《长相思》",
			"如果幸福不在巴黎，就一定在别处。可是我的世界已没有巴黎，只有你。——《巴黎没有摩天轮》",
			"你喜欢我，因为我是个不错的人，但你爱他，哪怕他是个错的人。——《许我向你看》" ];

	$(function() {
		var index = Math.floor(Math.random() * 12);
		var message = messages[index];
		$("#fun_time").append(message);
	});
</script>
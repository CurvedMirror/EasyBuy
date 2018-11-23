<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${loginUser==null }">
	<script>
			$(function(){
				$("#content").html("<h1><a href='${ctx}/login?action=toLogin'>去登录>></a></h1>");
			});
	</script>
</c:if>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>My JSP 'categoryBar.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="menu_bg">
		<div class="menu">
			<!--Begin 商品分类详情 Begin-->
			<div class="nav">
				<div class="nav_t">全部商品分类</div>
				<div class="leftNav">
					<ul>
						<c:forEach items="${pcList}" var="pc1">
							<c:if test="${pc1.type==1}">
								<li>
									<div class="fj">
										<span class="n_img"><span></span><img src="${ctx}/statics/images/nav1.png" /> </span>
										<span class="fl">${pc1.name}</span>
									</div>
									<div class="zj">
									
				                     <div class="zj">
		                        		<div class="zj_l">
			                          		  <c:forEach items="${pcList}" var="pc2">
												<c:if test="${pc2.type==2&&pc2.parentId==pc1.id}">
				                               		 <div class="zj_l_c">
					                                    <h2>
					                                        <a href="${ctx}/Product?action=queryProductList&categoryId=${pc2.id}&level=2">${pc2.name}</a>
					                                    </h2>
					                                	 <c:forEach items="${pcList}" var="pc3">
					                                		 <c:if test="${pc3.type==3&&pc3.parentId==pc2.id}">
					                                       		 <a href="${ctx}/Product?action=queryProductList&categoryId=${pc3.id}&level=3">${pc3.name}</a>
					                                    	 </c:if>
					                                    </c:forEach>
				                               		 </div>
			                               		 </c:if>
			                          		  </c:forEach>
		                       			 </div>
                   					 </div>
										
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			 <ul class="menu_r">
	            <li><a href="${ctx}/Home?action=index">首页</a></li>
	            <c:forEach items="${pcList}" var="pc1">
	            	<c:if test="${pc1.type==1 }">
	                	<li><a href="${ctx}/Product?action=queryProductList&level=1&categoryId=${pc1.id}">${pc1.name}</a></li>
	            	</c:if>
	            </c:forEach>
       		 </ul>

			
			<div class="m_ad">中秋送好礼！</div>
		</div>
	</div>
  </body>
</html>

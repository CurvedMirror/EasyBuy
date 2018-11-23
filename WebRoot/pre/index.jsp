<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!--[if IE 6]>
    <script src="${ctx}/statics/js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->

<title>尤洪</title>
</head>
<body>

	<c:if test="${pcList==null }">
		<c:redirect url="/Home?action=index"></c:redirect>
	</c:if>
	<!--Begin Header Begin-->
	<%@include file="/common/pre/header.jsp"%>
	<%@include file="/common/pre/searchBar.jsp"%>
	<!--End Header End-->
	<!--Begin Menu Begin-->
	<%@include file="/common/pre/categoryBar.jsp"%>
	<!--End Menu End-->
	<div class="i_bg bg_color">
		<div class="i_ban_bg">
			<!--Begin Banner Begin-->
			<div class="banner">
				<div class="top_slide_wrap">
					<ul class="slide_box bxslider">
						<li><img src="${ctx}/statics/images/ban1.jpg" width="740"
							height="401" />
						</li>
						<li><img src="${ctx}/statics/images/ban1.jpg" width="740"
							height="401" />
						</li>
						<li><img src="${ctx}/statics/images/ban1.jpg" width="740"
							height="401" />
						</li>
					</ul>
					<div class="op_btns clearfix">
						<a href="#" class="op_btn op_prev"><span></span> </a> <a href="#"
							class="op_btn op_next"><span></span> </a>
					</div>
				</div>
			</div>

			<!--End Banner End-->
			<div class="inews">
				<div class="news_t">
					<span class="fr"><a href="${ctx}/news?action=queryNewsList">更多 ></a> </span>新闻资讯
				</div>
				<ul>
					<c:forEach var="newsList" items="${newsList}">
						<li><span>[ 公告 ]</span><a href="${ctx}/news?action=newsDeatil&id=${newsList.id}">${newsList.content}</a></li>
					</c:forEach>
				</ul>
				<div class="charge_t">
					话费充值
					<div class="ch_t_icon"></div>
				</div>
				<form>
					<table border="0" style="width:205px; margin-top:10px;"
						cellspacing="0" cellpadding="0">
						<tr height="35">
							<td width="33">号码</td>
							<td><input type="text" value="" class="c_ipt" />
							</td>
						</tr>
						<tr height="35">
							<td>面值</td>
							<td><select class="jj" name="city">
									<option value="0" selected="selected">100元</option>
									<option value="1">50元</option>
									<option value="2">30元</option>
									<option value="3">20元</option>
									<option value="4">10元</option>
							</select> <span style="color:#ff4e00; font-size:14px;">￥99.5</span></td>
						</tr>
						<tr height="35">
							<td colspan="2"><input type="submit" value="立即充值"
								class="c_btn" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!--Begin 热门商品 Begin-->
		
		<div class="content mar_20">
			<img src="${ctx}/statics/images/mban_1.jpg" width="1200" height="110" />
		</div>

 <!--Begin 进口 生鲜 Begin-->
 <c:set var="index" value="0" />  
    <c:forEach items="${pcList}" var="pc1"  >
     	
   		 <c:if test="${pc1.type==1 }">
   			<c:set var="index" value="${index+1}"/>  
	       	<div class="i_t mar_10">
	            <span class="floor_num">${index}F</span>
	            <span class="fl">${pc1.name}</span>
	        </div>
	        <div class="content">
	            <div class="fresh_left">
	                <div class="fre_ban">
	                    <div id="imgPlay1">
	                        <ul class="imgs" id="actor1">
	                         <c:forEach items="${products}" var="pc3">
	                    		 <c:if test="${pc3.categoryLevel1Id==pc1.id}">
		                            <li><a href="${ctx}/Product?action=queryProductDeatil&id=${pc3.id}"><img src="${ctx}/files/${pc3.fileName}" width="211" height="286"/></a></li>
		                         </c:if>
	                        </c:forEach>
	                        </ul>
	                        <div class="prevf">上一张</div>
	                        <div class="nextf">下一张</div>
	                    </div>
	                </div>
	                <div class="fresh_txt">
	                    <div class="fresh_txt_c">
	                        <c:forEach items="${pcList}" var="pc2">
	                        <c:if test="${pc2.type==2&&pc2.parentId==pc1.id}">
	                            <a href="${ctx}/Product?action=queryProductList&categoryId=${pc2.id}">${pc2.name}</a>
	                        </c:if>
	                        </c:forEach>
	                    </div>
	                </div>
	            </div>
	            <div class="fresh_mid">
	                <ul>
	                     <c:forEach items="${products}" var="pc3">
	                     <c:if test="${pc3.categoryLevel1Id==pc1.id}">
	                        <li>
	                            <div class="name"><a href="#">${pc3.name}</a></div>
	                            <div class="price">
	                                <font>￥<span>${pc3.price}</span></font> &nbsp;
	                            </div>
	                            <div class="img">
	                                <a href="${ctx}/Product?action=queryProductDeatil&id=${pc3.id}">
	                                    <img src="${ctx}/files/${pc3.fileName}" width="185"  height="155"/>
	                                </a>
	                            </div>
	                        </li>
	                      </c:if>
	                      </c:forEach>
	                </ul>
	            </div>
	            <div class="fresh_right">
	                <ul>
	                    <li><a href="#"><img src="${ctx}/statics/images/fre_b1.jpg" width="260" height="220"/></a></li>
	                    <li><a href="#"><img src="${ctx}/statics/images/fre_b2.jpg" width="260" height="220"/></a></li>
	                </ul>
	            </div>
	        </div>
        </c:if>
    </c:forEach>
<!-- 		End 进口 生鲜 End -->
		

		<%@include file="/common/pre/footer.jsp"%>
	</div>

</body>
</html>
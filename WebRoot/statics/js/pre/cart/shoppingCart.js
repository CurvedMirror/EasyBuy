/**
 * 商品详情页添加到购物车
 */
function addCart(){
    var entityId=$("input[name='entityId']").val();
    var quantity=$("input[name='quantity']").val();
    //添加到购物车
    addCartByParam(entityId,quantity);
}
/**
 * 添加购物车
 */
function addCartByParam(productId, quantity) {
	$.post("Cart", "action=addItems&productId=" + productId + "&quantity="
			+ quantity, function(data) {
		if (data.status == 1) {
			showMessage(data.message);
			refreshCart();
		} else {
			showMessage(data.message);
		}
	}, "json")
}
/**
 * 刷新购物车
 */
function refreshCart() {
	$("#searchBar").load("Cart", "action=refreshCart");
}
/**
 * 结算 加载购物车列表
 */
function settlement1() {
	$("#settlement").load("Cart","action=toSettlement1");
}
/**
 * 去结算
 */
function settlement2() {
	$("#settlement").load("Cart","action=toSettlement2");
}
/**
 * 确认订单
 */
function settlement3() {
	//判断地址
    var addressId=$("input[name='selectAddress']:checked").val();
    var newAddress=$("input[name='address']").val();
    var newRemark=$("input[name='remark']").val();
    if(addressId=="" || addressId==null){
        showMessage("请选择收货地址");
        return;
    }else if(addressId=="-1"){
        if(newAddress=="" || newAddress==null){
            showMessage("请填写新的收货地址");
            return;
        }else if(newAddress.length<=2 || newAddress.length>=50){
            showMessage("收货地址为2-50个字符");
            return;
        }
    }
    $("#settlement").load("Cart","addressId="+addressId+"&newAddress="+newAddress+"&newRemark="+newRemark+"&action=toSettlement3");
    
}
/**
 * 商品详情页的 数量加
 * 
 * @param obj
 * @param entityId
 */
function addQuantity(obj,entityId,stock){
	// 获取商品数量
	var quantity = getPCount(obj) + 1;
	
	if(quantity>stock){
		showMessage("商品数量不足");
		return;
	}
	 modifyCart(entityId,quantity,jq(obj));
}
/**
 * 减去 数量减
 * 
 * @param obj
 * @param entityId
 */
function subQuantity(obj,entityId){
	var quantity = getPCount(obj) - 1;
	
	if(quantity < 1){
		return;
	}
	 modifyCart(entityId,quantity,jq(obj));
}
/**
 * 直接输入数量
 * @param obj
 * @param entityId
 */
function changeQuantity(obj,entityId,stock) {
	var quantity = getPCount(obj);
	if(quantity>stock){
		showMessage("商品数量不足");
		return;
	}
	if(quantity<1){
		quantity =1;
	}
	modifyCart(entityId,quantity,jq(obj));
	
	
}
/**
 * 修改购物车列表
 * 
 * @param entityId
 * @param quantity
 */
function modifyCart(entityId,quantity,obj){
	$.post("Cart","entityId="+entityId+"&quantity="+quantity+"&action=modifyCart",function(data){
		var result = eval("(" + data + ")");
		  if (result.status == 1) {
			  obj.parent().find(".car_ipt").val(quantity);
              settlement1();
		  }else{
         	 	showMessage(result.message);
          }
	})
}
/**
 * 删除购物车
 * 
 * @param entityId
 */
function removeCart(entityId){
	$.post("Cart","action=modifyCart&entityId="+entityId,function(data){
		var result = eval("(" + data + ")");
		 if (result.status == 1) {
			 
             settlement1();
		  }else{
        	 	showMessage(result.message);
         }
	});
}
/**
 * 获取数量
 * 
 * @param obj
 * @returns
 */
function getPCount(obj){
	return parseInt($(obj).parent().find(".car_ipt").val());
}

$(function() {
	/**
	 * 删除商品
	 */
	$(".del").click(function() {
		var node = $(this);
		var id = $(this).attr("value");
		$.post(contextPath+"/admin/product","action=deleteById&id="+id,function(data){
			showMessage(data.message);
			if(data.status==1){
				node.parent().parent().remove();
			}
		},"json");
	})
})
/**
 * 删除该商品信息
 * @param id
 */
function deleteById(id) {
	var bool=window.confirm("确认删除此商品信息么?");
	if(bool){
		$.ajax({
	        url: contextPath + "/admin/product",
	        method: "post",
	        data: {
	            id: id,
	            action: "deleteById"
	        },
	        success: function (jsonStr) {
	            var result = eval("(" + jsonStr + ")");
	            if (result.status == 1) {
	                window.location.reload();
	            }
	        }
	    });
	}
}
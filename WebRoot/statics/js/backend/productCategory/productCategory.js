$(function() {
	/**
	 * 删除商品
	 */
	$(".del").click(
			function() {
				var node = $(this);
				var id = $(this).attr("value");
				$.post(contextPath + "/admin/productCategory",
						"action=delProductCategory&id=" + id, function(data) {
							showMessage(data.message);
							if (data.status == 1) {
								node.parent().parent().remove();
							}
						}, "json");
			});
	/**
	 * 修改
	 */
	$("[name=select]").click(function() {
		toUpdateProductCategoryList($(this));
	});
})

// 选择商品分类级别
function selectProductCategoryLevel(obj) {
	var level = $(obj).val();
	if (level == 1) {
		$('#productCategoryLevel1').parent().parent().hide();
		$('#productCategoryLevel2').parent().parent().hide();
	} else if (level == 2) {
		$('#productCategoryLevel1').parent().parent().show();
		$('#productCategoryLevel2').parent().parent().hide();
	} else {
		$('#productCategoryLevel1').parent().parent().show();
		$('#productCategoryLevel2').parent().parent().show();
	}
}

/**
 * 点击单选按钮 修改
 */
function toUpdateProductCategoryList(obj) {
	var id = $(obj).val();
	$.ajax({
		url : contextPath + "/admin/productCategory",
		method : "post",
		data : {
			action : "toUpdateProductCategory",
			id : id
		},
		success : function(jsonStr) {
			$("#addProductCategory").html(jsonStr);
		}
	});
}
// 分类添加
function toAddProductCategory() {
	$.ajax({
		url : contextPath + "/admin/productCategory",
		method : "post",
		data : {
			action : "toAddProductCategory"
		},
		success : function(jsonStr) {
			$("#addProductCategory").html(jsonStr);
			$("#productCategoryLevel1").parent().parent().hide();
		}
	});
}
//查询下级分类
function queryProductCategoryList(obj, selectId) {
	 var parentId = $(obj).val();
	$.post( contextPath + "/admin/productCategory","action=queryProductCategoryList&parentId="+parentId,function(result){
	 
		var options = "<option value='-1'>" + "请选择..." + "</option>";
        for (var i = 0; i < result.data.length; i++) {
            var option = "<option value=" + result.data[i].id + ">" + result.data[i].name + "</option>";
            options = options + option;
        }
        $("#" + selectId).html(options);
	},"json")
}

//修改
function saveOrUpdate() {
	var id = $("#id").val(); //获取商品id
	var type = $("#type").val();
	var pro1 = $("#productCategoryLevel1").val();
	var pro2 = $("#productCategoryLevel2").val();
	
	
	if(type=="-1"){
		showMessage("请选择分类级别");
		return false;
	}else if(type=="2"){
		if (pro1=="-1"||pro1=="") {
			showMessage("请选择一级分类");
			return false;
		}
	}else if(type=="3"){
		if (pro1=="-1"||pro1=="") {
			showMessage("请选择一级分类");
			return false;
		}else if (pro2=="-1"||pro2=="") {
			showMessage("请选择二级分类");
			return false;
		}
	}
	
    modifyProductCategory(id);
}

$("input[type='file']").change(function(){   
		var file = this.files[0];
		if (window.FileReader) {    
		var reader = new FileReader();
			reader.readAsDataURL(file);    
			reader.onloadend = function(e){
				$("#img").attr("src",e.target.result);
				$("#img").css("display","block");
			}
		}
		
});


/**
 * 修改或新增
 */
function modifyProductCategory(id) {
	var productCategoryLevel1 = $("#productCategoryLevel1").val();
	var productCategoryLevel2 = $("#productCategoryLevel2").val();
	var name = $("#name").val();
	var type = $("#type").val();
	
	$.ajax({
	    url: contextPath + "/admin/productCategory",
	    method:"post",
		data:{
			  action: "modifyProductCategory",
	          name: name,
	          type: type,
	          productCategoryLevel1:productCategoryLevel1,
	          productCategoryLevel2:productCategoryLevel2,
	          id:id
		},
		success: function (jsonStr) {
			 var result = eval("(" + jsonStr + ")");
	            //状态判断
			 if (result.status == 1) {
				showMessage("操作成功");
				$("#addProductCategory").html("");
	         }
		}
	});
}

//修改
function toUpdateProductCategoryList(obj) {
    var id = $(obj).val();
    $.ajax({
        url: contextPath + "/admin/productCategory",
        method: "post",
        data: {
            action: "toUpdateProductCategory",
            id: id
        },
        success: function (jsonStr) {
            $("#addProductCategory").html(jsonStr);
        }
    });
}


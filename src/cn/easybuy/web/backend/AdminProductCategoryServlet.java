package cn.easybuy.web.backend;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.web.AbstractServlet;

/**
 * Created by bdqn on 2016/5/8.
 */
@WebServlet(urlPatterns = { "/admin/productCategory" }, name = "adminProductCategory")
public class AdminProductCategoryServlet extends AbstractServlet{

	private ProductCategoryService productCategoryService ;
	
	@Override
	public void init() throws ServletException {
		productCategoryService = new ProductCategoryServiceImpl();
	}
	/**
	 * 跳转到商品管理展示页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String currentPageStr =request.getParameter("currentPage");
		int currentPage = EmptyUtils.isNotEmpty(currentPageStr)?Integer.parseInt(currentPageStr):1;
		int rowCount = productCategoryService.getRowCount();
		Pager pager = new Pager(rowCount,6,currentPage);
		pager.setUrl("admin/productCategory?action=index");
		List<ProductCategory> productCategoryList =productCategoryService.queryAllProductCategory(pager);
		request.setAttribute("productCategoryList", productCategoryList);
		request.setAttribute("menu", 4);
		request.setAttribute("pager", pager);
		return "/backend/productCategory/productCategoryList";
	}

	/**
	 * 添加商品分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 public String toAddProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
	        //查询一级分类 全部
	        List<ProductCategory> productCategoryList=productCategoryService.queryCategory(1);
	        request.setAttribute("productCategoryList1",productCategoryList);
	        request.setAttribute("productCategory",new ProductCategory());
	        return "/backend/productCategory/toAddProductCategory";
	    }
	
	public ReturnResult delProductCategory(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnResult result = new ReturnResult();
		int id = Integer.parseInt(request.getParameter("id"));
		int subSetsCount = productCategoryService.querySubSetsSum(id);
		if (subSetsCount==0) {
			int ok = productCategoryService.delProductCategoryById(id);
			if (ok>0) {
				result.returnSuccess("删除成功");
			}else {
				result.returnSuccess("删除失败");
			}
		}else{
			result.returnFail("已存在子类别");
		}
		return result;
	}
	/**
	 * 添加或修改商品分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ReturnResult modifyProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	ReturnResult result=new ReturnResult();
    	int parentId = 0;
        int id=request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String productCategoryLevel1 = request.getParameter("productCategoryLevel1");
        String productCategoryLevel2 = request.getParameter("productCategoryLevel2");
        String type=request.getParameter("type");  
        
        if(type.equals("1")){
            parentId =0;
        }else if(type.equals("2")){
            parentId =Integer.parseInt(productCategoryLevel1);
        }else{
            parentId =Integer.parseInt(productCategoryLevel2);
        }
        
        ProductCategory productCategory  =new ProductCategory();
        productCategory.setId(id);
        productCategory.setParentId(parentId);
        productCategory.setName(name);
        productCategory.setType(Integer.parseInt(type));
        
        if(id==0){
        	  productCategoryService.addProductCategory(productCategory);
        }else{
        	  productCategoryService.updateProductCategory(productCategory);
        }
      
        return result.returnSuccess();
    }
    /**
     * 查询子分类
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ReturnResult queryProductCategoryList(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	 ReturnResult result=new ReturnResult();
    	 int parentId =Integer.parseInt(request.getParameter("parentId"));
    	 List<ProductCategory> list = productCategoryService.queryByParenId(parentId);
    	 result.setData(list);
    	 return result.returnSuccess();
    }
    /**
     * 修改商品分类
     * @param request
     * @param response
     * @return
     */
    public String toUpdateProductCategory(HttpServletRequest request,HttpServletResponse response)throws Exception{
       //首先根据id查询出分类  如果分类的类别== 2 查询出1级   如果类别是3  查询出一级二级
    	
    	String id = request.getParameter("id");
    	ProductCategory productCategory = productCategoryService.getById(Integer.parseInt(id));
    	List<ProductCategory> productCategoryList1 = new ArrayList<ProductCategory>();
    	List<ProductCategory> productCategoryList2 = new ArrayList<ProductCategory>();
    	
    	if(productCategory.getType()>=2){
    		productCategoryList1 = productCategoryService.queryCategory(1);
    	}
    	if(productCategory.getType() == 3){
    		productCategoryList2 = productCategoryService.queryCategory(2);
    		request.setAttribute("parentProductCategory",productCategoryService.getById(productCategory.getParentId()));
    	}
    	request.setAttribute("productCategory",productCategory);
    	request.setAttribute("productCategoryList1", productCategoryList1);
    	request.setAttribute("productCategoryList2", productCategoryList2);
    	return "/backend/productCategory/toAddProductCategory";
    }
    @Override
    public Class getServletClass() {
        return AdminProductCategoryServlet.class;
    }
}

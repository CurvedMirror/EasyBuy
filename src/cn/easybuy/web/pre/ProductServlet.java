package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.Product;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductParams;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.web.AbstractServlet;

@WebServlet (urlPatterns={"/Product"},name="Product")
public class ProductServlet extends AbstractServlet {

	private ProductService productService;
	private ProductCategoryService productCategoryService;
	private ProductParams params;
	
	public void init() throws ServletException {
		productService = new ProductServiceImpl();
		productCategoryService = new ProductCategoryServiceImpl();
		params = new ProductParams();
	}
	
	@Override
	public Class getServletClass() {
		return ProductServlet.class;
	}

	public String queryProductList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId= request.getParameter("categoryId");
		String keyWord= request.getParameter("keyWord");
		String currentPageStr= request.getParameter("currentPage");
		String pageSizeStr=request.getParameter("pageSize");
		
		
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 4:Integer.parseInt(pageSizeStr);
	    int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
	  
	    params.setCategoryId(EmptyUtils.isEmpty(categoryId)? null:Integer.parseInt(categoryId));
	    params.setKeyWords(keyWord);
	    params.openPage((currentPage - 1) * rowPerPage, rowPerPage);
	    
	    int total = productService.queryProductCount(params);
	    Pager pager = new Pager(total, rowPerPage, currentPage);
	    pager.setUrl("/Product?action=queryProductList&categoryId="+(EmptyUtils.isNotEmpty(categoryId)?categoryId:"")+"&keyWord="+(EmptyUtils.isNotEmpty(keyWord)?keyWord:""));
	    List<Product> pList = productService.queryProductList(params);
	    request.setAttribute("pList", pList);
	    request.setAttribute("pager", pager); //用来分页
	    request.setAttribute("total", total); //发现了多少件
	    request.setAttribute("keyWord", keyWord);
	    
	    return "/pre/product/queryProductList";
	}

	public String queryProductDeatil(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("id");
        Product product = productService.getDetailById(Integer.parseInt(id));
        request.setAttribute("product", product);
        return "/pre/product/productDeatil";
    }
	
}

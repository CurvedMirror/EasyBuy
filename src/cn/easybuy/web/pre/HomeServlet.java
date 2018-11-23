package cn.easybuy.web.pre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.News;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductParams;
import cn.easybuy.service.news.NewsService;
import cn.easybuy.service.news.NewsServiceImpl;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/Home" }, name = "Home")
public class HomeServlet extends AbstractServlet {
	
	private ProductCategoryService pcService;
	private NewsService newsService;
	private ProductParams params;
	private ProductService productService;
	
	public void	init() throws ServletException{
		pcService = new ProductCategoryServiceImpl();
		newsService = new NewsServiceImpl();
		params = new ProductParams();
		productService = new ProductServiceImpl();
	}
	
	public String index(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		List<ProductCategoryVo> pcList = pcService.queryAllProductCategory();
		List<News> newsList = newsService.queyrAllNews();
		List<ProductCategory> pcList= pcService.queryAllProductCategory(null);
		List<Product> products = productService.getAllProduct(null);
		
		req.getSession().setAttribute("pcList",pcList);
		req.setAttribute("newsList", newsList);
		req.setAttribute("products", products);
		
		
        return "/pre/index";
	}

	@Override
	public Class getServletClass() {
		
		return HomeServlet.class;
	}
}

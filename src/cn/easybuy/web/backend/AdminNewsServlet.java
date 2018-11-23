package cn.easybuy.web.backend;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.News;
import cn.easybuy.service.news.NewsService;
import cn.easybuy.service.news.NewsServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/news" }, name = "adminNews")
public class AdminNewsServlet extends AbstractServlet {
	private NewsService newsService;
	private ProductService productService;

	public void init() throws ServletException {
		newsService = new NewsServiceImpl();
		productService = new ProductServiceImpl();
	}

	/**
	 * 分页查询新闻资讯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryNewsList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int rowPerPage = newsService.getRowPerPage();//获得总记录数
		String currentPageStr =request.getParameter("currentPage");
		int currentPage = EmptyUtils.isNotEmpty(currentPageStr)?Integer.parseInt(currentPageStr):1;
		Pager pager = new Pager(rowPerPage,8,currentPage);
		pager.setUrl("news?action=queryNewsList");
		List<News> newsList = newsService.queyrAllNews(pager);
		request.getSession().setAttribute("newsList", newsList);
		request.getSession().setAttribute("pager", pager);
		request.setAttribute("menu", 7);
		return "/backend/news/newsList";
	}
	
	public String newsDeatil(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String idStr = request.getParameter("id");
		int id = EmptyUtils.isEmpty(idStr)?0:Integer.parseInt(idStr);
		News news = newsService.getDeatil(id);
		request.setAttribute("news", news);
		return "/backend/news/newsDetail";
	}

	@Override
	public Class getServletClass() {
		return AdminNewsServlet.class;
	}

}

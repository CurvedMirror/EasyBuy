package cn.easybuy.service.news;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.news.NewsDao;
import cn.easybuy.dao.news.NewsDaoImpl;
import cn.easybuy.entity.News;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class NewsServiceImpl implements NewsService {
	Connection conn;
	NewsDao newsDao;

	@Override
	public List<News> queyrAllNews()  {
		List<News> list = new ArrayList<News>();
		try {
			conn = DataSourceUtil.getConnection();
			newsDao = new NewsDaoImpl(conn);
			list= newsDao.queyrAllNews();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return list;
	}
	
	
	@Override
	public List<News> queyrAllNews(Pager pager) {
		List<News> list = null;
		try {
			conn = DataSourceUtil.getConnection();
			newsDao = new NewsDaoImpl(conn);
			list = newsDao.queyrAllNews(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return list;
	}

	@Override
	public int getRowPerPage() {
		int rowPerPage= 0;
		try {
			conn = DataSourceUtil.getConnection();
			newsDao = new NewsDaoImpl(conn);
			rowPerPage = newsDao.getRowPerPage();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return rowPerPage;
	}
	
	@Override
	public News getDeatil(int id) {
		News  news =null;
		try {
			conn = DataSourceUtil.getConnection();
			newsDao = new NewsDaoImpl(conn);
			news = newsDao.getDeatil(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return news;
	}
}

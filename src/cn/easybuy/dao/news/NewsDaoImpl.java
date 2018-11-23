package cn.easybuy.dao.news;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.News;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class NewsDaoImpl extends BaseDao implements NewsDao {
	public NewsDaoImpl(Connection conn) {
		super(conn);
	}
	
	public News tableToClass(ResultSet rs) throws Exception{
		News news = new News();
		news.setId(rs.getInt("id"));
		news.setContent(rs.getString("content"));
		news.setTitle(rs.getString("title"));
		news.setCreateTime(rs.getString("createTime"));
		return news;
	}
	@Override
	public List<News> queyrAllNews() throws Exception {
		List<News> newsList = new ArrayList<News>();
		StringBuffer sql = new StringBuffer("SELECT * FROM easybuy_news");
		sql.append(" limit 0,5");
		rs = super.executeQuery(sql.toString());
		while(rs.next()){
			News news = new News();
			news=tableToClass(rs);
			newsList.add(news);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return newsList;
	}
	
	@Override
	public List<News> queyrAllNews(Pager p) throws Exception {
		List<News> list = new ArrayList<News>();
		String sql = "SELECT * FROM `easybuy_news` ORDER BY createTime DESC LIMIT ?,?";
		rs = executeQuery(sql, (p.getCurrentPage()-1)*p.getRowPerPage(),p.getRowPerPage());
		while(rs.next()){
			News news = new News();
			news=tableToClass(rs);
			list.add(news);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}
	
	@Override
	public int getRowPerPage() throws Exception {
		int rowPerPage = 0;
		String sql ="SELECT COUNT(*) FROM `easybuy_news` ";
		rs=executeQuery(sql);
		while(rs.next()){
			rowPerPage = rs.getInt(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return rowPerPage;
	}
	
	@Override
	public News getDeatil(int id) throws Exception {
		News news = null;
		String sql = "SELECT * FROM `easybuy_news` WHERE id=?";
		rs = executeQuery(sql, id);
		while(rs.next()){
			news = new News();
			news = tableToClass(rs);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return news;
	}
}

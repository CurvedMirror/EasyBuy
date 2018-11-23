package cn.easybuy.dao.news;

import java.util.List;

import cn.easybuy.entity.News;
import cn.easybuy.utils.Pager;

public interface NewsDao {
	/**
	 * 查询首页新闻资讯
	 * @return
	 * @throws Exception
	 */
	List<News> queyrAllNews() throws Exception;
	/**
	 * 分页查询新闻资讯
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	List<News> queyrAllNews(Pager pager) throws Exception;
	/**
	 * 查询总记录数
	 * @return
	 */
	int getRowPerPage() throws Exception;
	
	/**
	 * 根据id查询资讯详情
	 * @param id
	 * @return
	 */
	News getDeatil(int id) throws Exception;
}

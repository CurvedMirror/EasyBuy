package cn.easybuy.service.news;

import java.util.List;

import cn.easybuy.entity.News;
import cn.easybuy.utils.Pager;

public interface NewsService {
	/**
	 * 查询首页新闻资讯
	 * @return
	 * @throws Exception
	 */
	List<News> queyrAllNews();
	/**
	 * 分页查询新闻资讯
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	List<News> queyrAllNews(Pager pager);
	/**
	 * 查询总记录数
	 * @return
	 */
	int getRowPerPage();
	/**
	 * 根据id查询资讯详情
	 * @param id
	 * @return
	 */
	News getDeatil(int id);
}

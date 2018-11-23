package cn.easybuy.utils;

import java.io.Serializable;


public class Pager implements Serializable{
	private int currentPage;//当前页
	private int rowCount;//总条数
	private int rowPerPage;//每页显示条数
	private int pageCount;//总页数
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Pager(int rowCount, int rowPerPage, int currentPage) {
		this.rowCount = rowCount;
		this.rowPerPage = rowPerPage;
		this.currentPage = currentPage;
		this.pageCount = rowCount%rowPerPage==0?rowCount/rowPerPage:rowCount/rowPerPage+1;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}
	public Pager() {
		// TODO Auto-generated constructor stub
	}
}

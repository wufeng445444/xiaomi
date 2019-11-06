package com.wf.utils;

 //��ҳ��βҳ����һҳ����һҳ����ҳ������ʾ��ǰҳ�롢������������ҳ��

public class PageTool {
	private int pageSize;
	private int totalCount;
	private int currentPage;
	private int lastPage;
	private int nextPage;
	private int pageCount;
	private int startIndex;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public PageTool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageTool(int totalCount, String  currentPage) {
		super();
		
		//�ȳ�ʼ����ҳ�����ڳ�ʼ����һҳ
		this.totalCount = totalCount;//��ʼ����������
		pageCount=5;//��ʼ��ÿҳ������
		initialCurrentPage(currentPage) ;//��ʼ����ǰҳ��
		//��ʼ����ҳ��
		initialPageSize();
		initialLastPage();//��ʼ����һҳ
		initialNextPage();//��ʼ����һҳ
		//��ʼ����ʼ�±�
		initialStartIndex();
		
	}
	private void initialNextPage() {
		if (currentPage == pageSize) {
			nextPage = currentPage;
		} else {
			nextPage = currentPage + 1;
		}
	}
	private void initialStartIndex() {
		startIndex=(currentPage-1)*pageCount;
	}
	private void initialPageSize() {
		pageSize=totalCount/pageCount+(totalCount%pageCount==0?0:1);
		
	}
	
	private void initialLastPage() {
		if (currentPage==1) {
			lastPage=1;
		} else {
			lastPage=currentPage-1;
		}
	}
	@Override
	public String toString() {
		return "PageTool [pageSize=" + pageSize + ", totalCount=" + totalCount + ", currentPage=" + currentPage
				+ ", lastPage=" + lastPage + ", nextPage=" + nextPage + ", pageCount=" + pageCount + ", startIndex="
				+ startIndex + "]";
	}
	private void initialCurrentPage(String  currentPage) {
			if (currentPage == null||"".equals(currentPage)) {
				this.currentPage=1;
			} else {
				this.currentPage=Integer.valueOf(currentPage);
			}
		
	}
	
	
	
}

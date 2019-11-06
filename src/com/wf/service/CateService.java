package com.wf.service;

import java.util.List;

import com.wf.dao.CateDao;
import com.wf.entity.Category;
import com.wf.utils.PageTool;

public class CateService {
	private CateDao cateDao=new CateDao();

	public int selectCateCount() {
		return cateDao.selectCateCount();
	}

	

	public List<Category> findAllCate(PageTool pageTool) {
		return cateDao.findAllCate(pageTool);
		
	}

	public boolean insertCate(Category category) {
		 int row=cateDao.intsertCate(category);
		 if (row>0) {
				return true;
		} else {
			return false;
		}
	}



	public Category findCateById(String cid) {
		return cateDao.findCateById(cid) ;
	}



	


	public boolean updateCate(Category category) {
		int row=cateDao.updateCate(category);
		 if (row>0) {
				return true;
		} else {
			return false;
		}
	}



	public boolean deleteCate(String ids) {
		int row=cateDao.deleteUser(ids); 
		if (row>0) {
			return true;
		} else {
			return false;
		}
	
	}



	public List<Category> findAllCate() {
		return cateDao.findAllCate();
	}






	public List<Category> findAllCate(int count) {
		return cateDao.indexInfo(count);
	}


}

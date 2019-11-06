package com.wf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wf.entity.Category;
import com.wf.utils.PageTool;

public class CateDao {
private QueryRunner qRunner=new QueryRunner(new ComboPooledDataSource());

public int selectCateCount() {
	int count=0;
	
	try {
		long c = (long)qRunner.query("select count(*) from category ",new ScalarHandler());
		count=(int)c;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return count;
}

public List<Category> findAllCate(PageTool pageTool) {
	List<Category> cates=null;
	try {
		cates=qRunner.query("select * from   category  limit ?,?",new BeanListHandler<Category>(Category.class),pageTool.getStartIndex(),pageTool.getPageCount());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return cates;
}

public int intsertCate(Category category) {
	
	int row=0;
	try {
		row=qRunner.update("insert into category values(null,?,?,?,?,?)",
				category.getCname(),category.getState(),
				category.getOrder_number(),category.getDescription(),category.getCreate_time());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return row;
	
}

public Category findCateById(String cid) {
	Category category=null;
	try {
		category=qRunner.query("select * from   category  where cid=?",new BeanHandler<Category>(Category.class),cid);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return category;
}

public int updateCate(Category category) {
	int row=0;
	try {
		row=qRunner.update("update category set cname=?,state=?,order_number=?,"
	+"description=?,create_time=? where cid=?",
				category.getCname(),category.getState(),
				category.getOrder_number(),category.getDescription(),category.getCreate_time(),category.getCid());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return row;
}

public int deleteUser(String ids) {
	int row =0;
	
	try {
		row=qRunner.update("delete from category where cid in ("+ids+")");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



return row;
}

public List<Category> findAllCate() {
	List<Category> cates=null;
	try {
		cates=qRunner.query("select * from category where state =1", new BeanListHandler<Category>(Category.class));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return cates;
}

public List<Category> indexInfo(int count) {
	List<Category> cates=null;
	try {
		cates=qRunner.query("select * from category where state=1 order by order_number limit ?",new BeanListHandler<Category>(Category.class),count);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return cates;
}}



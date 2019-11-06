package com.wf.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wf.entity.LinkModel;

public class LinkSelect {
	
public static void main(String[] args) {
	QueryRunner qRunner=new QueryRunner(new ComboPooledDataSource());
	try {
		List<LinkModel> models=qRunner.query("select gname,cname,color from goods inner join category on goods.cid= category.cid" ,
				new BeanListHandler<LinkModel>(LinkModel.class));
			for (LinkModel linkModel : models) {
				System.out.println(linkModel);
			}
	
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}

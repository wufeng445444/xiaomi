package com.wf.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wf.entity.Orders;

public class OrdersDao {
private QueryRunner qRunner=new QueryRunner(new ComboPooledDataSource());

public int createOrders(Orders orders) {
		int row =0;
		try {
			row=qRunner.update("insert into orders values(?,?,?,?,?,?,0)",
					orders.getOrders_number(),orders.getUid(),orders.getSumPrice(),orders.getGoodsCount(),orders.getOrdersName(),orders.getCreate_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return row;
		
		
}

public int updateOrders(String orders_number) {
	int row=0;
	try {
		row=qRunner.update("update orders set state=1 where orders_number=?", orders_number);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return row;
}
}

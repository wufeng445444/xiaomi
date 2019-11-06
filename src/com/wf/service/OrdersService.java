package com.wf.service;

import com.wf.dao.OrdersDao;
import com.wf.entity.Orders;

public class OrdersService {
	private OrdersDao ordersDao=new OrdersDao();

	public boolean createOrders(Orders orders) {
		int row =ordersDao.createOrders(orders);
		if (row>0) {
			return true;
		}
		return false;
	}

	public boolean updateOrders(String orders_number) {
		int row =ordersDao.updateOrders(orders_number);
		if (row>0) {
			return true;
		}
		return false;
	}
}

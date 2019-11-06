package com.wf.service;

import java.util.List;

import com.wf.dao.GoodsDao;
import com.wf.dao.TrolleyDao;
import com.wf.entity.Goods;
import com.wf.entity.Trolley;
import com.wf.entity.User;

public class TrolleyService {
	private TrolleyDao trolleyDao=new TrolleyDao();
	private GoodsDao goodsDao=new  GoodsDao();
	public boolean addTrolley(Trolley trolley) {
		Trolley t=trolleyDao.confirmUsExist(trolley);
		if (t==null) {
			trolley.setNumber(1);
			trolleyDao.addTrolley(trolley);
			return false;
		} else {
			t.setNumber(t.getNumber()+1);
			trolleyDao.updateTrolley(t);
			return true;
		}
	}

	public int selectTrolleyCount(Integer uid) {
		
		return trolleyDao.selectTrolleyCount(uid);
	}

	public List<Trolley> findAllTrolly(User user) {
		List<Trolley> trolleys=trolleyDao.findAllTrolly(user);
		for (Trolley trolley : trolleys) {
			trolley.setUser(user);
			Goods goods = goodsDao.findGoodsById(trolley.getGid()+"");
			trolley.setGoods(goods);
		}
		return trolleys;
	}

	public boolean addOrDeleteNumber(String tid, String number) {
		int row=trolleyDao.addOrDeleteNumber(tid,number);
		if (row>0) {
			return true;
		} else {
			return false;
		}
		
	}

	public boolean deleteTrolley(String tid) {
		int row =trolleyDao.deleteTrolley(tid);
		if (row>0) {
			return true;
		}
		return false;
	}

	public boolean updateTrolley(String orders_number, Integer uid) {
		int row=trolleyDao.updateTrolley(orders_number,uid);
		if (row>0) {
			return true;
		} else {
			return false;
		}
		
	}
}

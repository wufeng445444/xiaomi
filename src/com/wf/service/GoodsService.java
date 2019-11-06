package com.wf.service;

import java.util.List;

import com.wf.dao.GoodsDao;
import com.wf.entity.Category;
import com.wf.entity.Goods;
import com.wf.utils.PageTool;

public class GoodsService {
private GoodsDao goodsDao=new GoodsDao(); 
private CateService cateService=new CateService();

	public boolean insertGoods(Goods goods) {
		
		 int row=goodsDao.insertGoods(goods);
		if (row>0) {
			return true;
		}
		return false;
	}
	public int selectGoodsCount() {
		return goodsDao.selectGoodsCount();
	}
	public List<Goods> findAllGoods(PageTool pageTool) {
	
		List<Goods> goodes= goodsDao.findAllGoods(pageTool);
		for (Goods goods : goodes) {
			Category category=cateService.findCateById(String.valueOf(goods.getCid()));
			goods.setCategory(category);
		} 
		
		return goodes;
	}
	public Goods findGoodsById(String gid) {
		return goodsDao.findGoodsById(gid);
	}
	public boolean updateGoods(Goods goods) {
		int row=goodsDao.updateGoods(goods);
		if (row>0) {
			return true;
		}
		return false;
	}
	public boolean deleteGoods(String ids) {
		int row=goodsDao.deleteGoods(ids);
		if (row>0) {
			return true;
		}
		return false;
	}
	public List<Goods> findGoodsByState(int state, int count) {
		return goodsDao.findGoodsByState(state,count);
	}
	public List<Goods> findGoodsByCate(int cid, int count) {
		return goodsDao.findGoodsByCate(cid,count);
	}
	public List<Goods> findGoodsBySearch(String info, int count) {
		return goodsDao.findGoodsBySearch(info,count);
	}
	
}

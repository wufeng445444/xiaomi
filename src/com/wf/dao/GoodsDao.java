package com.wf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wf.entity.Goods;
import com.wf.utils.PageTool;

public class GoodsDao {
private QueryRunner qRunner=new QueryRunner(new ComboPooledDataSource());
	public int insertGoods(Goods goods) {
		int row=0;
		try {
			row=qRunner.update("insert into goods values(null,?,?,?,?,?,?,?,?,?,?,?)",
					goods.getCid(),goods.getGname(),goods.getColor(),goods.getSize(),goods.getPrice(),goods.getDescription(),goods.getFull_description(),
					goods.getPic(),goods.getState(),goods.getVersion(),goods.getProduct_date());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}
	public int selectGoodsCount() {
		int count=0;
		long c=0;
		try {
			c = (long)qRunner.query("select count(*) from goods ", new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count=(int)c;
		return count;
	}
	public List<Goods> findAllGoods(PageTool pageTool) {
		List<Goods> goods=null;
		try {
			goods=qRunner.query("select * from goods limit ?,?", new BeanListHandler<Goods>(Goods.class),pageTool.getStartIndex(),pageTool.getPageCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}
	public Goods findGoodsById(String gid) {
		Goods goods=null;
		try {
			goods=qRunner.query("select * from goods where gid=?",new BeanHandler<Goods>(Goods.class),Integer.valueOf(gid));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}
	public int updateGoods(Goods goods) {
		int row=0;
		try {
			row=qRunner.update("update goods set cid=?,gname=?,color=?,size=?,"
					+ "price=?,description=?,full_description=?,pic=?,"
					+ "state=?,version=?,product_date=? where gid=?",
					goods.getCid(),goods.getGname(),goods.getColor(),goods.getSize(),goods.getPrice(),goods.getDescription(),goods.getFull_description(),
					goods.getPic(),goods.getState(),goods.getVersion(),goods.getProduct_date() ,goods.getGid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return row;
	}
	public int deleteGoods(String ids) {
		int row =0;
		
		try {
			row=qRunner.update("delete from goods where gid in ("+ids+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return row;
	}
	public List<Goods> findGoodsByState(int state, int count) {
		List<Goods> goods=null;
		try {
			goods=qRunner.query("select * from goods where state = ? order by gid desc limit ?",new BeanListHandler<Goods>(Goods.class),state,count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}
	public List<Goods> findGoodsByCate(int cid, int count) {
		List<Goods> goods=null;
		try {
			goods=qRunner.query("select * from goods where cid=? order by gid desc limit ?", new BeanListHandler<Goods>(Goods.class) ,cid,count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}
	public List<Goods> findGoodsBySearch(String info, int count) {
		//模糊查询肯定要做字符串拼接，sql语句字符串拼接
				/*
				 	select * from goods where gname like "%关键字1%"
				 	select * from goods where gname like "%关键字1%" or gname like "%关键字2%"
				 	select * from goods where gname like "%关键字1%" or gname like "%关键字2%" or gname like "%关键字3%"
				 */
		List<Goods> goods=null;
		StringBuilder sb=new StringBuilder("select * from goods where ");
		if (!info.contains("#")) {
			sb.append("gname like '%"+info+"%' ");
		}else {
			String[] split = info.split("#");
			for (int i = 0; i < split.length; i++) {
				if (i==0) {
					sb.append("gname like '%"+split[i]+"%' ");
				} else {
					sb.append("gname like '%"+split[i]+"%' ");
				}
			}
			
		}
		//把分页也拼接进去
		sb.append("limit "+count);
		try {
			goods=qRunner.query(sb.toString(),new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}	
	

}

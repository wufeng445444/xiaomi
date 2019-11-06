package com.wf.entity;

import java.util.Date;
import java.util.List;

public class Orders {
	private String orders_number;//订单编号
	private Integer uid;//关联用户，标识哪个用户的订单
	private User user;//虚拟对象，不会存入数据库，只是为了方便使用
	private Double sumPrice;//订单总价
	private String ordersName;//订单名称
	private Integer goodsCount;//商品件数
	private Date create_time;//订单的生成日期
	//0 待支付，1代发货（已支付） ，2已发货 ，3，运输中，4已签收
	private Integer state;//订单状态
	private List<Trolley> trolleys;//如果后期需要展示订单中的数据，需要将订单中的商品保存起来
	public String getOrders_number() {
		return orders_number;
	}
	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getOrdersName() {
		return ordersName;
	}
	public void setOrdersName(String ordersName) {
		this.ordersName = ordersName;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<Trolley> getTrolleys() {
		return trolleys;
	}
	public void setTrolleys(List<Trolley> trolleys) {
		this.trolleys = trolleys;
	}
	@Override
	public String toString() {
		return "Orders [orders_number=" + orders_number + ", uid=" + uid + ", user=" + user + ", sumPrice=" + sumPrice
				+ ", ordersName=" + ordersName + ", goodsCount=" + goodsCount + ", create_time=" + create_time
				+ ", state=" + state + ", trolleys=" + trolleys + "]";
	}
	public Orders(String orders_number, Integer uid, Double sumPrice, String ordersName, Integer goodsCount,
			Date create_time) {
		super();
		this.orders_number = orders_number;
		this.uid = uid;
		this.sumPrice = sumPrice;
		this.ordersName = ordersName;
		this.goodsCount = goodsCount;
		this.create_time = create_time;
	}
	
}

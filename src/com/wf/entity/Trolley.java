package com.wf.entity;

public class Trolley {
private Integer tid;//主键
private Integer uid;//用户id
private User user;//虚拟对象，这个属性不需要存储数据库，只是为了显示数据
private Integer gid;//商品id
private Goods goods;//虚拟对象，这个属性不需要存储数据库，只是为了显示数据
private Integer number;//商品数量
private String orders_number;//订单编号
public Integer getTid() {
	return tid;
}
public void setTid(Integer tid) {
	this.tid = tid;
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
public Integer getGid() {
	return gid;
}
public void setGid(Integer gid) {
	this.gid = gid;
}
public Goods getGoods() {
	return goods;
}
public void setGoods(Goods goods) {
	this.goods = goods;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public String getOrders_number() {
	return orders_number;
}
public void setOrders_number(String orders_number) {
	this.orders_number = orders_number;
}
@Override
public String toString() {
	return "Trolley [tid=" + tid + ", uid=" + uid + ", user=" + user + ", gid=" + gid + ", goods=" + goods + ", number="
			+ number + ", orders_number=" + orders_number + "]";
}
public Trolley() {
	super();
	// TODO Auto-generated constructor stub
}
public Trolley(Integer uid, Integer gid) {
	super();
	this.uid = uid;
	this.gid = gid;
}


}

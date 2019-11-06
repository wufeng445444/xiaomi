package com.wf.entity;

import java.util.Date;
import java.util.List;

public class Orders {
	private String orders_number;//�������
	private Integer uid;//�����û�����ʶ�ĸ��û��Ķ���
	private User user;//������󣬲���������ݿ⣬ֻ��Ϊ�˷���ʹ��
	private Double sumPrice;//�����ܼ�
	private String ordersName;//��������
	private Integer goodsCount;//��Ʒ����
	private Date create_time;//��������������
	//0 ��֧����1����������֧���� ��2�ѷ��� ��3�������У�4��ǩ��
	private Integer state;//����״̬
	private List<Trolley> trolleys;//���������Ҫչʾ�����е����ݣ���Ҫ�������е���Ʒ��������
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

package com.wf.entity;

public class LinkModel {
private String gname;
private String cname;
private String color;
public String getGname() {
	return gname;
}
public void setGname(String gname) {
	this.gname = gname;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
@Override
public String toString() {
	return "LinkModel [gname=" + gname + ", cname=" + cname + ", color=" + color + "]";
}

}

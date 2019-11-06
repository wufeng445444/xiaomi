package com.wf.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.wf.dao.UserDao;
import com.wf.entity.User;
import com.wf.utils.PageTool;
import com.wf.utils.SendMessage;

public class UserService {
private UserDao userDao=new UserDao();
	public boolean checkPhone(String phone) {
		User user =userDao.checkPhone(phone);
		if (user!=null) {
			return true;
		}
		return false;
	}
	public boolean checkUsername(String username) {
		User user =userDao.checkUsername(username);
		if (user!=null) {
			return true;
		}
		return false;
	}
	public boolean registUser(User user) {
		int row=userDao.registUser(user);
		if (row!=0) {
			return true;
		}
		return false;
	}
	public boolean createCode(String phone, HttpSession session) {
		int code =SendMessage.createCode(phone);
		String newCode=phone +"#"+code;
		if (code==0) {
			return false;
		} else {
			session.setAttribute("code", newCode );
			return true;
		}
		
	}
	public boolean validateCode(String phone, String code, HttpSession session) {
		String newCode =phone+"#"+code;
		String oldCode=(String)session.getAttribute("code");
		/*if (newCode.equals(oldCode)) {
			return true;
		}return false;*/
		//ͨ���ֻ��Ų��ҵ��û������û����뵽session��
		User user=userDao.findUserByPhone(phone);
		session.setAttribute("user", user);
		return true;
	
	}
	public boolean adminLogin(User user, HttpSession session) {
		User u=userDao.adminLogin(user);
		if (u!=null) {
			// �û����ڴ���session ��
			session.setAttribute("user",u);
			return true;
		}
		return false;
	}


	public int selectUserCount() {
		return userDao.selectUserCount();
	}
	public List<User> findAllUser(PageTool pageTool) {
		return userDao.findAllUser(pageTool);
	}
	public boolean updateManager(User user) {
		int row=userDao.updateManager(user); 
		if (row>0) {
			return true;
		} else {
			return false;
		}
	
	}
	public boolean deleteUser(String ids) {
		int row=userDao.deleteUser(ids); 
		if (row>0) {
			return true;
		} else {
			return false;
		}
	
	}

}

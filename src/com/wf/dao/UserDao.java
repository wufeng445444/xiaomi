package com.wf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wf.entity.User;
import com.wf.utils.PageTool;

public class UserDao {
	private QueryRunner qRunner=new QueryRunner(new ComboPooledDataSource());
	public User checkPhone(String phone) {
		User user=null;
		try {
			user=qRunner.query("select * from user where phone=?",new BeanHandler<User>(User.class),phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public User checkUsername(String username) {
		User user=null;
		try {
			user=qRunner.query("select * from user where username=?",new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public int registUser(User user) {
		int row=0;
		try {
			row=qRunner.update("insert into user(uname,gender,phone,area,username,password,photo,creat_time) values(?,?,?,?,?,?,?,?)",
					user.getUname(),user.getGender(),user.getPhone(),user.getArea(),user.getUsername(),user.getPassword(),user.getPhoto(),user.getCreat_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}

	public User adminLogin(User user) {
		User u=null;
		try {
			u=qRunner.query("select * from user where username=? and password=? and manager=0",new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	public List<User> findAllUser(PageTool pageTool) {
	List<User> users=null;
	try {
		users=qRunner.query("select * from user  limit ?,?",new BeanListHandler<User>(User.class),pageTool.getStartIndex(),pageTool.getPageCount());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return users;
	}
	public int selectUserCount() {
		int count=0;
		
		try {
			long c = (long)qRunner.query("select count(*) from user ",new ScalarHandler());
			count=(int)c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	public int updateManager(User user) {
		int row =0;
		try {
			//ÐÞ¸Ä
			row=qRunner.update("update  user set manager=? where uid=?  ",user.getManager(),user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return row;
	}
	public int deleteUser(String ids) {
		int row =0;
		
			try {
				row=qRunner.update("delete from user where uid in ("+ids+")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		
		return row;
	}
	public User findUserByPhone(String phone) {
		User user =null;
			try {
				user=qRunner.query("select * from user where phone = ?",new  BeanHandler<User>(User.class),phone);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return user;
	}
	
}

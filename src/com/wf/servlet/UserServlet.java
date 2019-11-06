package com.wf.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.wf.entity.User;
import com.wf.service.UserService;
import com.wf.utils.PageTool;
import com.wf.utils.UploadUtils;
@WebServlet("/user")
@MultipartConfig
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String func =request.getParameter("func");
		switch (func) {
			case "checkPhone":
					checkPhone(request,response);
					break;
			case "checkUsername":
					checkUsername(request,response);
					break;
			case "registUser":
					registUser(request,response);
					break;
			case "checkPhoneIsRegist":
					checkPhoneIsRegist(request,response);
					break;
			case "createCode":
					createCode(request,response);
					break;
			case "validateCode":
					validateCode(request,response);
					break;
			case "adminLogin":
					adminLogin(request,response);
					break;
			case "adminLogout":
					adminLogout(request,response);
					break;
			case "findAllUser":
					findAllUser(request,response);
					break;
			case "updateManager":
				updateManager(request,response);
				break;
			case "deleteUser":
				deleteUser(request,response);
				break;
			
			default:
					break;
		}
			
		}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String ids = request.getParameter("ids");
			boolean isSuccess=userService.deleteUser(ids);
			if (isSuccess) {
				response.sendRedirect("user?func=findAllUser");
			}
	}
	private void updateManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String uid = request.getParameter("uid");
			String manager = request.getParameter("manager");
			User user=new User(Integer.valueOf(uid), Integer.valueOf(manager));
			boolean isSuccess=userService.updateManager(user);
			if (isSuccess) {
				response.sendRedirect("user?func=findAllUser");
			}
			
	
	}
	//���ҵ������û�
	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  currentPage= request.getParameter("currentPage");
		 int totalCount =userService.selectUserCount();
		PageTool pageTool=new PageTool(totalCount, currentPage);
		List<User> users=userService.findAllUser(pageTool);
		request.setAttribute("users",users);
		request.setAttribute("pageTool",pageTool);
		request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
		
}
	//��̨�ǳ�
	private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("admin/login.jsp");
	}
	//��̨��¼
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	User user=new User(username,password);
	/*����½�����ݴ��뵽session��
	 * */
	HttpSession session = request.getSession();
	boolean isSuccess=userService.adminLogin(user,session);
		if (isSuccess) {
			//��½�ɹ�
			response.sendRedirect("admin/main.jsp");
		} else {
			//��½ʧ��
			request.setAttribute("msg", "�û���������������󣬻�����û�е�½Ȩ��");
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
			
		}

	}
	private void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone=request.getParameter("phone");
		String code=request.getParameter("code");
		HttpSession session = request.getSession();
		boolean	isSuccess=userService.validateCode(phone,code,session);
		response.getWriter().print(isSuccess);
	}
	private void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
		HttpSession session = request.getSession();
		boolean isSuccess=userService.createCode(phone,session);
		response.getWriter().print(isSuccess);
	}
	private void checkPhoneIsRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkPhone(request,response);
	}
	//��½���������ϴ��ļ���
	//�ϴ�ͷ�񣨲����ǹ�����ķ�װ��
	private void registUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡ�����ύ����Ϣ
		String uname = request.getParameter("uname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String area = request.getParameter("area");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	//	�Ȼ�ȡpath����
		String 	photo="";
			Part part=request.getPart("photo");
	//�ж�path�����Ƿ�Ϊ�գ����Ϊ�վ�ֱ�������ϴ�ͷ�񣬷���͵��ù������ϴ����ж������Ƿ�ƥ��
		if (part.getSize()==0) {
			request.setAttribute("msg","���ϴ�ͷ��");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}else {
			photo=UploadUtils.upload("regist.jsp",part, request, response);
			if ("".equals(photo)) {
				return;
						
			}
		}
	
	
	
		User user =new User(uname, Integer.valueOf(gender), phone, area, username, password, photo, new Date());
		boolean isSuccess=userService.registUser(user);
		if (isSuccess) {
			response.sendRedirect("login.jsp");
		} else {
			request.setAttribute("msg","����ע��");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			
			
		}
	}
	//�˺�Ψһ��
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		boolean isRegest=userService.checkUsername(username);
		response.getWriter().print(isRegest);
	}
	//�绰Ψһ��
	private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone=request.getParameter("phone");
		boolean isRegest=userService.checkPhone(phone);
		response.getWriter().print(isRegest);
	}
	}

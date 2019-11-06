package com.wf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.beans.editors.NumberEditor;
import com.wf.entity.Trolley;
import com.wf.entity.User;
import com.wf.service.TrolleyService;
@WebServlet("/trolley")
public class TrolleyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrolleyService trolleyService=new TrolleyService();
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String func = request.getParameter("func");
			switch (func) {
			case "addTrolley":
				addTrolley(request,response);
				break;
			case "selectTrolleyCount":
				selectTrolleyCount(request,response);
				break;
			case "findAllTrolley":
				findAllTrolley(request,response);
			break;
			case "addOrDeleteNumber":
				addOrDeleteNumber(request,response);
				break;
			case "deleteTrolley":
				deleteTrolley(request,response);
				break;
			default:
				break;
			}

}
private void deleteTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		boolean isSuccess=trolleyService.deleteTrolley(tid);
		response.sendRedirect("trolley?func=findAllTrolley");
}
private void addOrDeleteNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		String number = request.getParameter("number");
		boolean isSuccess=trolleyService.addOrDeleteNumber(tid,number);
		if (isSuccess) {
			response.sendRedirect("trolley?func=findAllTrolley");
		}
}
private void findAllTrolley(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//�Ȼ�ȡ�û�
	HttpSession session = request.getSession();
	User user=(User)session.getAttribute("user");
	//����service
	List<Trolley> trolleys=trolleyService.findAllTrolly(user);
	
	//�浽������У��浽���ﳵչʾҳ��
	request.setAttribute("trolleys", trolleys);
	request.getRequestDispatcher("trolley.jsp").forward(request, response);
	
}
private void selectTrolleyCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
	//�Ȼ�ȡ��ǰ�û�
	HttpSession session = request.getSession();
	User user=(User)session.getAttribute("user");
	int count=trolleyService.selectTrolleyCount(user.getUid());
	response.getWriter().write(count+"");
	
}
private void addTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	��ȡgid
	String gid = request.getParameter("gid");
//	��ȡuid
	HttpSession session = request.getSession();
	User user =(User)session.getAttribute("user");
	//�������ﳵ����
	Trolley trolley=new Trolley(user.getUid(),Integer.valueOf(gid));
	boolean isRepeat=trolleyService.addTrolley(trolley);
	response.getWriter().print(isRepeat);
}
}

package com.wf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.entity.Category;
import com.wf.service.CateService;
import com.wf.utils.DateTool;
import com.wf.utils.PageTool;
@WebServlet("/cate")
public class CateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CateService cateService=new CateService();
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String func = request.getParameter("func");
	 switch (func) {
	case "findAllCate": 
		findAllCate(request,response);
		break;
	case "insertCate":
		insertCate(request,response);
		break;
	case "findCateById":
		findCateById(request,response);
		break;
	case "updateCate":
		updateCate(request,response);
		break;
		case "deleteCate":
			deleteCate(request,response);
			break;
	default:
		break;
	}
}
private void deleteCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String ids = request.getParameter("ids");
	boolean isSuccess=cateService.deleteCate(ids);
	if (isSuccess) {
		response.sendRedirect("cate?func=findAllCate");
	}
	
}
private void updateCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String cid = request.getParameter("cid");
	String cname = request.getParameter("cname");
	String state = request.getParameter("state");
	String order_number = request.getParameter("order_number");
	String description = request.getParameter("description");
	String create_time= request.getParameter("create_time");
	Category category=new Category(Integer.valueOf(cid),cname, Integer.valueOf(state), 
			Integer.valueOf(order_number) , description, DateTool.stringToDate(create_time));
			boolean isSuccess=cateService.updateCate(category);
			if (isSuccess) {
				response.sendRedirect("cate?func=findAllCate");
			}else {
				response.sendRedirect("admin/error.jsp");
			}

}
private void findCateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String cid = request.getParameter("cid");
Category category=cateService.findCateById(cid);
	request.setAttribute("cate", category);
	request.getRequestDispatcher("admin/category_update.jsp").forward(request, response);
	
}
private void insertCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cname = request.getParameter("cname");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time= request.getParameter("create_time");
		Category category=new Category(cname, Integer.valueOf(state), 
		Integer.valueOf(order_number) , description, DateTool.stringToDate(create_time));
		
		boolean isSuccess=cateService.insertCate(category);		
		if (isSuccess) {
			response.sendRedirect("cate?func=findAllCate");
		}else {
			response.sendRedirect("admin/error.jsp");
		}

}
private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String currentPage = request.getParameter("currentPage");
	int totalCount=cateService.selectCateCount();	
	PageTool pageTool=new PageTool(totalCount, currentPage);
		List<Category> cates=cateService.findAllCate(pageTool);
		request.setAttribute("cates",cates);
		request.setAttribute("pageTool",pageTool);
		request.getRequestDispatcher("admin/category_list.jsp").forward(request, response);
		
}
}

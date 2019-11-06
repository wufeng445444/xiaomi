package com.wf.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wf.entity.Orders;
import com.wf.entity.User;
import com.wf.service.OrdersService;
import com.wf.service.TrolleyService;
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService=new OrdersService();
	private TrolleyService trolleyService=new TrolleyService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String func = request.getParameter("func");
			switch (func) {
			case "createOrders":
				createOrders(request,response);
				break;
			case "updateOrders":
				updateOrders(request,response);
				break;
			default:
				break;
			}
	}
	private void updateOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String orders_number = request.getParameter("orders_number");
		 boolean isSuccess=ordersService.updateOrders(orders_number);
		if (isSuccess) {
			response.sendRedirect("index?func=indexInfo");
		}
	}
	//生成订单的方法
	private void createOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建订单对象
		//获取订单信息
		String sumPrice  = request.getParameter("sumPrice");
		String goodsCount = request.getParameter("goodsCount");
		String orders_number=UUID.randomUUID().toString();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		String ordersName=user.getUname()+"的订单";
		Date create_time =new Date();//创建订单日期
		
		Orders orders=new Orders(orders_number, user.getUid(), Double.valueOf(sumPrice), ordersName, Integer.valueOf(goodsCount), create_time);
			boolean isSuccess=ordersService.createOrders(orders);
				if (isSuccess) {
					boolean isSucc=trolleyService.updateTrolley(orders_number, user.getUid());	
						if (isSucc) {
							System.out.println("购物车修改成功");
							//为了保证订单信息能够在首页面展示，所以先将订单存放在request域对象中，然后转发到首页面
							request.setAttribute("orders",orders);
							request.getRequestDispatcher("pay/index.jsp").forward(request, response);
							
						}
				}
				
				
	}
	
	
	
}

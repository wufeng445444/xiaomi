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
	//���ɶ����ķ���
	private void createOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������������
		//��ȡ������Ϣ
		String sumPrice  = request.getParameter("sumPrice");
		String goodsCount = request.getParameter("goodsCount");
		String orders_number=UUID.randomUUID().toString();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		String ordersName=user.getUname()+"�Ķ���";
		Date create_time =new Date();//������������
		
		Orders orders=new Orders(orders_number, user.getUid(), Double.valueOf(sumPrice), ordersName, Integer.valueOf(goodsCount), create_time);
			boolean isSuccess=ordersService.createOrders(orders);
				if (isSuccess) {
					boolean isSucc=trolleyService.updateTrolley(orders_number, user.getUid());	
						if (isSucc) {
							System.out.println("���ﳵ�޸ĳɹ�");
							//Ϊ�˱�֤������Ϣ�ܹ�����ҳ��չʾ�������Ƚ����������request������У�Ȼ��ת������ҳ��
							request.setAttribute("orders",orders);
							request.getRequestDispatcher("pay/index.jsp").forward(request, response);
							
						}
				}
				
				
	}
	
	
	
}

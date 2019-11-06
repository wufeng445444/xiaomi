package com.wf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.entity.Category;
import com.wf.entity.Goods;
import com.wf.service.CateService;
import com.wf.service.GoodsService;
import com.wf.utils.CookieUtisl;
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
private CateService cateService=new CateService();
private GoodsService goodsService=new GoodsService();
	private static final long serialVersionUID = 1L;
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String func = request.getParameter("func");
	switch (func) {
	case "indexInfo":
		indexInfo(request,response);
		break;
	case "searchGoods":
		searchGoods(request,response);
		break;
	case "findGoodsById":
		findGoodsById(request,response);
		break;
	default:
		break;
	}
	
}
private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
	Goods goods=goodsService.findGoodsById(gid);
		//�浽������Ȼ����ת��detail.jsp��ϸ�ڣ�ҳ��
	request.setAttribute("goods",goods);
	request.getRequestDispatcher("detail.jsp").forward(request, response);
}
//��ҳ��������Ʒ������cookie�ķ���
private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�Ȼ�ȡ����
	String search = request.getParameter("search");
	
	CookieUtisl.addCookie(search, request, response);
	response.getWriter().print(true);
	
}
//�ռ�ҳ���չʾ����
private void indexInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//�õ�����
	List<Category> cates=cateService.findAllCate(10);
   //�õ�С�����ǵ�Ʒ			4�������ǵ�Ʒ��С����ֵ��5����õ�5����Ʒ����
	List<Goods>	starGoods=goodsService.findGoodsByState(4,5);
	//�õ����еļҵ���Ʒ			��һ��4����ҵ�ķ��࣬�ڶ���8����õ�8����Ʒ����
	List<Goods>	homeGoods=goodsService.findGoodsByCate(4,8);
	//�õ����е����ܼҾ���Ʒ               ��һ��5�������ܼҾӵķ��࣬�ڶ���5����õ�������Ʒ����
	List<Goods>	smartGoods=goodsService.findGoodsByCate(5,5);
	//�õ����Ų�Ʒ
	List<Goods>	hotGoods=goodsService.findGoodsByState(1,4);
	//Ϊ���Ƽ�
	//���ù�����,��ȡcookie�е����� 
		String info = CookieUtisl.getCookieInfo(request);
		List<Goods> tjGoods=null;
		if ("".equals(info)) {
			//���û��������ʷ����С�����Ƽ�
			tjGoods = goodsService.findGoodsByState(2, 5);
		}else {
			tjGoods=goodsService.findGoodsBySearch(info, 5);
		}
	request.setAttribute("cates",cates);
	request.setAttribute("starGoods",starGoods);
	request.setAttribute("homeGoods",homeGoods);
	request.setAttribute("smartGoods", smartGoods);
	request.setAttribute("hotGoods",hotGoods);
	request.setAttribute("tjGoods", tjGoods);
	request.getRequestDispatcher("index.jsp").forward(request, response);
	
}


}

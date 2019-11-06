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
		//存到域对象里，然后在转到detail.jsp（细节）页面
	request.setAttribute("goods",goods);
	request.getRequestDispatcher("detail.jsp").forward(request, response);
}
//首页面搜索商品，生成cookie的方法
private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取数据
	String search = request.getParameter("search");
	
	CookieUtisl.addCookie(search, request, response);
	response.getWriter().print(true);
	
}
//收集页面的展示数据
private void indexInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//得到分类
	List<Category> cates=cateService.findAllCate(10);
   //得到小米明星单品			4代表明星单品的小分类值，5代表得到5条商品分类
	List<Goods>	starGoods=goodsService.findGoodsByState(4,5);
	//得到所有的家电商品			第一个4代表家电的分类，第二个8代表得到8条商品数据
	List<Goods>	homeGoods=goodsService.findGoodsByCate(4,8);
	//得到所有的智能家居商品               第一个5代表智能家居的分类，第二个5代表得到五条商品数据
	List<Goods>	smartGoods=goodsService.findGoodsByCate(5,5);
	//得到热门产品
	List<Goods>	hotGoods=goodsService.findGoodsByState(1,4);
	//为你推荐
	//调用工具类,获取cookie中的数据 
		String info = CookieUtisl.getCookieInfo(request);
		List<Goods> tjGoods=null;
		if ("".equals(info)) {
			//如果没有搜索历史按照小分类推荐
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

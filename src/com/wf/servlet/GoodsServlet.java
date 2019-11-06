package com.wf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.wf.entity.Category;
import com.wf.entity.Goods;
import com.wf.service.CateService;
import com.wf.service.GoodsService;
import com.wf.utils.DateTool;
import com.wf.utils.PageTool;
import com.wf.utils.UploadUtils;
@WebServlet("/goods")
@MultipartConfig
public class GoodsServlet extends HttpServlet {
private CateService cateService=new CateService();
private GoodsService goodsService=new GoodsService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String func = request.getParameter("func");
		switch (func) {
		case "findAllCate":
			findAllCate(request,response);
			break;
		case "insertGoods":
			insertGoods(request,response);
			break;
		case "findAllGoods":
			findAllGoods(request,response);
			break;
		case "findGoodsById":
			findGoodsById(request,response);
			break;
		case "updateGoods":
			updateGoods(request,response);
			break;
		case "deleteGoods":
			deleteGoods(request,response);
			break;
		default:
			break;
		}
	
	}
	private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		boolean isSuccess=goodsService.deleteGoods(ids);
		if (isSuccess) {
			response.sendRedirect("goods?func=findAllGoods");
		}
	}
	//�޸�
	private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String gid = request.getParameter("gid");
		String cid = request.getParameter("cid");
		String gname = request.getParameter("gname");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		//ͼƬ������
		String pic="";
		//�õ�part����
		Part part = request.getPart("pic");
		//�ж��Ƿ��ϴ�������
		if (part.getSize() == 0) {
			pic=request.getParameter("oldPic");
		} else {
			pic=UploadUtils.upload("goods?func=findGoodsById&gid="+gid, part,request, response);
			if ("".equals(pic)) {
				return;
			}
		}
		
		Goods goods=new Goods(Integer.valueOf(gid),
				Integer.valueOf(cid), gname, color, size,Double.valueOf(price) , 
				description, full_description, pic,Integer.valueOf(state) , version,
				DateTool.stringToDate(product_date));
		boolean isSuccess=goodsService.updateGoods(goods);
		if (isSuccess) {
			response.sendRedirect("goods?func=findAllGoods");
		}
		
		
		
		
	}
	//���һ���
	private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		System.err.println(gid);
		Goods goods =goodsService.findGoodsById(gid);
		System.out.println(goods);
		request.setAttribute("goods",goods);
		//����service��ȡ�����еķ��࣬
		List<Category> cates = cateService.findAllCate();
		System.out.println(cates);
		request.setAttribute("cates", cates);
		request.getRequestDispatcher("admin/goods_update.jsp").forward(request, response);
	}
	//��ѯ������Ʒ�ķ���
	private void findAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//������ҳ���߶���
		String currentPage = request.getParameter("currentPage");
		int totalCount=goodsService.selectGoodsCount();
		PageTool pageTool=new PageTool(totalCount, currentPage);
		List<Goods> goodes=goodsService.findAllGoods(pageTool);
		request.setAttribute("pageTool",pageTool);
		request.setAttribute("goodes", goodes);
		request.getRequestDispatcher("admin/goods_list.jsp").forward(request, response);
		
	}
	private void insertGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			String cid = request.getParameter("cid");
			String gname = request.getParameter("gname");
			String color = request.getParameter("color");
			String size = request.getParameter("size");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String full_description = request.getParameter("full_description");
			String state = request.getParameter("state");
			String version = request.getParameter("version");
			String product_date = request.getParameter("product_date");
			//ͼƬ������
			String pic="";
			//�õ�part����
			Part part = request.getPart("pic");
			//�ж��Ƿ��ϴ�������
			if (part.getSize() == 0) {
				request.setAttribute("msg", "�����ϴ�ͼƬ");
				findAllCate(request, response);
				return;
			} else {
				pic=UploadUtils.upload("goods?func=findAllCate", part,request, response);
				if ("".equals(pic)) {
					return;
				}
			}
			//������Ʒ��ʵ����
			Goods goods=new Goods(Integer.valueOf(cid), gname, color, size,Double.valueOf(price) , 
					description, full_description, pic,Integer.valueOf(state) , version,
					DateTool.stringToDate(product_date));		
			boolean isSuccess = goodsService.insertGoods(goods);
			if (isSuccess) {
				response.sendRedirect("goods?func=findAllGoods");
			} else {
				response.sendRedirect("admin/error.jsp");
			}
			
	}
	private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Category> cates=cateService.findAllCate();
			request.setAttribute("cates", cates);
			request.getRequestDispatcher("admin/goods_add.jsp").forward(request, response);
			
			   
	}
}





















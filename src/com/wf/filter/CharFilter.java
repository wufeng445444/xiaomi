package com.wf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class CharFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//��������Ĳ��������Լ���Ӧ�����ͼ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//���У�
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}

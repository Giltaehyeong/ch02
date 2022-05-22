package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// 필터를 적용할 요청의 패턴 지정 - 모든 요청에 필터를 적용.
@WebFilter(urlPatterns = "/*")
public class PerformanceFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 초기화 작업
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	                    //HttpServletRequest가 아니라 형변환을 해줘야함.
			throws IOException, ServletException {
		// 1. 전처리 작업
		long startTime = System.currentTimeMillis();

		// 2. 서블릿 또는 다음 필터를 호출
		chain.doFilter(request, response);

		// 3. 후처리 작업
		HttpServletRequest req = (HttpServletRequest) request;
		// 어디서 어디로 요청했는지 알기 위해 필터에 다음과 같은 메서드로 작업한다.
		String referer = req.getHeader("referer");
		                         //from 어디서 요청했는지
		String method = req.getMethod();
		                         //to 어디로 요청했는지
		System.out.print("[" + referer + "]" + "->" + method + "[" + req.getRequestURI() + "]");
		System.out.println(" 소요시간=" + (System.currentTimeMillis() - startTime) + "ms");
	}

	@Override
	public void destroy() {
		// 정리 작업
	}

}
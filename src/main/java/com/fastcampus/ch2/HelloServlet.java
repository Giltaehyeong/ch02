package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

	@Override
	public void init() throws ServletException { // 메모리에 올라갈 때 처음 한번만 호출.
		// 서블릿이 초기화될 때 자동 호출되는 메서드
		// 1. 서블릿의 초기화 작업 담당
		System.out.println("[HelloServlet] init() is called.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // 서비스가 호출될 때마다 사용.
		// 1. 입력
		// 2. 처리
		// 3. 출력
		System.out.println("[HelloServlet] service() is called.");
	}

	@Override
	public void destroy() { 
		// 3. 뒷정리 - 서블릿이 메모리에서 제거 될 때 서블릿 컨테이너에 의해서 자동 호출.
		// 메모리에서 내려올때, 내용이 새로 갱신될때, 뒷마무리 작업.
		// 컴파일러 : 앗!! 컨트롤러가 프로그램이 변경되었구나 !! 알수 있다 ~~
		System.out.println("[HelloServlet] destory() is called.");
	}	
}
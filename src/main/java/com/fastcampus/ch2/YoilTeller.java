package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 년원일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTeller { // http://localhost/ch2/getYoil?year=2021&month=10&day=1
//	public static void main(String[] args) { 
		@RequestMapping("/getYoil")
		public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 1. 입력
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		// 2. 작업
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ...
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		// 3. 출력
		response.setContentType("text/html"); //보내는 문서 종류는 text/html이다.
		// 브라우저는 보내는 내용이 텍스트인지 바이너리인지 모름.
		// 그래서 형식을 지정해주어야 함.
		response.setCharacterEncoding("utf-8"); //인코딩 양식은 utf-8이다.
		PrintWriter out = response.getWriter(); // response객체에서 브라우저로의 출력 스트림을 얻는다.
		out.println(year + "년 " + month + "월 " + day + "일은 ");
		out.println(yoil + "요일입니다.");
	}
}

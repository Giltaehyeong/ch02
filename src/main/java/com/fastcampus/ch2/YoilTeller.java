package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTeller { // http://localhost:8080/getYoil?year=2021&month=10&day=1
//	public static void main(String[] args) {
	@RequestMapping("/getYoil")
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 							//문자열 배열 자동으로 만들어줌.
		
		//관심사 Concerns -> 해야할 작업
		//OOP 5대 설계원칙 - SOLID
		//SRP : 단일책임의 원칙(하나의 메서드는 하나의 책임)
		
		//관심사 분리에는 3가지 원칙이 있다.
		//1. 관심사
		//2. 변하는것, 변하지 않는 것
		//  common, uncommon
		//3. 공통(중복)코드
		
		// 1. 입력
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		//2. 작업(요일계산)
		int yyyy = Integer.parseInt(year);
					//문자열을 정수값으로 변환하여 받아옴.
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //1. 일요일, 2. 월요일 ...
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		//3. 출력
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter(); // response 객체에서 브라우저로의 출력 스트림을 얻는다.
		out.println(year + "년 " + month + "월 " + day + "일은 ");
		out.println(yoil + "요일입니다.");
	}
}

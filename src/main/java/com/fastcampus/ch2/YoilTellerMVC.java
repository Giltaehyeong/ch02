package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 년원일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC { // http://localhost/ch2/getYoilMVC?year=2021&month=10&day=1
		@RequestMapping("/getYoilMVC")
//		public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
		public String main(int year, int month, int day, Model m) throws IOException{
//			  뷰 이름을 반환해야되서 void -> String 으로
		
//			ModelAndView mv = new ModelAndView();	
			
			//1. 유효성 검사 : 값이 들어왔는지 안들어왔는지 확인.
			if(!isValid(year, month, day))
				return "yoilError";
			
			//2. 요일 받아오는 부분을 별도의 메서드로 뽑는다.
			// 우클릭 -> Refactor -> Extract Method(코드를 별도의 메서드로 뽑아냄.)
			char yoil = getYoil(year, month, day);
			
			//3. 계산한 결과를 model에 저장
			m.addAttribute("year", year);
			m.addAttribute("month", month);
			m.addAttribute("day", day);
			m.addAttribute("yoil", yoil);
			
//			//4. 결과를 보여줄 View를 지정
//			mv.setViewName("yoil");
//			return mv;
			
			return "yoil"; // /WEB-INF/views/yoil.jsp

		}
		
		//메서드의 접근자를 private로 한 이유는 이 class 안에서만 사용하기 떄문.
		private boolean isValid(int year, int month, int day) {
			// TODO Auto-generated method stub
			return true;
		}

		private char getYoil(int year, int month, int day) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ...
			return " 일월화수목금토".charAt(dayOfWeek);
		}
}

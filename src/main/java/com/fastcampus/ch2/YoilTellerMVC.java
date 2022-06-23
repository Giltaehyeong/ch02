package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC { // http://localhost/getYoilMVC?year=2021&month=10&day=1
//	public static void main(String[] args) {
	@RequestMapping("/getYoilMVC")
	// URL 매핑은 중복된게 있으면 안됨.
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public String main(int year, int month, int day, Model model) throws IOException {
							//입력받을 값을 매개변수로 선언, Model을 선언해서
//		ModelAndView mv = new ModelAndView();
		
		//1. 유효성 검사
		if(!isValid(year, month, day))
			return "yoilError";
			
		//2. 요일 계산
		char yoil = getYoil(year, month, day);
		
		// 3. 계산한 결과를 model에 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
//		mv.addObject("year", year);
//		mv.addObject("month", month);
//		mv.addObject("day", day);
//		mv.addObject("yoil", yoil);
		
		//4. 결과를 보여줄 view를 지정
//		mv.setViewName("yoil");	
//		return mv;
		
		return "yoil"; // /WEB-INF/view/yoil.jsp
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //1. 일요일, 2. 월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}
	
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }

}

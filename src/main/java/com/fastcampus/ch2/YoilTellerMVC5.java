package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC5 { // http://localhost/ch2/getYoilMVC5?year=2021&month=10&day=1
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC5")
//	public String main(@ModelAttribute("myDate") MyData date, Model model) //  아래와 동일
	public String main(@ModelAttribute MyDate date, Model model) throws IOException {
							//생략 가능			//참조형

		if(!isValid(date))
			return "yoilError";

//		char yoil = getYoil(date);

//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		return "yoil";
	}

	//입력받은 값을 메서드에 반환.
	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}
	
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }

	//입력 받은 값을 메서드에 반환.
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //1. 일요일, 2. 월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}

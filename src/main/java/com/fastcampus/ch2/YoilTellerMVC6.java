package com.fastcampus.ch2;

import java.io.FileDescriptor;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC6 {
	
	//"" 값을 int형으로 바꿔달라는 오류가 발생하게되면 실행되는 예외메서드
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		                                //객체가 에러객체를 갖고있고
		System.out.println("result= " + result);
		FieldError error = result.getFieldError();
		//이 에러 객체가지고 어떤 값들을 얻을 수 있는지 확인해보는 메서드
		
		System.out.println("code = " + error.getCode());
		System.out.println("getField = " + error.getField());
		System.out.println("msg = " + error.getDefaultMessage());
		
		ex.printStackTrace();
		// 예외가 발생한거 찍어보기.
		return "yoilError";
		//해당 view 페이지 출력
	}
	
    @RequestMapping("/getYoilMVC6") // http://localhost/ch2/getYoilMVC4
    	public String main(MyDate date, BindingResult result) {
    	System.out.println("result= " + result);
 
        // 1. 유효성 검사
    	if(!isValid(date)) 
    		return "yoilError";  // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동
    	
        // 2. 처리
//    	char yoil = getYoil(date);

        // 3. Model에 작업 결과 저장
//        model.addAttribute("myDate", date);
//        model.addAttribute("yoil", yoil);
        
        // 4. 작업 결과를 보여줄 View의 이름을 반환
        return "yoil"; // /WEB-INF/views/yoil.jsp
    }
    
    private boolean isValid(MyDate date) {
		// TODO Auto-generated method stub
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);
    }
    
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }
}
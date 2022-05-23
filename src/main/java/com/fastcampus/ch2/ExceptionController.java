package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	                    //에외처리 최고 조상.
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //200 -> 500
	// 응답상태코드를 변경한다.
	//ExceptionController 컨트롤러 내부에 메서드에서 예외가 발생하면 이 메서드에서 처리한다.
	public String catcher(Exception ex, Model m) {
		System.out.println("catcher() in ExceptionController");
		System.out.println("m="+m);
//		m.addAttribute("ex", ex); 
//		isErrorPage = "true" 지시자가 있다면 모델객체로 메세지를 전달하지 않아도 
//		exception 기본객체를 사용할 수 있다.
		
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main(Model m) throws Exception{
		m.addAttribute("msg", "message from ExceptionController.main()");
			throw new Exception("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws Exception{
			throw new FileNotFoundException("예외가 발생했습니다.");
	}
}

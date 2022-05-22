package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //ctrl + shift + o
public class RegisterController {
	
	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
														//둘다 허용해준다.
//	<view-controller path="register/add" view-name="registerForm" />
//	@GetMapping("register/add") // 신규회원 가입 화면
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value = "register/save", method=RequestMethod.POST)
//	 Request method 'GET' not supported
	@PostMapping("/register/save") //4.3 부터 적용
	public String save(User user, Model m) throws Exception {
		// 1. 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
			
			m.addAttribute("msg", msg); //model에 담아서 넘겨줄 수 있음.
			return "forward:/register/add"; //URL재작성(rewriting)
//			return "redirect:/register/add?msg="+msg; //URL재작성(rewriting)
		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}

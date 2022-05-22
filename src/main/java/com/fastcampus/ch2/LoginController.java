package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response) throws Exception{
		System.out.println("id="+id);
		System.out.println("pwd="+id);
		System.out.println("rememberId="+rememberId);
		// 1. id와 pwd를 확인
		if(!logincheck(id, pwd)) {
			// 2일치하지 않으면, loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg="+msg;
		}
		
		// 2-2. id와 pwd가 일치하면, 홈으로 이동
		
		if(rememberId) {
			//		1. 쿠키를생성
			Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
			//		2. 응답에 저장
			response.addCookie(cookie);
		}else {
			// 쿠키를 삭제
			Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
			cookie.setMaxAge(0); // 쿠키 삭제
			response.addCookie(cookie); // 응답 저장
		}
		return "redirect:/";
	}

	private boolean logincheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}


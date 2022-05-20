package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //ctrl + shift + o
public class RegisterController {
	
//	@RequestMapping(value = "register/add", method={RequestMethod.GET, method=RequestMethod.POST})
														//�Ѵ� ������ش�.
//	<view-controller path="register/add" view-name="registerForm" />
//	@GetMapping("register/add") // �ű�ȸ�� ���� ȭ��
//	public String register() {
//		return "registerForm";
//	}
	
//	@RequestMapping(value = "register/save", method=RequestMethod.POST)
//	 Request method 'GET' not supported
	@PostMapping("/register/save") //4.3 ���� ����
	public String save(User user, Model m) throws Exception {
		// 1. ��ȿ�� �˻�
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id�� �߸��Է��ϼ̽��ϴ�.", "utf-8");
			
			m.addAttribute("msg", msg); //model�� ��Ƽ� �Ѱ��� �� ����.
			return "redirect:/register/add"; //URL���ۼ�(rewriting)
//			return "redirect:/register/add?msg="+msg; //URL���ۼ�(rewriting)
		}
		
		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}

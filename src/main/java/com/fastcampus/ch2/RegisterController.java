package com.fastcampus.ch2;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //ctrl + shift + o
public class RegisterController {

	@InitBinder
	//��¥�������� �ٲٴ� �޼���
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService" + conversionService);
//		              //������ �����ϴ� �޼���
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		                                         //��ȯ�� ����Ʈ Ÿ��.
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//																// �� ���� ����Ұ��ΰ�?
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));
//		binder.setValidators(new UserValidator()); //UserValidator�� WebDataBinder�� ���� validator�� ���
//		binder.addValidators(new UserValidator());
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList" + validatorList);
	}
	
	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
														//�Ѵ� ������ش�.
//	<view-controller path="register/add" view-name="registerForm" />
//	@GetMapping("register/add") // �ű�ȸ�� ���� ȭ��
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value = "register/save", method=RequestMethod.POST)
//	 Request method 'GET' not supported
	@PostMapping("/register/save") //4.3 ���� ����
	public String save(@Valid User user, BindingResult result,Model m) throws Exception {
		               //���ε� �� ��ü �ڿ� �;ߵ�.
					   //BindingResult�� ������ ��Ʈ�ѷ����� ���ε� ����� �ְ� ��� �Ұ����� ó���ϰ� �ϴ°�.
//		resultorg.springframework.validation.BeanPropertyBindingResult: 1 errors
		System.out.println("result = "+ result);
		System.out.println("user ="+ result);
		
//		//���� ���� - Validaoter�� ���� �����ϰ�, validate()�� ���� ȣ��
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); //BingdingResult�� Errors�� �ڼ�
		
		// User ��ü�� ������ ��� ������ ������, registerForm�� �̿��ؼ� ������ ������� ��.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
		// 1. ��ȿ�� �˻�
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id�� �߸��Է��ϼ̽��ϴ�.", "utf-8");
//			
//			m.addAttribute("msg", msg); //model�� ��Ƽ� �Ѱ��� �� ����.
//			return "forward:/register/add"; //URL���ۼ�(rewriting)
//			return "redirect:/register/add?msg="+msg; //URL���ۼ�(rewriting)
//		}
		
		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}

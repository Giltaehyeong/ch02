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
	//날짜형식으로 바꾸는 메서드
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService" + conversionService);
//		              //데이터 검증하는 메서드
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		                                         //변환할 데이트 타입.
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//																// 빈 값을 허용할것인가?
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));
//		binder.setValidators(new UserValidator()); //UserValidator를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator());
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList" + validatorList);
	}
	
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
	public String save(@Valid User user, BindingResult result,Model m) throws Exception {
		               //바인딩 할 객체 뒤에 와야됨.
					   //BindingResult가 있으면 컨트롤러한테 바인딩 결과를 주고 어떻게 할것인지 처리하게 하는것.
//		resultorg.springframework.validation.BeanPropertyBindingResult: 1 errors
		System.out.println("result = "+ result);
		System.out.println("user ="+ result);
		
//		//수동 검증 - Validaoter를 직접 생성하고, validate()를 직접 호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); //BingdingResult는 Errors의 자손
		
		// User 객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
		// 1. 유효성 검사
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
//			
//			m.addAttribute("msg", msg); //model에 담아서 넘겨줄 수 있음.
//			return "forward:/register/add"; //URL재작성(rewriting)
//			return "redirect:/register/add?msg="+msg; //URL재작성(rewriting)
//		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}

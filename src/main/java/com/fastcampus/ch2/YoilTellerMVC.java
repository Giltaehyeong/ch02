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

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTellerMVC { // http://localhost/ch2/getYoilMVC?year=2021&month=10&day=1
		@RequestMapping("/getYoilMVC")
//		public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
		public String main(int year, int month, int day, Model m) throws IOException{
//			  �� �̸��� ��ȯ�ؾߵǼ� void -> String ����
		
//			ModelAndView mv = new ModelAndView();	
			
			//1. ��ȿ�� �˻� : ���� ���Դ��� �ȵ��Դ��� Ȯ��.
			if(!isValid(year, month, day))
				return "yoilError";
			
			//2. ���� �޾ƿ��� �κ��� ������ �޼���� �̴´�.
			// ��Ŭ�� -> Refactor -> Extract Method(�ڵ带 ������ �޼���� �̾Ƴ�.)
			char yoil = getYoil(year, month, day);
			
			//3. ����� ����� model�� ����
			m.addAttribute("year", year);
			m.addAttribute("month", month);
			m.addAttribute("day", day);
			m.addAttribute("yoil", yoil);
			
//			//4. ����� ������ View�� ����
//			mv.setViewName("yoil");
//			return mv;
			
			return "yoil"; // /WEB-INF/views/yoil.jsp

		}
		
		//�޼����� �����ڸ� private�� �� ������ �� class �ȿ����� ����ϱ� ����.
		private boolean isValid(int year, int month, int day) {
			// TODO Auto-generated method stub
			return true;
		}

		private char getYoil(int year, int month, int day) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:�Ͽ���, 2:������ ...
			return " �Ͽ�ȭ�������".charAt(dayOfWeek);
		}
}

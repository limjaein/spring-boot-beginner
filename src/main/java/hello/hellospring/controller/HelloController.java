package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	// http get method 매핑 함수 
	@GetMapping("hello")
	 public String hello(Model model) {
		 model.addAttribute("data", "hello!!");
		 return "hello";	// viewResolver가 resources/templates/{viewName}.html로 자동 렌더링
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc (@RequestParam(name = "name", required = false) String name, Model model) {
		 model.addAttribute("name", name);
		 return "hello-template";	
	}
	
	@GetMapping("hello-string")
	// ResponseBody 추가 시 view 없이 리턴 값을 그대로 리턴
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}
	
	@GetMapping("hello-api")
	// ResponseBody 추가 시 viewResolver 대신 HttpMessageConverter가 동작 
	// 문자열, json객체 등 자동 converting 해준다
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
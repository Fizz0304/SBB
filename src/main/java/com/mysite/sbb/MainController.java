package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/sbb")
	@ResponseBody
	public String index() {
		return "안녕하세요 SBB에 오신 것을 환영합니다.";
	}
	
	@GetMapping("/")
	public String root() {
		// redirect는 /question/list URL 페이지로 리다이렉트(클라이언트가 요청 시 새로운 URL을 전송하는 것)하라는 의미.
		return "redirect:/question/list";
	}
	
}

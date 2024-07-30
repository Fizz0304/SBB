package com.mysite.sbb.question;

import java.util.List;

// 필요한 라이브러리 불러오기
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;

import com.mysite.sbb.answer.AnswerForm;

@RequestMapping("/question")
// questionRepository 객체를 주입 > final이 붙은 속성을 포함하는 생성자를 자동으로 만들어 주는 역할
@RequiredArgsConstructor
// 컨트롤러라는 어노테이션을 추가
@Controller 
public class QuestionController {
	
	private final QuestionService questionService;
	
	// 아래 url 경로로 매핑
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0")int page) { // 매개변수로 Model을 지정하면 객체가 자동으로 생성
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging); // 모델 객체는 자바 클래스와 템플릿 간 연결 고리 역할
		
		return "question_list"; //  문자열이 아닌 파일명 question_list를 의미
	}
	
	@GetMapping(value = "/datail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping(value = "/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PostMapping(value = "/create")
	// @Valid 애너테이션을 통해 QuestionForm의 @NotEmpty 등의 검증 기능이 동작한다.
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
	
	
}

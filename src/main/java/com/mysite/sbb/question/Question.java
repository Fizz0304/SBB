package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // 스프링이 Question 클래스를 엔티티로 인식하도록 애너테이션을 추가
public class Question {
	@Id  // 기본키를 지정하는 애너테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터를 저장할 때, 해당 속성에 값을 자동으로 1씩 증가해 저장하는 애너테이션
	private Integer id;
	
	@Column(length=200)  // 열의 세부 설정을 하는 애너테이션
	private String subject;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
}

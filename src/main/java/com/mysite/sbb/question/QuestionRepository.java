package com.mysite.sbb.question;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// QuestionRepository 인터페이스를 리포지터리로 만들기 위해 JpaRepository 인터페이스를 상속한다.
// JpaRepository<Question, Integer>는 Question 엔티티로 리포지터리를 생성한다는 의미이다.
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String Content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable); // Pageable 객체를 입력받아 Page<Question> 타입 객체를 리턴하는 findAll 메서드를 생성
}

package com.siyu.triviabackend.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long>{
    //List<Question> findByFailedOrNot(boolean failedOrNot);

    // List<Question> findByFailedOrNot(boolean failedOrNot);
    List<Question> findByFailureStatus(boolean failureStatus);
}

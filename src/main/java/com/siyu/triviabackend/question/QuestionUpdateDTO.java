package com.siyu.triviabackend.question;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionUpdateDTO {

    private String submittedAnswer;

   
    private boolean failureStatus;

    
}

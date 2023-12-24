package com.siyu.triviabackend.question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCreateDTO {
   
    private String question;

    private String correntAnswer;

    private String[] incorrentAnswer;

    private String submittedAnswer;

    private boolean failedOrNot;

    private Long gameId;

}

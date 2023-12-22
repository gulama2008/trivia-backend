package com.siyu.triviabackend.question;

import com.siyu.triviabackend.game.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer apiId;

    @Column
    private String question;

    @Column
    private String correntAnswer;

    @Column
    private String[] incorrentAnswer;

    @Column
    private String submittedAnswer;

    @Column
    private boolean failedOrNot;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Question() {
    }
    
    public Question(Integer apiId,String question,String correctAnswer,String[] incorrentAnswer,String submittedAnswer,boolean failedOrNot) {
        this.apiId = apiId;
        this.question = question;
        this.correntAnswer = correctAnswer;
        this.incorrentAnswer = incorrentAnswer;
        this.submittedAnswer = submittedAnswer;
        this.failedOrNot = failedOrNot;
    }
}

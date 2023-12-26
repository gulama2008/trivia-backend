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
    private String question;

    @Column
    private String correctAnswer;
    @Column
    private String[] incorrectAnswer;
    @Column
    private String submittedAnswer;

    // @Column
    // private boolean failedOrNot;

    @Column
    private boolean failureStatus;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Question() {
    }
    
    public Question(String question,String correctAnswer,String[] incorrectAnswer,String submittedAnswer,boolean failureStatus,Game game) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
        this.submittedAnswer = submittedAnswer;
        this.failureStatus = failureStatus;
        this.game = game;
    }
}

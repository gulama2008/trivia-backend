package com.siyu.triviabackend.question;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyu.triviabackend.game.Game;
import com.siyu.triviabackend.game.GameRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<Question> getAll() {
        return this.questionRepository.findAll();
    }

    public Optional<Question> getById(Long id) {
		Optional<Question> foundQuestion = questionRepository.findById(id);
		return foundQuestion;
	}

    public Question createQuestion(QuestionCreateDTO data) {
        Long id = data.getGameId();
        
        Optional<Game> game = this.gameRepository.findById(id);
        if (game.isPresent()) {
            String question = data.getQuestion();
            String correctAnswer=data.getCorrentAnswer();
            String[] incorrectAnswer = data.getIncorrentAnswer();
            String submittedAnswer = data.getSubmittedAnswer();
            boolean failedOrNot = data.isFailedOrNot();
            Game gameParam = game.get();
            Question newQuestion = new Question( question, correctAnswer, incorrectAnswer, submittedAnswer,
                    failedOrNot, gameParam);
            Question created = this.questionRepository.save(newQuestion);
            return created;
        }
        return null;
    }
}

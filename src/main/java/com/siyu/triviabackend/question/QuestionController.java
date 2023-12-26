package com.siyu.triviabackend.question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyu.triviabackend.exceptions.NotFoundException;
import com.siyu.triviabackend.game.Game;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAll() {
        List<Question> allQuestions = this.questionService.getAll();
        return new ResponseEntity<>(allQuestions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
	public ResponseEntity<Question> getById(@PathVariable Long id) {
		Optional<Question> found = this.questionService.getById(id);
		
		if(found.isPresent()) {
			return new ResponseEntity<Question>(found.get(), HttpStatus.OK);
		}
		throw new NotFoundException(String.format("Todo with id: %d does not exist", id));
	}
    
    @PostMapping
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody QuestionCreateDTO data) {
        Question newQuestion = this.questionService.createQuestion(data);
        if (newQuestion == null) {
            throw new NotFoundException(String.format("Could not found game with id %d", data.getGameId()));
        }
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/failed")
    public ResponseEntity<List<Question>> getAllFailedQuestions() {
        List<Question> allFailedQuestions = this.questionService.getAllFailedQuestions();
        return new ResponseEntity<>(allFailedQuestions, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> updateById(@PathVariable Long id, @Valid @RequestBody QuestionUpdateDTO data) {
        Optional<Question> updated = this.questionService.updateById(id, data);
        if (updated.isPresent()) {
			return new ResponseEntity<Question>(updated.get(), HttpStatus.OK);
		}
        throw new NotFoundException(String.format("Question with id %d does not exist, could not update", id));
    }

}

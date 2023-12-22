package com.siyu.triviabackend.game;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyu.triviabackend.exceptions.NotFoundException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;
    
    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        List<Game> allGames = this.gameService.getAll();
        return new ResponseEntity<>(allGames, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createGame() {
        Game newGame = this.gameService.createGame();
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
	public ResponseEntity<Game> updateById(@PathVariable Long id, 
            @Valid @RequestBody GameUpdateDTO data) {
		Optional<Game> updated = this.gameService.updateById(id, data);
        if (updated.isPresent()) {
			return new ResponseEntity<Game>(updated.get(), HttpStatus.OK);
		}
        throw new NotFoundException(String.format("Game with id %d does not exist, could not update", id));
	}
}

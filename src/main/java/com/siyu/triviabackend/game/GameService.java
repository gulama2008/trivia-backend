package com.siyu.triviabackend.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    public Optional<Game> getById(Long id) {
        return this.gameRepository.findById(id);
    }

    public Game createGame() {
        Game newGame = new Game();
        Game created = this.gameRepository.save(newGame);
        return created;
    }
    
    public Optional<Game> updateById(Long id, GameUpdateDTO data) {
        Optional<Game> foundGame = this.getById(id);
        if (foundGame.isPresent()) {
            Game toUpdate = foundGame.get();
            toUpdate.setScore(data.getScore());
            Game updatedGame = this.gameRepository.save(toUpdate);
            return Optional.of(updatedGame);

        }
	    return foundGame;
    }
}

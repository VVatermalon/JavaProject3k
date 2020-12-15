package com.games.demo.service.impl;


import com.games.demo.dto.GameDto;
import com.games.demo.entity.Category;
import com.games.demo.entity.Game;
import com.games.demo.entity.Request;
import com.games.demo.repository.CategoryRepository;
import com.games.demo.repository.GameRepository;
import com.games.demo.service.GameService;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public GameServiceImpl(GameRepository gameRepository, CategoryRepository categoryRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Iterable<Game> findAll(Sort sort) {
        return gameRepository.findAll(sort);
    }

    @Override
    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @Override
    public void deleteGame(Integer integer) {
        gameRepository.deleteById(integer);
    }

    @Override
    public void updateGame(Integer id, GameDto gameDto) {
        gameRepository.updateGame(id, gameDto.getName(), gameDto.getDescription(), gameDto.getPeopleAmount(),
                gameDto.getCurrentPeopleAmount(), gameDto.getStartDate(), gameDto.getEndDate());
    }

    @Override
    public void deleteCategory(Integer gameId, String category) {
        gameRepository.deleteGameCategory(gameId, categoryRepository.findByName(category).getId());
    }

    @Override
    public void addCategory(Integer gameId, String category) {
        gameRepository.addGameCategory(gameId, categoryRepository.findByName(category).getId());
    }

    @Override
    public Game getGameById(Integer id) throws Exception {return gameRepository.findById(id).get();}

    @Override
    public Game createGame(GameDto gameDto) {
        Game game = new Game();

        game.setName(gameDto.getName());
        game.setDescription(gameDto.getDescription());
        game.setStartDate(gameDto.getStartDate());
        game.setEndDate(gameDto.getEndDate());
        game.setPeopleAmount(gameDto.getPeopleAmount());
        game.setCurrentPeopleAmount(gameDto.getCurrentPeopleAmount());

        List<Category> categories = new ArrayList<>();
        for (var category : gameDto.getCategories()) {
            categories.add(categoryRepository.findByName(category));
        }
        game.setRequests(new ArrayList<Request>());
        game.setCategories(categories);

        gameRepository.save(game);
        return game;
    }

    @Override
    public List<Game> getGamesByParams(GameDto gameDto) throws Exception {
        return gameRepository.findGamesByParams(gameDto.getPeopleAmount(), gameDto.getCategories().get(0),
                gameDto.getStartDate(), gameDto.getEndDate()).get();
    }

    @Override
    public List<Game> getGamesByName(String name) throws Exception {
        return gameRepository.findGamesByName(name).get();
    }

}

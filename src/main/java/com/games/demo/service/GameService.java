package com.games.demo.service;

import com.games.demo.dto.GameDto;
import com.games.demo.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Integer id) throws Exception;
    Game createGame(GameDto gameDto);
    //List<Game> getGames(int page, int counter);
    //List<Game> getGamesByName(String name, int page, int counter);
    List<Game> getGamesByName(String name) throws Exception;
    List<Game> getGamesByParams(GameDto gameDto) throws Exception;
    void deleteGame(Integer id);
    void addCategory(Integer gameId, String category);
    void deleteCategory(Integer gameId, String category);
    void updateGame(Integer id, GameDto gameDto);
    Iterable<Game> findAll(Sort sort);
    Page<Game> findAll(Pageable pageable);
}

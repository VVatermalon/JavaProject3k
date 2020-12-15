package com.games.demo.repository;

import com.games.demo.entity.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {
    Optional<List<Game>> findGamesByName(String name);
    List<Game> findAll();
    @Query(value = "select GameId, Games.Name, Description, StartDate, EndDate, PeopleAmount, CurrentPeopleAmount, Categories.Name" +
            " from Games join Categories on Categories.CategoryId = Games.GameId" +
            "where CurrentPeopleAmount < PeopleAmount and (?1 is null or PeopleAmount = ?1) and (?2 is null or Category = ?2) and" +
            "(?3 is null or StartDate = ?3) and (?4 is null or EndDate = ?4)", nativeQuery = true)
    Optional<List<Game>> findGamesByParams(Integer peopleAmount, String category, Date gameStart, Date gameEnd);
    @Query(value = "update Games set Name = ?2, Description = ?3, PeopleAmount = ?4, CurrentPeopleAmount = ?5, StartDate = ?6, EndDate = ?7" +
            "where GameId = ?1", nativeQuery = true)
    void updateGame(Integer id, String name, String description, Integer peopleAmount, Integer currentPeopleAmount, Date gameStart, Date gameEnd);
    @Query(value = "update Games set CurrentPeopleAmount = CurrentPeopleAmount + 1 where GameId = ?1", nativeQuery = true)
    void addPerson(Integer id);
    @Query(value = "insert into GameCategories value(?1,?2)")
    void addGameCategory(Integer gameId, Integer categoryId);
    @Query(value = "delete GameCategories where GameId = ?1 and CategoryId = ?2")
    void deleteGameCategory(Integer gameId, Integer categoryId);
}
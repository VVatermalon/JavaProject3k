package com.games.demo.controller;

import com.games.demo.dto.GameDto;
import com.games.demo.entity.Category;
import com.games.demo.entity.Game;
import com.games.demo.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService service) {this.gameService = service;}

    @Operation(summary= "Get games amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Amount returned", content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/games/amount")
    public ResponseEntity<Integer> getAllGamesAmount() {
        return new ResponseEntity<Integer>(gameService.getAllGames().size(), HttpStatus.OK);
    }

    @Operation(summary= "Get games by input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games founded", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Games not founded"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/games/input")
    public ResponseEntity<Collection<Game>> getAllGamesByInput(@RequestParam("input") String input, @RequestParam("page") int page,
                                                               @RequestParam("size") int size) {
        try {
            Collection<Game> games = gameService.getGamesByName(input);
            if (games.size() == 0)
                return new ResponseEntity("Games not found", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary= "Get games by parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games founded", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Games not founded"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/games/params")
    public ResponseEntity<Collection<Game>> getAllGamesByParams(@RequestParam(value = "peopleAmount", required = false) int peopleAmount,
                                                                @RequestParam(value = "gameType", required = false) String gameType,
                                                                @RequestParam(value = "gameStart", required = false)
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date gameStart,
                                                                @RequestParam(value = "gameEnd", required = false)
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date gameEnd) {
        try {
            GameDto gameDto = new GameDto();
            gameDto.setStartDate(gameStart);
            gameDto.setEndDate(gameEnd);
            gameDto.setPeopleAmount(peopleAmount);
            List<String> categories = new ArrayList<>();
            categories.add(gameType);
            gameDto.setCategories(categories);
            Collection<Game> games = gameService.getGamesByParams(gameDto);
            if (games.size() == 0)
                return new ResponseEntity("Games not found", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary= "Create game", security = @SecurityRequirement(name = "ADMIN"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game created", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Resource forbidden"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/games")
    public ResponseEntity<Game> CreateGame(@RequestBody @Valid GameDto gameDto) {
        gameService.createGame(gameDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary= "Delete game by id", security = @SecurityRequirement(name = "ADMIN"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game deleted", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Game not founded"),
            @ApiResponse(responseCode = "403", description = "Resource forbidden"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/games/{id}")
    public ResponseEntity<Game> DeleteGameById(@PathVariable("id") Integer id) {
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary= "Update game by id", security = @SecurityRequirement(name = "ADMIN"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game deleted", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Game not founded"),
            @ApiResponse(responseCode = "403", description = "Resource forbidden"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/games/{id}")
    public ResponseEntity<Game> UpdateGameById(@PathVariable("id") Integer id, @RequestBody GameDto gameDto) {
        gameService.updateGame(id, gameDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

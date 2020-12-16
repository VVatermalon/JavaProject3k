package com.games.demo.controller;

import com.games.demo.dto.RequestDto;
import com.games.demo.entity.Request;
import com.games.demo.entity.Status;
import com.games.demo.service.RequestService;
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

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService service) {this.requestService = service;}

    @Operation(summary= "Leave request for game", security = @SecurityRequirement(name = "Auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request leaved", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/requests/leave")
    public ResponseEntity<Request> leaveRequest(@RequestParam(value = "gameId") Integer id,
                                                @RequestParam(value = "userId") Integer userId) {
        try {
            RequestDto reqDto = new RequestDto();
            reqDto.setGameId(id);
            reqDto.setUserId(userId);
            reqDto.setStatus(Status.newReq); // перенеси это в конструктор наверное
            requestService.createRequest(reqDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary= "Get all new requests for game", security = @SecurityRequirement(name = "ADMIN"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all new requests", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Requests not founded"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/requests")
    public ResponseEntity<Collection<Request>> getAllNewRequests() {
        try {
            Collection<Request> requests = requestService.getAllNewRequests();
            if (requests.size() == 0)
                return new ResponseEntity("New requests not found", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary= "Accept request", security = @SecurityRequirement(name = "ADMIN"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request accepted", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Request not founded"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/requests/accept/{id}")
    public ResponseEntity<Request> AcceptRequest(@PathVariable("id") Integer id) {
        requestService.acceptRequestById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary= "Reject request", security = @SecurityRequirement(name = "ADMIN"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request rejected", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Request not founded"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/requests/reject/{id}")
    public ResponseEntity<Request> RejectRequest(@PathVariable("id") Integer id) {
        requestService.rejectRequestById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

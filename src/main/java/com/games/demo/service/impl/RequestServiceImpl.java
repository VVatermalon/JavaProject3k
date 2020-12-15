package com.games.demo.service.impl;

import com.games.demo.dto.RequestDto;
import com.games.demo.entity.Request;
import com.games.demo.entity.Status;
import com.games.demo.repository.GameRepository;
import com.games.demo.repository.RequestRepository;
import com.games.demo.repository.UserRepository;
import com.games.demo.service.RequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository,
                              GameRepository gameRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getAllNewRequests() throws Exception {
        return requestRepository.getAllNewRequest().get();
    }

    @Override
    public Request getRequestById(Integer id) throws Exception {
        return requestRepository.findById(id).get();
    }

    @Override
    public Request createRequest(RequestDto requestDto) throws Exception {
        Request request = new Request();
        request.setStatus(Status.newReq);
        request.setUser(userRepository.findById(requestDto.getUserId()).get());
        request.setGame(gameRepository.findById(requestDto.getGameId()).get());
        requestRepository.save(request);
        return request;
    }

    @Override
    public void acceptRequestById(Integer id) {
        requestRepository.acceptRequest(id);
        gameRepository.addPerson(requestRepository.findById(id).get().getGame().getId());
    }

    @Override
    public void rejectRequestById(Integer id) {
        requestRepository.rejectRequest(id);
    }

    @Override
    public List<Request> findAll(Sort sort) {
        return requestRepository.findAll(sort);
    }

    @Override
    public List<Request> getAllUserRequests(Integer userId) throws Exception {
        return requestRepository.getAllUserRequest(userId).get();
    }

    @Override
    public Page<Request> findAll(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Override
    public List<Request> findAllById(Iterable<Integer> iterable) {
        return requestRepository.findAllById(iterable);
    }

    @Override
    public void deleteById(Integer integer) {
        requestRepository.deleteById(integer);
    }
}

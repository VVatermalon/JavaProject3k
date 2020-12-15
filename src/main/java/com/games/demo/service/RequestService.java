package com.games.demo.service;

import com.games.demo.dto.RequestDto;
import com.games.demo.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();
    List<Request> getAllNewRequests() throws Exception;
    List<Request> getAllUserRequests(Integer userId) throws Exception;
    Request getRequestById(Integer id) throws Exception;
    Request createRequest(RequestDto requestDto) throws Exception;
    void deleteById(Integer integer);
    void acceptRequestById(Integer id);
    void rejectRequestById(Integer id);

    List<Request> findAll(Sort sort);
    Page<Request> findAll(Pageable pageable);
    List<Request> findAllById(Iterable<Integer> iterable);
}
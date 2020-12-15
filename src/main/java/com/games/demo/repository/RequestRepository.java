package com.games.demo.repository;

import com.games.demo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query(value = "select * from Requests  where Status = 'newReq'", nativeQuery = true)
    Optional<List<Request>>  getAllNewRequest();
    @Query(value = "select Id, Status, UserId, GameId from Requests join Users on UserId = RequestId  where UserId = ?1", nativeQuery = true)
    Optional<List<Request>>  getAllUserRequest(Integer UserId);
    @Query(value = "update Requests set Status = 'accepted' where RequestId = ?1", nativeQuery = true)
    void  acceptRequest(Integer id);
    @Query(value = "update Requests set Status = 'rejected' where RequestId = ?1", nativeQuery = true)
    void  rejectRequest(Integer id);
}

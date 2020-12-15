package com.games.demo.repository;

import com.games.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    @Query(value = "select RoleId, RoleName from Roles join Users on Users.UserId = Roles.RoleId" +
            "where UserId = ?1", nativeQuery = true)
    List<Role> getUserRoles(Integer id);
}

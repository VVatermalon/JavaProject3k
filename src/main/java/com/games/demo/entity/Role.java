package com.games.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Roles")
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoleId", nullable = false)
    private Integer id;
    @Column(name = "RoleName", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "UserRoles", fetch = FetchType.LAZY)
    private List<User> users;
}

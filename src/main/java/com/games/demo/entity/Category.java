package com.games.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Categories")
@Data
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CategoryId", nullable = false)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "GameCategories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Game> games;
}

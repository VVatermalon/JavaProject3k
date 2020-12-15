package com.games.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity(name = "Games")
@Data
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GameId", nullable = false)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "StartDate", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh24:mi")
    private Date startDate;
    @Column(name = "EndDate", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh24:mi")
    private Date endDate;
    @Column(name = "PeopleAmount", nullable = false)
    private Integer peopleAmount;
    @Column(name = "CurrentPeopleAmount", nullable = false)
    private Integer currentPeopleAmount;

    @OneToMany(mappedBy = "GameRequests", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonBackReference
    private List<Request> requests;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "GameCategories", joinColumns = {@JoinColumn(name = "GameId")},
            inverseJoinColumns = {@JoinColumn(name = "CategoryId")})
    private List<Category> categories;
}
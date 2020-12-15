package com.games.demo.dto;

import com.games.demo.entity.Category;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class GameDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "Name must be equal or greater than 5 characters and less than 30 characters")
    private String name;

    @Size(max = 200, message = "Description must be less than 200 characters")
    private String description;

    @NotNull(message = "Start date cannot be null")
    @NotBlank(message = "Start date cannot be empty")
    private Date startDate;

    @NotNull(message = "Finish date cannot be null")
    @NotBlank(message = "Finish date cannot be empty")
    private Date endDate;

    @NotNull(message = "People amount cannot be null")
    @NotBlank(message = "People amount cannot be empty")
    private Integer peopleAmount;


    @NotNull(message = "Current people amount cannot be null")
    @NotBlank(message = "Current people amount cannot be empty")
    private Integer currentPeopleAmount;

    @NotNull(message = "Categories cannot be null")
    @NotBlank(message = "Categories cannot be empty")
    private List<String> categories;
}

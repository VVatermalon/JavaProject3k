package com.games.demo.dto;

import com.games.demo.entity.Category;
import com.games.demo.entity.Status;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class RequestDto {
    @NotBlank(message = "User ID cannot be empty")
    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotBlank(message = "Game ID cannot be empty")
    @NotNull(message = "Game ID cannot be null")
    private Integer gameId;

    @NotBlank(message = "Game status cannot be empty")
    @NotNull(message = "Game status cannot be null")
    private Status status;
}

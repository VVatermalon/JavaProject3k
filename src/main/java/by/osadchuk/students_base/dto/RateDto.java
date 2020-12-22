package by.osadchuk.students_base.dto;

import lombok.Data;

@Data
public class RateDto {
    private String username;
    private String subject;
    private Integer mark;
    private String description;
}

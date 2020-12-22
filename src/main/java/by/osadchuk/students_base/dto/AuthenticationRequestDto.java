package by.osadchuk.students_base.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto{
    private String username;
    private String password;
}

package net.javaguides.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentsDto {
    private Long id;
    @NotEmpty(message = "first name should not be empty")
    private String firstName;
    @NotEmpty(message = "last name should not be empty")
    private String lastName;
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "password should not be empty")
    private String password;
    @NotEmpty(message = "username should not be empty")
    private String username;
}

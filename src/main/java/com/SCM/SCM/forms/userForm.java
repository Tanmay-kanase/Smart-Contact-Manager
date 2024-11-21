package com.SCM.SCM.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class userForm {
    @NotBlank(message = "Username is required")
    @Size(min = 3 ,message = "Min 3 character is required")
    private String name;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 6 , message = "Min 6 characters required")
    private String password;
    @NotBlank(message = "About should not be blank")
    private String about;
    @Size(min = 8 , max = 12 , message = "Invalid phone number")
    private String phoneNumber;

}

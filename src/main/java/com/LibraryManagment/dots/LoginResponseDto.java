package com.LibraryManagment.dots;



import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
     private String token;
    private String type = "Bearer";
    private String username;
    private String message;

    public LoginResponseDto(String token, String username, String message) {
        this.token = token;
        this.username = username;
        this.message = message;
    }
}

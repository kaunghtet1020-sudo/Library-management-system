package com.LibraryManagment.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagment.dots.LoginRequestDto;
import com.LibraryManagment.dots.LoginResponseDto;
import com.LibraryManagment.dots.RegisterRecordDto;
import com.LibraryManagment.models.User;
import com.LibraryManagment.security.CustomUserDetailsService;
import com.LibraryManagment.security.JwtUtil;
import com.LibraryManagment.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
            System.out.println(userDetails.getUsername());
            String jwt = jwtUtil.generatedToken(userDetails);
            System.out.println("Token is " + jwt);
            return ResponseEntity.ok(new LoginResponseDto(jwt, userDetails.getUsername(), "Login success"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }
    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRecordDto registerRequest) {
    Optional<User> existingUserOpt = userService.findByUsername(registerRequest.getUsername());
    if (existingUserOpt.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(HttpStatus.CONFLICT.value());
    }

    User newUser = new User();
    newUser.setUsername(registerRequest.getUsername());
    newUser.setEmail(registerRequest.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    userService.register(newUser);

    return ResponseEntity.ok(HttpStatus.OK.value());
}

}

package edu.eci.arep.controllers;

import edu.eci.arep.models.User;
import edu.eci.arep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * Controller for authentication.
 * @author Andres Arias
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController (UserService userService) {
        this.userService = userService;
    }

    /**
     * Method for login.
     * @param body information for login
     * @return correct response if authentication is successful or not
     */
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User body) {
        try {
            Boolean result = userService.login(body);
            return Boolean.TRUE.equals(result) ? new ResponseEntity<>("correct credentials", HttpStatus.OK) : new ResponseEntity<>("incorrect credentials", HttpStatus.UNAUTHORIZED);
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.badRequest().body("Error generating password hash");
        }

    }

    /**
     * Method for register.
     * @param body information for register
     * @return correct response if registration is successful or not
     */
    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody User body){
        try{
            Boolean result = userService.register(body);
            return Boolean.TRUE.equals(result) ?  new ResponseEntity<>("Registration successful", HttpStatus.CREATED) : new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
        }   catch (NoSuchAlgorithmException e) {
            return ResponseEntity.badRequest().body("Error generating password hash");
        }
    }
}

package com.example.demo.controller;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){

        this.userService=userService;
    }
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createuser(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);

        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam Long userId, @RequestParam String status) {
        try {
            UserResponse updateResponse = userService.updateStatus(userId, status);

            if (updateResponse != null) {
                if ("Inactive".equalsIgnoreCase(status)) {
                    return ResponseEntity.status(HttpStatus.OK).body("User status has been set to Inactive.");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("User status updated successfully to Active.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid status provided. Allowed values are 'Active' or 'Inactive'.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating status: " + e.getMessage());
        }
    }

}
package com.ridoy.villa.controller;

import com.ridoy.villa.model.User;
import com.ridoy.villa.service.UserService;
import com.ridoy.villa.util.ApiResponse;
import com.ridoy.villa.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all Users
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users != null && !users.isEmpty()) {
                return ResponseEntity.ok(ResponseUtil.success("Users retrieved successfully", users));
            } else {
                return ResponseEntity.status(404).body(ResponseUtil.success("No users found", new ArrayList<>()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Users retrieval failed", e.getMessage()));
        }
    }

    // Get a User by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(ResponseUtil.success("User found", user));
        } else {
            return ResponseEntity.status(404).body(ResponseUtil.failed("User not found", null));
        }
    }

    // Create a new User
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser != null) {
            return ResponseEntity.ok(ResponseUtil.success("User created successfully", createdUser));
        } else {
            return ResponseEntity.status(400).body(ResponseUtil.failed("User creation failed", null));
        }
    }

    // Update an existing User
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            if (updatedUser != null) {
                return ResponseEntity.ok(ResponseUtil.success("User updated successfully", updatedUser));
            } else {
                return ResponseEntity.status(400).body(ResponseUtil.failed("User update failed", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("User update failed", e.getMessage()));
        }
    }

    // Delete a User by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ResponseUtil.success("User deleted successfully", null));
    }

    // Basic login endpoint
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody User user) {
        String userRole = userService.validateUser(user.getUsername(), user.getPassword());
        if (userRole != null) {
            return ResponseEntity.ok(ResponseUtil.success("Login successful!", userRole));
        } else {
            return ResponseEntity.status(401).body(ResponseUtil.failed("Invalid username or password", null));
        }
    }
}
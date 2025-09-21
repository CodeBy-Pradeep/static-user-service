package com.pradeep.microservices.staticuserservice.controller;

import com.pradeep.microservices.staticuserservice.model.User;
import com.pradeep.microservices.staticuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class StaticUserController {

    private final UserService userService;

    public StaticUserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Get all users (with optional filter by name)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(userService.getAllUsers(name));
    }

    // ✅ Get single user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // ✅ Get total count
    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok(userService.getUserCount());
    }

    // ✅ Mock dynamic user for demo
    @GetMapping("/dynamic/{id}")
    public ResponseEntity<User> getDynamicUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new User(id, "StaticUser" + id, "static@example.com"));
    }
}

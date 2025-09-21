package com.pradeep.microservices.staticuserservice.service;

import com.pradeep.microservices.staticuserservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final List<User> users = Arrays.asList(
            new User(1L, "Alice", "alice@example.com"),
            new User(2L, "Bob", "bob@example.com"),
            new User(3L, "Charlie", "charlie@example.com"),
            new User(4L, "David", "david@example.com")
    );

    public List<User> getAllUsers(String name) {
        if (name != null && !name.isEmpty()) {
            return users.stream()
                    .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public int getUserCount() {
        return users.size();
    }
}

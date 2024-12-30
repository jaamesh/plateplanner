package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.User;
import org.launchcode.PlatePlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public List<User> getAllSavedUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Optional<User> getSavedUser(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody @Valid User user, Errors errors) {
//        TODO: replace this line with appropriate User methods
        if(errors.hasErrors()) {
            System.out.println(errors);
        }
        return userRepository.save(user);
    }

    @PostMapping("/update/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody @Valid User user) {
        if (userRepository.existsById(userId)) {
            userRepository.save(user);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }

}

package rsoapp.userms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsoapp.userms.model.dto.UserProfile;
import rsoapp.userms.model.entity.User;
import rsoapp.userms.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        try {
            Optional<User> user = userService.getUserById(userId);
            if(user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("profile/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Integer userId) {
        try {
            return userService.getUserProfile(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
//        try {
//            return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
//        try {
//            userService.deleteUserById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

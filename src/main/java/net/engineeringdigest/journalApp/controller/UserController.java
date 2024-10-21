package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public Iterable<User> getAllUsers(){
//        return userService.getAll();
//    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
//        return new ResponseEntity<>(userService.findByusername(username),HttpStatus.OK);
//    }

//    Added to public controller
//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult result){
//        if(result.hasErrors())
//            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
//        userService.saveNewUser(user);
//        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user, BindingResult result){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userInDb =userService.findByusername(username);
        if(userInDb!=null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>("User details changed successfully",HttpStatus.ACCEPTED);
        }
        if(result.hasErrors())
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByusername(authentication.getName());
//        String username=authentication.getName();
//        User userInDb = userService.findByusername(username);
//        if(userInDb!=null){
//            userService.deleteEntry(userInDb.getId());
//            return new ResponseEntity<>("User deleted successfully",HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<>("User deleted successfully",HttpStatus.GONE);
    }
}
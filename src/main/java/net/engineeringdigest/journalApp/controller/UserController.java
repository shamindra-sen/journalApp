package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

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

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse= weatherService.getWeatherByCity("Kolkata");
        String greeting ="";
        if(weatherResponse!=null)
            greeting = "\nWelcome to the Journal App! Your current weather is " + weatherResponse.getCurrent().getWeatherDescription().get(0) + " and feels like "+weatherResponse.getCurrent().getFeelslike();
        return new ResponseEntity<>("Hello, " + authentication.getName() + greeting, HttpStatus.OK);
    }
}
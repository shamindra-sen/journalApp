package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
       if( userService.saveNewUser(user))
           return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
       else
           return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }
    @GetMapping("/health")
    public String healthCheck() {
        return "I'm healthy!";
    }
}

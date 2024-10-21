package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
       List<User> all= userService.getAllUsers();
       if(all!=null && !all.isEmpty()){
           return new ResponseEntity<>(all, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all-journals")
    public ResponseEntity<?> getAllJournalEntries() {
        List<JournalEntry> all=journalEntryService.getAll();
        if(all!=null &&!all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-admin")
    public ResponseEntity<?> addAdminUser(@RequestBody User user){
       if(userService.saveAdmin(user))
           return new ResponseEntity<>("Admin user added successfully", HttpStatus.CREATED);
       else
           return new ResponseEntity<>("Error adding admin user as duplicate username exists", HttpStatus.CONFLICT);
    }
}

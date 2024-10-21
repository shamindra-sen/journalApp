package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
       try {
           User user = userService.findByusername(username);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved=journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       }catch(Exception e){
           throw new RuntimeException("Something went wrong while saving data.....",e);
       }
    }
    
    public void saveEntry(JournalEntry journalEntry){
            journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteEntry(ObjectId id, String username){
        boolean removed=false;
        User user = userService.findByusername(username);
        removed= user.getJournalEntries().removeIf(je -> je.getId().equals(id));
        if(removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
        return removed;
    }

    public List<JournalEntry> getAllJournalEntriesOfUser(String username) {
        User user=userService.findByusername(username);
        return user.getJournalEntries();
    }

    //function to check if the JournalEntryID passed by the authenticated user belongs to his JournalEntry and not ID of other user's JournalEntry which might have been stolen by him.
    public boolean isJournalEntryIdOfAuthenticatedUser(String username, ObjectId id) {
        User user = userService.findByusername(username);
        return user.getJournalEntries().stream().anyMatch(je -> je.getId().equals(id));
    }
}

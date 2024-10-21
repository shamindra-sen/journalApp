//
//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController_v2 {
//
//    private Map<String, JournalEntry> journalEntries=new HashMap<>();
//    @GetMapping
//    public List<JournalEntry> getJournalEntries() {
//        return new ArrayList<>(journalEntries.values());
//    }
//    @GetMapping("/{id}")
//    public Optional<JournalEntry> getJournalEntryById(@PathVariable String id){
//        return Optional.ofNullable(journalEntries.get(id));
//    }
//    @PostMapping
//    public boolean createJournalEntry(@RequestBody JournalEntry entry){
//        journalEntries.put(entry.getId(), entry);
//        return true;
//    }
//    @PutMapping("/{id}")
//    public boolean updateJournalEntry(@PathVariable String id, @RequestBody JournalEntry updatedEntry){
//        journalEntries.put(id, updatedEntry);
//        return true;
//    }
//    @DeleteMapping("/{id}")
//    public JournalEntry deleteJournalEntry(@PathVariable String id){
//        journalEntries.remove(id);
//        return journalEntries.get(id);
//    }
//}

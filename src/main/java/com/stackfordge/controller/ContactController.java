package com.stackfordge.controller;
import com.stackfordge.model.Contact;
import com.stackfordge.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@Slf4j
public class ContactController {
    private final ContactService contactService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> submitContact(@Valid @RequestBody Contact contact) {
        try {
            Contact saved = contactService.saveContact(contact);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Thank you! Your inquiry has been received. We'll get back to you within 24 hours.");
            response.put("contactId", saved.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error submitting contact", e);
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "An error occurred. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }
    
    @GetMapping("/uncontacted")
    public ResponseEntity<List<Contact>> getUncontacted() {
        return ResponseEntity.ok(contactService.getUncontactedInquiries());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contactService.getContactById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/contacted")
    public ResponseEntity<Contact> markContacted(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contactService.markAsContacted(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            contactService.deleteContact(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Not found"));
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "StackFordge API"));
    }
}

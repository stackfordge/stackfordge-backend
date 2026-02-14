package com.stackfordge.controller;

import com.stackfordge.dto.ContactRequestDTO;
import com.stackfordge.model.Contact;
import com.stackfordge.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/contacts")
@RequiredArgsConstructor
public class PublicContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> submitContact(
            @Valid @RequestBody ContactRequestDTO request) {

        Contact saved = contactService.saveContact(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "success", true,
                        "contactId", saved.getId()
                )
        );
    }
    @GetMapping
    public List<Contact> getAll() {
        return contactService.getAllContacts();
    }

}

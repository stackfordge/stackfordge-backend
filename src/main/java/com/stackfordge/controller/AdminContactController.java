package com.stackfordge.controller;

import org.springframework.data.domain.Page;
import com.stackfordge.dto.ContactResponseDTO;
import com.stackfordge.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/contacts")
@RequiredArgsConstructor
public class AdminContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                contactService.getPaginated(page, size)
        );
    }

    @PutMapping("/{id}/contacted")
    public ResponseEntity<Void> markContacted(@PathVariable Long id) {
        contactService.markAsContacted(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}

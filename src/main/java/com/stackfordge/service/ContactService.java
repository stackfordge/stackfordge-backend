package com.stackfordge.service;

import com.stackfordge.dto.ContactRequestDTO;
import com.stackfordge.dto.ContactResponseDTO;
import com.stackfordge.exception.ResourceNotFoundException;
import com.stackfordge.model.Contact;
import com.stackfordge.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public Contact saveContact(ContactRequestDTO dto) {

        log.info("New contact submission | email={} | name={}", dto.getEmail(), dto.getName());

        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setProjectType(dto.getProjectType());
        contact.setBudget(dto.getBudget());
        contact.setTimeline(dto.getTimeline());
        contact.setMessage(dto.getMessage());
        contact.setContacted(false);

        return contactRepository.save(contact);
    }

    // Pagination (ADMIN)
    public Page<ContactResponseDTO> getPaginated(int page, int size) {

        Page<Contact> contacts = contactRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return contacts.map(this::mapToResponse);
    }

    public List<Contact> getUncontactedInquiries() {
        return contactRepository.findByContactedFalse();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
    }
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Transactional
    public Contact markAsContacted(Long id) {
        Contact contact = getContactById(id);
        contact.setContacted(true);
        return contact;
    }

    @Transactional
    public void deleteContact(Long id) {

        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact not found with id: " + id);
        }

        contactRepository.deleteById(id);
    }

    // DTO Mapper
    private ContactResponseDTO mapToResponse(Contact contact) {
        return ContactResponseDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .projectType(contact.getProjectType())
                .budget(contact.getBudget())
                .timeline(contact.getTimeline())
                .message(contact.getMessage())
                .contacted(contact.isContacted())
                .createdAt(contact.getCreatedAt())
                .build();
    }
}

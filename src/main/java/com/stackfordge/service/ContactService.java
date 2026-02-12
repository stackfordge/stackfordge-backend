package com.stackfordge.service;
import com.stackfordge.model.Contact;
import com.stackfordge.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {
    private final ContactRepository contactRepository;
    
    @Transactional
    public Contact saveContact(Contact contact) {
        log.info("Saving contact from: {}", contact.getEmail());
        return contactRepository.save(contact);
    }
    
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
    public List<Contact> getUncontactedInquiries() {
        return contactRepository.findByContactedFalse();
    }
    
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found: " + id));
    }
    
    @Transactional
    public Contact markAsContacted(Long id) {
        Contact contact = getContactById(id);
        contact.setContacted(true);
        return contactRepository.save(contact);
    }
    
    @Transactional
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}

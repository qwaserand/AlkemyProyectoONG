package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Contact;
import alkemy.challenge.Challenge.Alkemy.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ResponseEntity<?> createContact(Contact contact) {
        contactRepository.save(contact);
        return new ResponseEntity("el contacto ha sido creado con exito", HttpStatus.OK);
    }

    public List<Contact> bringAllContacts() {
        return contactRepository.findByDeletedFalse();
    }

    public Contact listContact(Long id) {
        return contactRepository.findAllById(id);
    }
}

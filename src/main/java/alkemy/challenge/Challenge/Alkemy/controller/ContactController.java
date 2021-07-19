package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Contact;
import alkemy.challenge.Challenge.Alkemy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contacts")
    public ResponseEntity<?> createContact(@RequestBody @Valid Contact contact) {
        return contactService.createContact(contact);
    }

    @GetMapping("/contacts")
    public List<Contact> bringAllContacts() {
        return contactService.bringAllContacts();
    }

    @GetMapping("/backoffice/contacts/{id}")
    public Contact listContact(@PathVariable("id") long id){
        return contactService.listContact(id);
    }


}

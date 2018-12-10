package com.doorkel.schoolynk.controller;

import com.doorkel.schoolynk.domain.Contact;
import com.doorkel.schoolynk.exception.CustomError;
import com.doorkel.schoolynk.service.ContactService;
import com.doorkel.schoolynk.util.SchoolynkConstants;
import io.swagger.annotations.Api;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Contact Info")
@RestController
public class ContactRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ContactRestController.class);

  @Autowired
  private ContactService contactService;

  /**
   * Retrieve All Contacts.
   *
   * @return Contact List.
   */
  @GetMapping(value = SchoolynkConstants.contactsListUrl)
  public ResponseEntity<List<Contact>> listAllUsers() {
    List<Contact> contacts = contactService.findAllContacts();
    LOGGER.info("Contact size:" + contacts.size());
    if (contacts.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
  }


  /**
   * Get a contact info.
   *
   * @param id contact id
   * @return response entity.
   */
  @GetMapping(value = SchoolynkConstants.contactsDetailUrl)
  public ResponseEntity<?> getContact(@PathVariable("id") long id) {
    Contact contact = contactService.findContactById(id);
    if (contact == null) {
      return new ResponseEntity<>(new CustomError("Unable to Find.", "5003"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Contact>(contact, HttpStatus.OK);
  }


  /**
   * Create a Contact.
   *
   * @param contact contact dto
   * @return http status
   */
  @PostMapping(value = SchoolynkConstants.contactsListUrl)
  public ResponseEntity<?> createContact(@RequestBody Contact contact) {

    /*
     * if (contactService.isContactExists(contact.getFirstName())) { return new ResponseEntity<>(
     * new CustomError( "Unable to Create Account with email " + contact.getFirstName() +
     * " not found.", "5003"), HttpStatus.NOT_FOUND); }
     */
    contactService.save(contact);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Update Contact List.
   *
   * @param id contact id
   * @param contact contact dto
   * @return response
   */
  @PutMapping(value = SchoolynkConstants.contactsDetailUrl)
  public ResponseEntity<?> updateContact(@PathVariable long id, @RequestBody Contact contact) {
    final Contact current = contactService.findContactById(id);
    if (current == null) {
      return new ResponseEntity<>(new CustomError("Unable to update.", "5003"),
          HttpStatus.NOT_FOUND);
    }
    /*
     * current.setFirstName( contact.getFirstName() != null ? contact.getFirstName() :
     * current.getFirstName()); current .setLastName(contact.getLastName() != null ?
     * contact.getLastName() : current.getLastName()); current.setEvaluations(
     * contact.getEvaluations() != null ? contact.getEvaluations() : current.getEvaluations());
     * current.setGender(contact.getGender() != null ? contact.getGender() : current.getGender());
     * current.setNationality( contact.getNationality() != null ? contact.getNationality() :
     * current.getNationality()); current.setPresentCountry(contact.getPresentCountry() != null ?
     * contact.getPresentCountry() : current.getPresentCountry()); current.setDateOfBirth(
     * contact.getDateOfBirth() != null ? contact.getDateOfBirth() : current.getDateOfBirth());
     * contactService.update(0L, current);
     */
    return new ResponseEntity<Contact>(current, HttpStatus.OK);
  }

  /**
   * Delete Contact.
   *
   * @param id contact id
   * @return response
   */
  @DeleteMapping(value = SchoolynkConstants.contactsDetailUrl)
  public ResponseEntity<?> deleteContact(@PathVariable long id) {
    final Contact contact = contactService.findContactById(id);
    if (contact == null) {
      return new ResponseEntity<>(new CustomError("Not found.", "5003"), HttpStatus.NOT_FOUND);
    }
    contactService.delete(contact.getContactId());
    return new ResponseEntity<Contact>(contact, HttpStatus.OK);
  }

}

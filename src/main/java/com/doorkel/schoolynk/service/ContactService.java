package com.doorkel.schoolynk.service;

import com.doorkel.schoolynk.domain.Contact;
import java.util.List;

public interface ContactService {

  List<Contact> findAllContacts();

  Contact findContactById(Long id);

  void save(Contact contact);

  void update(Long id, final Contact contact);

  void delete(Long id);

}

package com.doorkel.schoolynk.dao;

import com.doorkel.schoolynk.domain.Contact;
import java.util.List;

public interface ContactDao {

  List<Contact> findAllContacts();

  Contact findContactById(Long id);

  void save(Contact contact);

  void updateContact(Long id, final Contact contact);

  void deleteContact(Long id);

}

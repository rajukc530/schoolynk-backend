package com.doorkel.schoolynk.service;

import com.doorkel.schoolynk.dao.ContactDao;
import com.doorkel.schoolynk.domain.Contact;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

  @Autowired
  ContactDao contactDao;

  @Override
  public List<Contact> findAllContacts() {
    return contactDao.findAllContacts();
  }

  @Override
  public Contact findContactById(Long id) {
    return contactDao.findContactById(id);
  }

  @Override
  public void save(Contact contact) {
    contactDao.save(contact);
  }

  @Override
  public void update(Long id, Contact contact) {
    contactDao.updateContact(id, contact);

  }

  @Override
  public void delete(Long id) {
    contactDao.deleteContact(id);

  }

}

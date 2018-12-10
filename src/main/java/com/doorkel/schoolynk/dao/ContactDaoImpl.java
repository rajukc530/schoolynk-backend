package com.doorkel.schoolynk.dao;

import com.doorkel.schoolynk.domain.Contact;
import com.doorkel.schoolynk.service.base.OfyService;
import java.util.List;
import org.assertj.core.util.CheckReturnValue;
import org.springframework.stereotype.Repository;

@Repository
@CheckReturnValue
public class ContactDaoImpl implements ContactDao {

  @Override
  public List<Contact> findAllContacts() {
    return OfyService
        .ofy().load().type(Contact.class).list();
  }

  @Override
  public Contact findContactById(Long id) {
    return OfyService.ofy().load().type(Contact.class).id(id).now();
  }

  @Override
  public void save(Contact contact) {
    OfyService.ofy().save().entity(contact).now();
  }

  @Override
  public void updateContact(Long id, Contact contact) {
    OfyService.ofy().save().entity(contact).now();

  }

  @Override
  public void deleteContact(Long id) {
    OfyService.ofy().delete().type(Contact.class).id(id).now();
  }

}

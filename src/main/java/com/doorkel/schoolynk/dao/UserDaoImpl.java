package com.doorkel.schoolynk.dao;

import com.doorkel.schoolynk.domain.UserDetail;
import com.doorkel.schoolynk.service.base.OfyService;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

  @Override
  public List<UserDetail> findAllUsers() {
    return OfyService
        .ofy().load().type(UserDetail.class).list();
  }

  @Override
  public UserDetail findUserById(Long id) {
    return null;
  }

  @Override
  public UserDetail findUserByEmail(String email) {
    List<UserDetail> userDetailList = OfyService
        .ofy().load().type(UserDetail.class).filter("email =", email).list();
    if (userDetailList.size() > 0) {
      return userDetailList.get(0);
    } else {
      return null;
    }

  }

  @Override
  public void save(UserDetail userDetail) {
    OfyService.ofy().save().entity(userDetail).now();
  }

  @Override
  public void update(Long id, UserDetail userDetail) {
    OfyService.ofy().save().entity(userDetail).now();

  }

  @Override
  public void delete(Long id) {
    OfyService.ofy().delete().type(UserDetail.class).id(id).now();
  }

  @Override
  public boolean isUserExists(String email) {
    List<UserDetail> userDetailList = OfyService
        .ofy().load().type(UserDetail.class).filter("email =", email).list();
    if (userDetailList.size() > 0) {
      return true;
    } else {
      return false;
    }
  }
}

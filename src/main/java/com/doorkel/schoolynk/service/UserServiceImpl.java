package com.doorkel.schoolynk.service;

import com.doorkel.schoolynk.dao.UserDao;
import com.doorkel.schoolynk.domain.UserDetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

  @Override
  public List<UserDetail> findAllUsers() {
    return null;
  }

  @Override
  public UserDetail findUserById(Long id) {
    return null;
  }

  @Override
  public UserDetail findUserByEmail(String email) {
    return userDao.findUserByEmail(email);
  }

  @Override
  public void save(UserDetail userDetail) {
    userDao.save(userDetail);
  }

  @Override
  public void update(Long id, UserDetail userDetail) {
    userDao.update(id, userDetail);

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public boolean isUserExists(String email) {
    return userDao.isUserExists(email);
  }
}

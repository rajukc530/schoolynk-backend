package com.doorkel.schoolynk.dao;

import com.doorkel.schoolynk.domain.UserDetail;
import java.util.List;

public interface UserDao {

  List<UserDetail> findAllUsers();

  UserDetail findUserById(Long id);

  UserDetail findUserByEmail(String email);

  void save(UserDetail userDetail);

  void update(Long id, final UserDetail userDetail);

  void delete(Long id);

  boolean isUserExists(String email);

}

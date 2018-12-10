package com.doorkel.schoolynk.service;

import com.doorkel.schoolynk.dao.UserDao;
import com.doorkel.schoolynk.domain.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserDetailsService {

  @Autowired
  UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserDetail userDetail = userDao.findUserByEmail(username);

    if (userDetail != null) {
      return User.withUsername(username)
          .password(userDetail.getPassword())
          .authorities("ROLE_USER")
          .build();
    }
    throw new UsernameNotFoundException(username);

  }

}
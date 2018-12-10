package com.doorkel.schoolynk.controller;

import static com.doorkel.schoolynk.util.SecurityConstants.SIGNUP_URL;

import com.doorkel.schoolynk.domain.Account;
import com.doorkel.schoolynk.domain.UserDetail;
import com.doorkel.schoolynk.dto.ResultDto;
import com.doorkel.schoolynk.dto.UserDto;
import com.doorkel.schoolynk.exception.CustomError;
import com.doorkel.schoolynk.service.UserService;
import com.doorkel.schoolynk.service.base.OfyService;
import com.google.appengine.api.NamespaceManager;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "User Info")
@RestController
public class UserAuthenticationRestController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(UserAuthenticationRestController.class);

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  // private ApplicationUserRepository applicationUserRepository;

  /**
   * signup endpoint.
   *
   * @param user user info details
   * @return string
   */
  @PostMapping(value = SIGNUP_URL)
  public ResponseEntity<Void> signup(@Valid @RequestBody UserDto user) {

    if (userService.isUserExists(user.getEmail())) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    final UserDetail userDetail = new UserDetail();
    userDetail.setEmail(user.getEmail());
    userDetail.setFirstName(user.getFirstName());
    userDetail.setLastName(user.getLastName());
    userDetail.setPassword(encoder.encode(user.getPassword()));
    userService.save(userDetail);

    NamespaceManager.set("accountId");
    final Account account = new Account();
    account.setName(user.getEmail());
    OfyService.ofy().save().entity(account).now();
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Get User Detail by email address endpoint.
   *
   * @param email email address
   * @return user detail object
   */
  @GetMapping(value = "/user/{email}")
  public ResponseEntity<UserDetail> findUserByEmail(@PathVariable String email) {
    final UserDetail userDetail = userService.findUserByEmail(email);
    if (userDetail == null) {
      return new ResponseEntity(new CustomError("User with email not found", "5001"),
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);

  }

  /**
   * Check for already exists users.
   *
   * @param email email para
   * @return ResultDto with true or false
   */
  @GetMapping(value = "/user/exist/{email}")
  public ResponseEntity<ResultDto> userExists(@PathVariable String email) {
    final ResultDto dto = new ResultDto();
    if (userService.isUserExists(email)) {
      dto.setFlag(true);
    } else {
      dto.setFlag(false);
    }
    return new ResponseEntity<ResultDto>(dto, HttpStatus.OK);
  }

  /**
   * Update user info.
   *
   * @param email email id
   * @param user user dto object
   * @return response entity
   */
  @PutMapping(value = "/user/{email}")
  public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserDto user) {
    final UserDetail userDetail = userService.findUserByEmail(email);
    if (userDetail == null) {
      return new ResponseEntity<>(
          new CustomError("Unable to update. User with email " + email + " not found.", "5003"),
          HttpStatus.NOT_FOUND);
    }
    userDetail.setFirstName(
        user.getFirstName() != null ? user.getFirstName() : userDetail.getFirstName());
    userDetail
        .setLastName(user.getLastName() != null ? user.getLastName() : userDetail.getLastName());
    userDetail.setPassword(
        user.getPassword() != null ? encoder.encode(user.getPassword()) : userDetail.getPassword());
    userService.update(0L, userDetail);
    return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);
  }

  /**
   * Delete User.
   *
   * @param email email id
   * @return reponse entity
   */
  @DeleteMapping(value = "/user/{email}")
  public ResponseEntity<?> deleteUser(@PathVariable String email) {
    final UserDetail userDetail = userService.findUserByEmail(email);
    if (userDetail == null) {
      return new ResponseEntity<>(
          new CustomError("Unable to update. User with email " + email + " not found.", "5003"),
          HttpStatus.NOT_FOUND);
    }
    userService.delete(userDetail.getUserId());
    return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);
  }

}
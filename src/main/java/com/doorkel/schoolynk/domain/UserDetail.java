package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserDetail implements Serializable {

  @Id
  private Long userId;

  @Index
  private String email;

  private String password;

  private String firstName;

  private String lastName;

  // @Getter
  //@Setter
  //private List<Account> accountList;


}

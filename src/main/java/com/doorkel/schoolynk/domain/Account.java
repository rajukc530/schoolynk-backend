package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Account {

  @Id
  private Long accountId;

  @Getter
  @Setter
  private String name;

}

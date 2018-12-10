package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class UserInfo {

  @Id
  public Long id;

  @Getter
  @Setter
  private String name = null;

  /**
   * constructor.
   */
  public UserInfo() {
  }

  /**
   * constructor.
   *
   * @param name name
   */
  public UserInfo(String name) {
    this.name = name;
  }

}

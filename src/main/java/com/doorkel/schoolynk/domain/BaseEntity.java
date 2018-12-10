package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BaseEntity {

  @Index
  @Id
  protected Long id;

}

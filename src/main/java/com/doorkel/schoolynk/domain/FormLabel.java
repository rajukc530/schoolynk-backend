package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FormLabel extends BaseEntity {
  @Index
  private String en;
  @Index
  private String ja;
}

package com.doorkel.schoolynk.domain;

import com.doorkel.schoolynk.util.ValidationType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Validation extends BaseEntity {
  @Index
  private ValidationType type;
  @Index
  private String options;
}

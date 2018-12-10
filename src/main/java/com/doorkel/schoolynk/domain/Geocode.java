package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Geocode extends BaseEntity {
  private Double latitude;
  private Double longitude;
}

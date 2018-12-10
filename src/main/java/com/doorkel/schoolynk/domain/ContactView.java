package com.doorkel.schoolynk.domain;

import com.doorkel.schoolynk.util.ViewType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ContactView extends BaseEntity {
  @Index
  private ViewType key;
  @Index
  private FormItem viewItem;
}

package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "ContactItem")
public class ContactItem {

  @Index
  @Id
  private Long contactItemId;
  @Index
  @Parent
  Key<Contact> contact;
  @Index
  private Long formItemId; //ID for FormItemID
  @Index
  private String value;
}

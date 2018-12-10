package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;


@Getter
@Setter
@Entity(name = "Contact")
public class Contact {

  @Index
  @Id
  private Long contactId;
  @Index
  private String firstName;
  @Index
  private String lastName;
  @Index
  private String telephone;
  @Index
  private String mainEmail;
  @Index
  private List<String> emails;
  @Index
  private String birthDay;
  @Index
  private String status;
  private String comment;
  @Index
  private Long rank;
  @Index
  private List<Ref<UserDetail>> staffIds;
  @Index
  private List<Ref<Action>> actions;
  @Index
  private LocalDate createdAt;
  @Index
  private LocalDate updatedAt;
  @Index
  private List<Ref<ContactItem>> items;

  @OnSave
  protected void prePersist() {
    if (contactId == null) {
      createdAt = new LocalDate();
    }
    updatedAt = new LocalDate();
  }
}

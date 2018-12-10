package com.doorkel.schoolynk.domain;

import com.doorkel.schoolynk.util.Status;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

@Getter
@Setter
@Entity
public class Participant extends BaseEntity {
  @Index
  private Ref<Contact> contactId;
  @Index
  private String name;
  @Index
  private String email;
  @Index
  private Status status;
  @Index
  private Ref<Form> formId;
  @Index
  private Ref<FormAnswer> answerId;
  @Index
  private LocalDate createdAt;
  @Index
  private LocalDate updatedAt;
  @Index
  private Ref<UserDetail> createdBy;

  @OnSave
  protected void prePersist() {
    if (id == null) {
      createdAt = new LocalDate();
    }
    updatedAt = new LocalDate();
  }
}

package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

@Getter
@Setter
@Entity
public class FormAnswer extends BaseEntity {
  @Index
  private Ref<Contact> contactId;
  @Index
  private Ref<Form> formId;
  @Index
  private List<Ref<UserDetail>> seenBys;
  @Index
  private Ref<UserDetail> staffId;
  @Index
  private List<Ref<FormAnswerItem>> items;
  @Index
  private LocalDate createdAt;
  @Index
  private LocalDate updatedAt;

  @OnSave
  protected void prePersist() {
    if (id == null) {
      createdAt = new LocalDate();
    }
    updatedAt = new LocalDate();
  }
}

package com.doorkel.schoolynk.domain;

import com.doorkel.schoolynk.util.StatusForm;
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
public class Form extends BaseEntity {
  @Index
  private String formName;
  @Index
  private String headerText;
  @Index
  private String footerText;
  @Index
  private StatusForm status;
  @Index
  private List<Ref<FormAnswer>> answersIds;
  @Index
  private List<Ref<UserDetail>> createdBy;
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

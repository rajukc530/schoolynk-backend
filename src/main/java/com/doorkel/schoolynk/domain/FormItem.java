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
public class FormItem extends BaseEntity {
  @Index
  private String name;
  @Index
  private Ref<FormLabel> label;
  @Index
  private List<Ref<Validation>> validations;
  @Index
  private List<Ref<FormItem>> options;
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

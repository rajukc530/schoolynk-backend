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
@Entity(name = "Action")
public class Action {

  @Index
  @Id
  private Long actionId;
  @Index
  private Ref<Contact> contact;
  @Index
  private LocalDate dateTime;
  @Index
  private boolean isAutoAdded;
  @Index
  private String type;
  private String detail;
  private String kind;
  private Long staffId;
  private String memo;
  private List<Long> seenBys;
  private List<Ref<ActionComment>> comments;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  @OnSave
  protected void prePersist() {
    if (actionId == null) {
      createdAt = new LocalDate();
    }
    updatedAt = new LocalDate();
  }

}

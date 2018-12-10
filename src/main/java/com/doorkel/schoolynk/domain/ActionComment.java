package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

@Getter
@Setter
@Entity(name = "ActionComment")
public class ActionComment {
  @Index
  @Id
  private Long actionCommentId;
  @Index
  private Ref<Action> action;
  @Index
  private LocalDate createdAt;
  @Index
  private LocalDate updatedAt;
  @Index
  private Ref<UserDetail> userDetail;
  private String text;

  @OnSave
  protected void prePersist() {
    if (actionCommentId == null) {
      createdAt = new LocalDate();
    }
    updatedAt = new LocalDate();
  }

}

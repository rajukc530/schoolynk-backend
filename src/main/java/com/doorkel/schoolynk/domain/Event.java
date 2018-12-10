package com.doorkel.schoolynk.domain;

import com.doorkel.schoolynk.util.Status;
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
public class Event extends BaseEntity {
  @Index
  private String eventName;
  @Index
  private LocalDate since;
  @Index
  private LocalDate until;
  @Index
  private List<Ref<UserDetail>> staffIds;
  @Index
  private Status status;
  @Index
  private String type;
  @Index
  private String placeName;
  private String placeId;
  @Index
  private String address;
  private Geocode geocode;
  private String comment;
  @Index
  private List<Ref<Form>> attendFormsIds;
  @Index
  private List<Ref<Form>> formIds;
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

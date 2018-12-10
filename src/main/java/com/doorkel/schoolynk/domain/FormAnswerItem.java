package com.doorkel.schoolynk.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FormAnswerItem extends BaseEntity {
  @Index
  @Parent
  private Ref<FormAnswer> parentPath;
  @Index
  private Long formItemId;
  @Index
  private String value;
  @Index
  private Ref<FormItem> parentItemId;

}

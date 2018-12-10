package com.doorkel.schoolynk.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {

  @Getter
  @Setter
  private String email;

  @Getter
  @Setter
  private String password;

}
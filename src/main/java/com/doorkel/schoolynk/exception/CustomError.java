package com.doorkel.schoolynk.exception;

import lombok.Getter;

@Getter
public class CustomError {

  private String errorMessage;
  private String errorCode;

  public CustomError(String errorMessage, String errorCode) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

}
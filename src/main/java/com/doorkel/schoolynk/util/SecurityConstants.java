package com.doorkel.schoolynk.util;

public class SecurityConstants {

  public static final String SECRET = "schoolynksecret";
  public static final long EXPIRATION_TIME = 28_800_000; // 8 hours
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGNUP_URL = "/user/signup";
  public static final String LOGIN_URL = "/user/login";
  public static final String LOGIN_ID = "email"; // default:email
  public static final String PASSWORD = "password"; // default:password
}
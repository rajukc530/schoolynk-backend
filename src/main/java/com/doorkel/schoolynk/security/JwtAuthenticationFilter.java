package com.doorkel.schoolynk.security;

import static com.doorkel.schoolynk.util.SecurityConstants.EXPIRATION_TIME;
import static com.doorkel.schoolynk.util.SecurityConstants.LOGIN_ID;
import static com.doorkel.schoolynk.util.SecurityConstants.LOGIN_URL;
import static com.doorkel.schoolynk.util.SecurityConstants.PASSWORD;
import static com.doorkel.schoolynk.util.SecurityConstants.SECRET;

import com.doorkel.schoolynk.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  private AuthenticationManager authenticationManager;
  private BCryptPasswordEncoder encoder;

  /**
   * constructor.
   *
   * @param authenticationManager auth manager
   * @param encoder password encoder
   */
  public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
      BCryptPasswordEncoder encoder) {
    this.authenticationManager = authenticationManager;
    this.encoder = encoder;
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URL, "POST"));
    setUsernameParameter(LOGIN_ID);
    setPasswordParameter(PASSWORD);

  }

  /**
   * attempt authentication.
   *
   * @param req http request
   * @param res http response
   * @return Authentication
   * @throws AuthenticationException Authentication exception
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
      HttpServletResponse res) throws AuthenticationException {
    try {
      LoginDto loginDto = new ObjectMapper().readValue(req.getInputStream(), LoginDto.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginDto.getEmail(),
              loginDto.getPassword(),
              new ArrayList<>())
      );
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  /**
   * success authentication.
   *
   * @param req http request
   * @param res http response
   * @param chain Filter chain
   * @param auth Authentication
   * @throws IOException io exception
   * @throws ServletException servlet exception
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain,
      Authentication auth) throws IOException, ServletException {
    String token = Jwts.builder()
        .setSubject(((User) auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
        .compact();
    // res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    res.setStatus(HttpStatus.OK.value());
    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    JSONObject jsonResponse = new JSONObject();
    try {
      jsonResponse.put("message", "Login Successful");
      jsonResponse.put("token", token);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    res.getWriter().write(jsonResponse.toString());
  }

}
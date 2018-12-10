package com.doorkel.schoolynk.security;

import static com.doorkel.schoolynk.util.SecurityConstants.HEADER_STRING;
import static com.doorkel.schoolynk.util.SecurityConstants.SECRET;
import static com.doorkel.schoolynk.util.SecurityConstants.TOKEN_PREFIX;

import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  //private AuthenticationManager authenticationManager;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain)
      throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
      throws UnsupportedEncodingException {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      String user = Jwts.parser().setSigningKey(SECRET.getBytes("UTF-8"))
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody().getSubject();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      }
      return null;
    }
    return null;
  }

}
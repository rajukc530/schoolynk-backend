package com.doorkel.schoolynk.security.config;

import static com.doorkel.schoolynk.util.SecurityConstants.LOGIN_URL;
import static com.doorkel.schoolynk.util.SecurityConstants.SIGNUP_URL;

import com.doorkel.schoolynk.api.config.CorsRequestFilter;
import com.doorkel.schoolynk.security.JwtAuthenticationFilter;
import com.doorkel.schoolynk.security.JwtAuthorizationFilter;
import com.doorkel.schoolynk.service.UserAuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_WHITELIST = {
      // -- swagger ui
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      "/name",
      "/_ah/**",
      "/user/exist/**"

      // other public endpoints of your API may be appended to this array
  };
  @Autowired
  private UserAuthenticationServiceImpl userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and().authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(SIGNUP_URL, LOGIN_URL).permitAll()
        .anyRequest().authenticated()
        .and().logout()
        .and().csrf().disable()
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), getPasswordEncoder()))
        .addFilter(new JwtAuthorizationFilter(authenticationManager()))
        .addFilterBefore(new CorsRequestFilter(), ChannelProcessingFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    ;
  }

  /**
   * configure Auth.
   *
   * @param auth auth builder
   * @throws Exception excpetion
   */
  @Autowired
  public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());

  }

  /**
   * get BCrypt PasswordEncoder.
   *
   * @return BCryptPasswordEncoder
   */
  @Bean
  public BCryptPasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

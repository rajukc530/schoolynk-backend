package com.doorkel.schoolynk.api.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {

  private static final Logger logger = LoggerFactory
      .getLogger(WebConfig.class);

  /**
   * set namespace filter.
   *
   * @return filter
   */
  @Bean
  public FilterRegistrationBean namespaceFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new DefaultNameSpaceFilter());
    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
    registration.addUrlPatterns("/*");
    registration.setOrder(1);
    return registration;
  }


  /**
   * set namespace filter.
   *
   * @return filter
   */
  @Bean
  public FilterRegistrationBean accountNamespaceFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new AccountNameSpaceFilter());
    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
    registration.addUrlPatterns("/account/*");
    registration.setOrder(2);
    return registration;
  }

  /**
   * Set Objectify Filter.
   *
   * @return Filter
   */
  @Bean
  public FilterRegistrationBean objectifyFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new com.googlecode.objectify.ObjectifyFilter());
    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
    registration.addUrlPatterns("/*");
    registration.setOrder(4);
    return registration;
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


}

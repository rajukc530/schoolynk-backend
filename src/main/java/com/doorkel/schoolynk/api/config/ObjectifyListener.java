package com.doorkel.schoolynk.api.config;

import com.doorkel.schoolynk.domain.UserInfo;
import com.googlecode.objectify.ObjectifyService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ObjectifyListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ObjectifyService.register(UserInfo.class);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}

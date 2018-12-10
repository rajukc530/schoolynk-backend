package com.doorkel.schoolynk.api.config;

import com.google.appengine.api.NamespaceManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// [START nsfilter]
// Filter to set the Google Apps domain as the namespace.
@WebFilter(urlPatterns = {"/*"})
public class DefaultNameSpaceFilter implements Filter {

  private static final Logger logger = LoggerFactory
      .getLogger(DefaultNameSpaceFilter.class);

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    // Make sure set() is only called if the current namespace is not already set.

    if (NamespaceManager.get() == null || NamespaceManager.get().isEmpty()) {
      // If your app is hosted on appspot, this will be empty. Otherwise it will be the domain
      // the app is hosted on.
      NamespaceManager.set(NamespaceManager.getGoogleAppsNamespace());
    }
    chain.doFilter(req, res); // Pass request back down the filter chain
  }
  // [END nsfilter]

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy() {
  }

}
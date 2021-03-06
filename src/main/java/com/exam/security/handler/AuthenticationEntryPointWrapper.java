package com.exam.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

public class AuthenticationEntryPointWrapper implements AuthenticationEntryPoint {
  
  protected Logger logger = LoggerFactory.getLogger(this.getClass());
  
  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    
    // 로그인 권한이 없는 페이지에 대한 접근인 경우 처리
    logger.debug("AuthenticationEntryPointWrapper = {}", authException);
  
    request.setAttribute("ex", authException);
    redirectStrategy.sendRedirect(request, response, "/denyAnonymousUrl.do");
    
  }

}

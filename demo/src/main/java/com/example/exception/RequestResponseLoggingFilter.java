package com.example.exception;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
//@Order(2)
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        
        // use httprequest wrapper 
        
        CachedBodyHttpServletRequest requestClone = new CachedBodyHttpServletRequest(req);
        if ("POST".equalsIgnoreCase(req.getMethod())) 
        {
          Object  test = requestClone.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
          requestClone.setAttribute("postdata1", test);
        }
        info(
          "Filter .................Logging Request  {} : {}"+ req.getMethod(), 
          req.getRequestURI());
        chain.doFilter(requestClone, response);
        info(
          "Logging Response :{}", 
          res.getContentType());
    }
    public void info() {
    	
    }
    
    private void info( String msg, String msg1) {
		 System.out.println(msg+" : "+msg1);
	 }

    // other methods
}
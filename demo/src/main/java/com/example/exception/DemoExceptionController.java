package com.example.exception;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DemoExceptionController {
	 @ExceptionHandler(value = DemoServiceException.class)
	   public ResponseEntity<Object> exception(DemoServiceException exception,/* HttpHeaders headers,
			      HttpStatus status,*/
			   ServletRequest request) {
		 debug(" Inside excpetion controller siddharth...###################");
		 debug("excep: "+exception);
	HttpServletRequest req =null;
	Object inputRequestData =null;
	ServletWebRequest ww=null;
	if( request instanceof HttpServletRequest) {
		req= (HttpServletRequest) request;
		String method =req.getMethod();
		
		if("POST".equalsIgnoreCase(method)|| "PUT".equalsIgnoreCase(method)) {
		inputRequestData =req.getAttribute("postdata");
	}else {
		Enumeration<String> enumD =req.getAttributeNames();
		while(enumD.hasMoreElements()) {
			String key =enumD.nextElement();
			inputRequestData += " {{" +key +" :"+req.getAttribute(key) +"}}";
		}
	}
		debug("post data byte... "+ req.getAttribute("postdata"));
	
	
		debug("post data postdata1... "+ req.getAttribute("postdata1"));	
	}else {
		debug(" Not httpservlet request ...................NNNNNNHIII  request="+request);
	}
		 //debug("headers: "+headers);
		 //debug("status: "+status);
	debug( " input data "+inputRequestData);
		 debug("request: "+request);
		 
	
		 
	      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	   }
	 
	 
	 
	 @ExceptionHandler(value = Throwable.class)
	   public ResponseEntity<Object> exceptionT(DemoServiceException exception,Object body,  HttpHeaders headers,
			      HttpStatus status,
			      WebRequest request) {
		 debug(" Inside Throable controller siddharth...");
		 debug("excep: "+exception);
		 debug("body: "+body);
		 debug("headers: "+headers);
		 debug("status: "+status);
		 debug("request: "+request);
		 
	      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	   }
	 
	 /*  Exception ex,
     Object body,
     HttpHeaders headers,
     HttpStatus status,
     WebRequest request
     */
	 
	 private void debug( String msg) {
		 System.out.println(""+msg);
	 }
}

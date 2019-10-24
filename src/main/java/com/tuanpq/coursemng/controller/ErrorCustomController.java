/**
 * @author admin
 * @date 05-10-2019
 */

package com.tuanpq.coursemng.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorCustomController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	@RequestMapping("/error")
	public String handleError(Model model, HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    Integer statusCode = null;
	    if (status != null) {
	        statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "/errors/error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "/errors/error-404";
	        }
	        else if(statusCode == HttpStatus.FORBIDDEN.value()) {
	            return "/errors/error-404";
	        }
	    }
	    model.addAttribute("errorCode", statusCode);
	    return "/errors/error";
	}

}

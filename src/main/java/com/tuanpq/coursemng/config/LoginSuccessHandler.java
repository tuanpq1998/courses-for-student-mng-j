/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tuanpq.coursemng.entity.Role;
import com.tuanpq.coursemng.utility.ConstantDelivery;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	ConstantDelivery constantDelivery;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/course/");
	}

	/*private String getUrl(Authentication authentication) {
		List<Role> roles = (List<Role>) authentication.getAuthorities();
		for (Role role : roles) {
			if (role.getName().equals(constantDelivery.ADMIN_ROLE))
				return "/admin/";
			if (role.getName().equals(constantDelivery.STUDENT_ROLE))
				return "/student/";
		}
		return "";
	}*/
	
}

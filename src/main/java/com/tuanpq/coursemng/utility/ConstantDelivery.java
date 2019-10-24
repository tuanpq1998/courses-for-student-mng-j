/**
 * @author admin
 * @date 04-10-2019
 */

package com.tuanpq.coursemng.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstantDelivery {

	@Value("${const.role.admin.withprefix}")
	public String ADMIN_ROLE;
	
	@Value("${const.role.student.withprefix}")
	public String STUDENT_ROLE;
	
	@Value("${const.role.admin}")
	public String ADMIN;
	
	@Value("${const.role.student}")
	public String STUDENT;
	
}

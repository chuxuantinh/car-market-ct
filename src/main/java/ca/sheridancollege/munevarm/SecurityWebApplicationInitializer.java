package ca.sheridancollege.munevarm;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.munevarm.security.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	
	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}
}

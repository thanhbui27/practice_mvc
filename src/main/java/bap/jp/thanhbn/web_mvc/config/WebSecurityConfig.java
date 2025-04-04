package bap.jp.thanhbn.web_mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import bap.jp.thanhbn.web_mvc.enums.Role;
import bap.jp.thanhbn.web_mvc.service.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
		
		private final CustomUserDetailsService userDetailsService;
	
	    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); 
	    }
	    
	    @Bean
	   	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())  
	                .requestMatchers("/order").hasAnyRole(Role.USER.name(), Role.ADMIN.name()) 
	                .anyRequest().permitAll()
	            )
	            .formLogin(form -> form.loginPage("/login").permitAll())	           
	            .logout()
	            .and()
	            .csrf().disable(); 
	        
	        http.userDetailsService(userDetailsService);  

	        return http.build();
	    }
}

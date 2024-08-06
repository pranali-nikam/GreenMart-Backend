//package com.greenify.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.greenify.entities.User;
//
//
//@Configuration
//public class SecurityConfig{
//
//@Bean
//
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	http
//	    .csrf().disable()
//	    .authorizeRequests()
//	          .antMatchers("/login","/resouces/**").permitAll()
//	          .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//	          .antMatchers("/seller/**").hasAuthority("ROLE_SELLER")
//	          .antMatchers("/user/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE_SELLER")
//	          .anyRequest().authenticated()
//	          .and()
//	     .formLogin()
//	          .loginPage("/login")
//	          .defaultSuccessUrl("/home",true)
//	          .permitAll()
//	          .and()
//	      .logout()
//	           .permitAll();
//	    
//	      return http.build();
//	      
//     }
//@Bean
//public BCryptPasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();
//	}
////@Bean
////public UserDetailsService userDetailsService() {
////	InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
////	userDetailsService.createUser(((Object) User.withUsername("admin"))
////			.password(passwordEncoder().encode("admin"))
////			.authorities("ROLE_ADMIN")
////			.build());
////	userDetailsService.createUser(((Object) User.withUsername("seller"))
////			.password(passwordEncoder().encode("seller"))
////			.authorities("ROLE_SELLER")
////			.build());
////	userDetailsService.createUser(((Object) User.withUsername("user"))
////			.password(passwordEncoder().encode("user"))
////			.authorities("ROLE_USER")
////			.build());
////	return userDetailsService;
//	
////}
//}

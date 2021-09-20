package com.school.studentinfosystem.security;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		csrf().disable()
        .cors()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .antMatchers("/student/**").hasAuthority("ADMIN")
        .antMatchers("/course/**").hasAuthority("ADMIN")
        .antMatchers("/login").hasAuthority("ADMIN")
        .antMatchers("/studentmgmt/**").hasAuthority("USER")
        .antMatchers("/studlogin").hasAuthority("USER")
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .logout()
        .logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");
	}
	
	@Autowired
	public void configAuthenticated(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(dataSource);
		auth.jdbcAuthentication().dataSource(dataSource)
			.authoritiesByUsernameQuery("select userid, role FROM student where userid=?")
			.usersByUsernameQuery("select userid, password, 1 FROM student where userid=?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

package com.example.server.config.security;

import com.example.server.config.security.component.JwtAuthencationTokenFilter;
import com.example.server.config.security.component.RestAuthorizationEntryPoint;
import com.example.server.config.security.component.RestfulAccessDeniedHandler;
import com.example.server.entity.User;
import com.example.server.service.IRoleService;
import com.example.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IUserService userService;
	@Autowired
	private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	@Autowired
	private IRoleService roleService;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/login",
				"/logout",
				"/css/**",
				"/js/**",
				"/index.html",
				"favicon.ico",
				"/doc.html",
				"/webjars/**",
				"/swagger-resources/**",
				"/v2/api-docs/**",
				"/captcha",
				"/ws/**",
				"/register/**"

		);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//使用JWT，不需要csrf
		http.csrf()
				.disable()
				//基于token，不需要session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				//所有请求都要求认证
				.anyRequest()
				.authenticated()
				.and()
				//禁用缓存
				.headers()
				.cacheControl();
		//添加jwt 登录授权过滤器
		http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		//添加自定义未授权和未登录结果返回
		http.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthorizationEntryPoint);
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService(){
		return username -> {
			User user = userService.getUserByUsername(username);
			if (null!=user){
				user.setRole(roleService.getRoles(user.getId()));
				return user;
			}
			throw new UsernameNotFoundException("用户名或密码不正确");
		};
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
		return new JwtAuthencationTokenFilter();
	}

}
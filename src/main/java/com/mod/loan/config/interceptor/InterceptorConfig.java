package com.mod.loan.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lijy on 2017/12/21 0021.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		// registry.addInterceptor(new ConstInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**", "/system/login*", "/system/error*");
		super.addInterceptors(registry);
	}
}

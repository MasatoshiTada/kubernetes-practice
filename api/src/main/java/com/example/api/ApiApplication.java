package com.example.api;

import com.example.api.service.CustomerService;
import com.example.api.web.filter.LoggingFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean loggingFilter() {
        LoggingFilter loggingFilter = new LoggingFilter();
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>(loggingFilter);
        // フィルターの順番を一番最初に指定
        registrationBean.setOrder(Integer.MIN_VALUE);
        // url-patternを指定
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public CommandLineRunner showEnv(DataSourceProperties dataSourceProperties, CustomerService customerService) {
        return args -> {
            System.out.println("■" + dataSourceProperties.getUrl());
            customerService.findAll().forEach(System.out::println);
        };
    }
}

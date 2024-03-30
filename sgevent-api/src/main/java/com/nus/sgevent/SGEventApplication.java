package com.nus.sgevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SGEventApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SGEventApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
    SpringApplicationBuilder builder
  ) {
    return builder.sources(SGEventApplication.class);
  }
}

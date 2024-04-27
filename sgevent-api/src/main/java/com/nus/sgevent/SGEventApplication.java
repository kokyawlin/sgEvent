package com.nus.sgevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SGEventApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {

    if (args.length > 0) {
      System.setProperty("db.username", args[0]);
      System.setProperty("db.password", args[1]);
      System.setProperty("mail.username", args[2]);
      System.setProperty("mail.password", args[3]);
    }

    SpringApplication.run(SGEventApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
    SpringApplicationBuilder builder  ) {
    return builder.sources(SGEventApplication.class);
  }
}

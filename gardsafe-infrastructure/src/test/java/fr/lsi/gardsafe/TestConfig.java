package fr.lsi.gardsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootConfiguration
@ComponentScan(basePackages = {"fr.lsi.gardsafe"}, lazyInit = true)
@EnableJpaRepositories
public class TestConfig {

  public static void mail(final String[] args) {
    SpringApplication.run(TestConfig.class, args);
  }

  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper();
  }

}

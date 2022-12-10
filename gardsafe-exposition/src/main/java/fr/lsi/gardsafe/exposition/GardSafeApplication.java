package fr.lsi.gardsafe.exposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class pour le lancement Spring boot
 * 
 * @author Laurent SION
 *
 */
@Configuration
@ComponentScan("fr.lsi.gardsafe")
@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@EntityScan("fr.lsi.gardsafe")
public class GardSafeApplication {

  /**
   * Fonction main
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(GardSafeApplication.class, args);
  }

}

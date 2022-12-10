package fr.lsi.gardsafe.exposition;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class pour le lancement Spring boot
 * 
 * @author Laurent SION
 *
 */
@Configuration
@ComponentScan("fr.lsi.gardsafe")
@SpringBootApplication // (exclude = {MultipartAutoConfiguration.class,
                       // JmxAutoConfiguration.class,})
// @EnableAutoConfiguration // (exclude = {MultipartAutoConfiguration.class,
// JmxAutoConfiguration.class,})
@EnableJpaRepositories("fr.lsi.gardsafe.infrastructure")
@EntityScan("fr.lsi.gardsafe")
public class GardSafeApplication {

  private static final Logger LOG = LoggerFactory.getLogger(GardSafeApplication.class);

  private static final String HTTP_DEFAULT_PORT = "8080";

  /**
   * Application main method
   * 
   * @param args main arguments
   * @throws UnknowHostException
   */
  public static void main(final String[] args) throws UnknownHostException {
    final Environment env = SpringApplication.run(GardSafeApplication.class, args).getEnvironment();
    logApplicationStartup(env);
  }

  /**
   * Log application name, version, urls and used profiles
   * 
   * @param env the spring environment object
   * @throws UnknownHostException
   */
  private static void logApplicationStartup(final Environment env) throws UnknownHostException {
    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }
    final String serverPort =
        Optional.ofNullable(env.getProperty("server.port")).orElse(HTTP_DEFAULT_PORT);
    String contextPath = env.getProperty("server.servlet.context-path");
    if (StringUtils.isEmpty(contextPath)) {
      contextPath = "/";
    }
    final String hostAddress = InetAddress.getLocalHost().getHostAddress();

    LOG.info("\n----------------------------------------------------------------------------\n\t" //
        + "Application '{} ({})' is running!\n\tAccess URLs:\n\t" //
        + "Local: \t\t{}://localhost:{}{}\n\t" //
        + "External: \t{}://{}:{}{}\n\t" //
        + "Profile(s): \t{}\n----------------------------------------------------------------------------", //
        env.getProperty("spring.application.name"), env.getProperty("application.version"),
        protocol, serverPort, contextPath, protocol, hostAddress, serverPort, contextPath,
        env.getActiveProfiles());

  }
}

package org.tricol.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tricol.config.JpaConfig;
import org.tricol.config.WebConfig;

@Configuration
@ComponentScan(basePackages = "org.tricol")
@Import({JpaConfig.class, WebConfig.class})
public class AppConfig {
}
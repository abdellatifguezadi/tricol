package org.tricol.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages = "org.tricol")
@EnableWebMvc
@Import(JpaConfig.class)
public class AppConfig {
}
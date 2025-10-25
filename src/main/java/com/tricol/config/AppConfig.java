package com.tricol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tricol")
public class AppConfig {
    // Removed manual bean creation for FournisseurService so that the
    // @Service-annotated FournisseurService can be injected by Spring.
}
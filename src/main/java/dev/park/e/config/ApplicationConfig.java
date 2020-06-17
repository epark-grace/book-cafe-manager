package dev.park.e.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"dev.park.e.service", "dev.park.e.dao"})
public class ApplicationConfig {
}

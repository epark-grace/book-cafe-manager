package dev.park.e.bookcafemanager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"dev.park.e.bookcafemanager.service", "dev.park.e.bookcafemanager.mapper"})
public class ApplicationConfig {
}

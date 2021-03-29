package dev.park.e.bookcafemanager.config;

import dev.park.e.bookcafemanager.properties.SeojiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SeojiProperties.class)
public class PropertiesConfiguration {
}

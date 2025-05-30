package us.zep.chatserver.common.config.app;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = "us.zep.chatserver")
public class PropertiesConfiguration {
}

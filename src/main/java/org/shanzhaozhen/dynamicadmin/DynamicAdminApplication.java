package org.shanzhaozhen.dynamicadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DynamicAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicAdminApplication.class, args);
    }

}

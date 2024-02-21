package com.cybereason.servicedemo.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "demo")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoProperties {
    private Ansible asnible;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class Ansible {
        private String ip;
        private String port;
        private String username;
        private String password;
    }

}

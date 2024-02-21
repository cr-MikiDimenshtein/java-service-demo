package com.cybereason.servicedemo.config;

import com.cybereason.servicedemo.properties.DemoProperties;
import com.github.woostju.ansible.AnsibleClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public AnsibleClient ansibleClient(DemoProperties demoProperties) {
        return new AnsibleClient();
    }
}

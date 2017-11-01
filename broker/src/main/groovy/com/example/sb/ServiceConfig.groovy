package com.example.sb

import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@CompileStatic
@Configuration
@ConfigurationProperties(prefix = "com.example.sb.config")
class ServiceConfig implements com.swisscom.cloud.sb.broker.config.Config{
    String value1
    int value2
}

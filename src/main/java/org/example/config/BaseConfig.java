package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface BaseConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("page.load.timeout")
    int pageLoadTimeout();

    @Key("wait.timeout.ms")
    int waitTimeout();

}

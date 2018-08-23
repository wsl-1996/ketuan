package com.skqtec.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperty {

    @Value("${host}")
    private String host;

    public String getHost(){
        return host;
    }
}

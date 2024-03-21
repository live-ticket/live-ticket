package com.ll.ticket.global.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${custom.file-path}")
    String filePath;

    @Value("${custom.file-upload}")
    private String fileUpload;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUpload)
                .addResourceLocations("file:///" + filePath);
    }


}

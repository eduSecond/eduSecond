package net.fullstack7.edusecond.edusecond.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///home/gyeongmini/uploads/")
                .setCachePeriod(3600);
                
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
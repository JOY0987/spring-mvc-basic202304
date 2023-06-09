package com.spring.mvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 로컬 스토리지에 저장된 파일을 백엔드서버에서
// 제공할 수 있도록 설정

@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {

    @Value("${file.upload.root-path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // upload/ 밑에 있는 파일은 /local/ 로 접근하겠다.
        registry.addResourceHandler("/local/**")
                .addResourceLocations("file:/" + rootPath);
    }
}

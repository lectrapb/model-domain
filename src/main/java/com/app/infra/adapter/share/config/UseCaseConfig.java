package com.app.infra.adapter.share.config;

import com.app.domain.share.labels.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


;

@Configuration
@ComponentScan(basePackages = "com.app",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = UseCase.class),
        useDefaultFilters = false)
public class UseCaseConfig {
}


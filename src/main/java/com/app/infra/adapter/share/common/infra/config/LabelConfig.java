package com.app.infra.adapter.share.common.infra.config;

import com.app.domain.share.bus.command.gateway.label.CommandHandlerService;
import com.app.domain.share.common.gateway.labels.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


;

@Configuration
@ComponentScan(basePackages = "com.app",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {UseCase.class, CommandHandlerService.class}),
        useDefaultFilters = false)
public class LabelConfig {
}


package com.alpha.bo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringMetricsConfig.class)
@ComponentScan(basePackages = "com.alpha.bo")
public class SpringConfig {
}

package com.alpha.bo;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics
public class SpringMetricsConfig extends MetricsConfigurerAdapter {

    CsvReporter reporter;

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        // registerReporter allows the MetricsConfigurerAdapter to
        // shut down the reporter when the Spring context is closed
        String metricsDir = "target";
        reporter = CsvReporter.forRegistry(metricRegistry).formatFor(Locale.UK).convertDurationsTo(TimeUnit.MILLISECONDS).convertRatesTo(TimeUnit.SECONDS)
                .build(new File(metricsDir));
        registerReporter(reporter);
        reporter.start(2, TimeUnit.SECONDS);
    }

    @Bean
    public Reporter reporter(){
        return reporter;
    }
}
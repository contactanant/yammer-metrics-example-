package com.alpha.bo;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reporter;
import com.google.inject.AbstractModule;
import com.palominolabs.metrics.guice.MetricsInstrumentationModule;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GuiceConfig extends AbstractModule {

    CsvReporter reporter;

    @Override
    protected void configure() {
        final MetricRegistry metrics = new MetricRegistry();
        String metricsDir = "target";
        reporter = CsvReporter.forRegistry(metrics).formatFor(Locale.UK).convertDurationsTo(TimeUnit.MILLISECONDS).convertRatesTo(TimeUnit.SECONDS)
                .build(new File(metricsDir));
//        startReport(reporter);
        bind(Reporter.class).toInstance(reporter);
        install(new MetricsInstrumentationModule(metrics));
    }

    void startReport(CsvReporter reporter) {
        reporter.start(2, TimeUnit.SECONDS);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

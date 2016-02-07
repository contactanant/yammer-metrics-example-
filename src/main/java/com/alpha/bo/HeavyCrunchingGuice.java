package com.alpha.bo;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.Reporter;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.inject.Inject;
import java.io.IOException;

public class HeavyCrunchingGuice {

    Reporter reporter;

    @Inject
    public HeavyCrunchingGuice(Reporter reporter) {
        this.reporter = reporter;
    }

    public static void main(String args[]) throws IOException {
        Injector injector = Guice.createInjector(new GuiceConfig());
        HeavyCrunchingGuice heavyCrunchingGuice = injector.getInstance(HeavyCrunchingGuice.class);

        for (int i = 0; i < 2; i++) {
            heavyCrunchingGuice.doProcessing();
        }
        ((CsvReporter) heavyCrunchingGuice.reporter).report();
        ((CsvReporter) heavyCrunchingGuice.reporter).close();
        System.out.println("stop reporter");
    }


    @Timed
    void doProcessing() {
        try {
            System.out.println("Processing started");
            Thread.sleep(2*100);
            doInnerProcessing();
        }
        catch(InterruptedException e) {}
    }


    @Timed
    void doInnerProcessing() {
        try {
            System.out.println("Inner processing started");
            Thread.sleep(1*100);
        }
        catch(InterruptedException e) {}
    }
}

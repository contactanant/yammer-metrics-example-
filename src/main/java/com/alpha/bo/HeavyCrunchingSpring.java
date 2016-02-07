package com.alpha.bo;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.annotation.Timed;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeavyCrunchingSpring {

    public static void main(String args[]) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        HeavyCrunchingSpring heavyCrunching = (HeavyCrunchingSpring) context.getBean("heavyCrunchingSpring");

        for (int i = 0; i < 2; i++) {
            heavyCrunching.doProcessing();
        }
        CsvReporter reporter = (CsvReporter) context.getBean("reporter");
        reporter.report();
        reporter.close();
        System.out.println("stop reporter");
    }


    @Timed
    public void doProcessing() {
        try {
            System.out.println("Processing started");
            Thread.sleep(2*100);
            doInnerProcessing();
        }
        catch(InterruptedException e) {}
    }


    @Timed
    public void doInnerProcessing() {
        try {
            System.out.println("Inner processing started");
            Thread.sleep(1*100);
        }
        catch(InterruptedException e) {}
    }
}

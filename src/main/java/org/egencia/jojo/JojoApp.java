package org.egencia.jojo;

import org.egencia.jojo.configuration.JojoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Simple slack Bot using Ullink lib:  https://github.com/Ullink/simple-slack-api
 *
 */
public class JojoApp {
    private static final Logger log = LoggerFactory.getLogger(JojoApp.class);
    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(JojoConfiguration.class);

        while (true) {
            Thread.sleep(1000);
        }
    }
}

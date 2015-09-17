package org.egencia.jojo.configuration;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "org.egencia.jojo")
@PropertySource("classpath:/jojo.properties")
@EnableScheduling
public class Jojoconfiguration {

    @Value("${slackToken}")
    private String slackToken;

    @Bean
    public SlackSession slackSession() throws IOException {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(slackToken);
        session.connect();
        return session;

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

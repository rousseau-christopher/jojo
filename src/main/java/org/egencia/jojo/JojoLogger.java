package org.egencia.jojo;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackEvent;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackEventListener;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Component
public class JojoLogger {
    private static final Logger log = LoggerFactory.getLogger(JojoLogger.class);

    private final SlackSession slackSession;

    @Inject
    public JojoLogger(SlackSession slackSession) {
        this.slackSession = slackSession;
    }

    @PostConstruct
    public void startLogging() {
        slackSession.addMessagePostedListener(
                (e, s) -> logMessage(e)
        );
    }

    public void logMessage(SlackMessagePosted messagePosted) {
        log.info(messagePosted.toString());
    }
}

package org.egencia.jojo;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Component
public class MessageLogger {
    private static final Logger log = LoggerFactory.getLogger(MessageLogger.class);

    private final SlackSession slackSession;

    @Inject
    public MessageLogger(SlackSession slackSession) {
        this.slackSession = slackSession;
    }

    @PostConstruct
    public void startLogging() {
        slackSession.addMessagePostedListener(
                (e, s) -> logMessage(e)
        );
    }

    private void logMessage(SlackMessagePosted messagePosted) {
        log.info(messagePosted.toString());

    }
}

package org.egencia.jojo;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ScheduledMessages {
    private static final Logger log = LoggerFactory.getLogger(ScheduledMessages.class);

    private final SlackSession slackSession;

    @Inject
    public ScheduledMessages(SlackSession slackSession) {
        this.slackSession = slackSession;
    }

    //@Scheduled(fixedRate = 10000)
    @Scheduled(cron="0 30 9 * * MON-FRI")
    public void callForStandUp() {
        SlackChannel channel = slackSession.findChannelByName("general");
        slackSession.sendMessage(channel,"StandUp",null);
        log.info(channel.toString());
    }
}

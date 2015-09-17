package org.egencia.jojo;

import com.ullink.slack.simpleslackapi.SlackSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JojoStandUp {
    private static final Logger log = LoggerFactory.getLogger(JojoStandUp.class);

    private final SlackSession slackSession;

    @Inject
    public JojoStandUp(SlackSession slackSession) {
        this.slackSession = slackSession;
    }

    //@Scheduled(fixedRate = 5000)
    //@Scheduled(cron="0 30 9 * * MON-FRI")
    public void callForStandUp() {
        log.info("every 5 sec");
    }
}

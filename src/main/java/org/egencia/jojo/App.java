package org.egencia.jojo;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackEvent;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession("xoxb-10819105254-Ri0hy12wtzJD2uxWINpcnn1X");
        session.addMessagePostedListener(
                (e, s) -> {
                    logMessage(e);
                }
        );

        session.connect();
        while (true) {
            Thread.sleep(1000);
        }
    }

    private static void logMessage(SlackMessagePosted messagePosted) {
        log.info(messagePosted.toString());
    }
}

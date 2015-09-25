package org.egencia.jojo;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Component
public class CommitStrip {
    private static final Logger log = LoggerFactory.getLogger(CommitStrip.class);

    private final SlackSession slackSession;

    private final URL rssUrl;

    @Inject
    public CommitStrip(SlackSession slackSession) throws MalformedURLException {
        this.slackSession = slackSession;
        rssUrl = new URL("http://www.commitstrip.com/en/feed/");
    }

    //@Scheduled(fixedRate = 5000)
    public void check() throws URISyntaxException, IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
        SyndFeed feed = input.build(in);
        List<SyndEntry> entries = feed.getEntries();
        entries.stream()
                .forEach(syndEntry -> log.info(syndEntry.toString()));
    }
}

package org.egencia.jojo;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommitStrip {
    private static final Logger log = LoggerFactory.getLogger(CommitStrip.class);

    private final SlackSession slackSession;

    private final URL rssUrl;

    @Inject
    public CommitStrip(SlackSession slackSession) throws MalformedURLException {
        this.slackSession = slackSession;
        rssUrl = new URL("http://www.commitstrip.com/fr/feed/");
    }

    @Scheduled(cron="0 0 9 * * MON-FRI")
    public void check() throws URISyntaxException, IOException, FeedException {
        List<SyndEntry> entries = getSyndEntries();
        SyndEntry syndEntry = entries.get(0);

        SlackChannel channel = slackSession.findChannelByName("divertissement");
        SyndContentImpl content = (SyndContentImpl)syndEntry.getContents().get(0);
        slackSession.sendMessage(channel, getValue(content.getValue()), null);
        log.info(syndEntry.getUri());
    }

    private List<SyndEntry> getSyndEntries() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
        SyndFeed feed = input.build(in);
        return feed.getEntries();
    }

    public String getValue(String rawData) {
        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(rawData);
        String image = null;
        if (matcher.find()) {
            image = matcher.group(1);
        }
        return image;
    }
}

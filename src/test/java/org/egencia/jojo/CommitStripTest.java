package org.egencia.jojo;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by wjouve on 25/09/2015.
 */
public class CommitStripTest  {

    @Test
    public void should_get_image_url () throws Exception {
        String rawData = "<p><img class=\"aligncenter size-full wp-image-13351\" src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg\" alt=\"\" width=\"650\" height=\"915\" /></p>";
        CommitStrip commitStrip = new CommitStrip(null);

        String result  = commitStrip.getValue(rawData);

        Assertions.assertThat(result).isEqualTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg");
    }

    @Test
    public void should_get_image_url_with_accent() throws Exception {

        String rawData = "[<p><img class=\"aligncenter size-full wp-image-13359\" src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Enfance-du-codeur-Jouer-à-la-console-avec-un-copain-650-final.jpg\" alt=\"\" width=\"650\" height=\"915\" /></p>";
        CommitStrip commitStrip = new CommitStrip(null);

        String result  = commitStrip.getValue(rawData);

        Assertions.assertThat(result).isEqualTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Enfance-du-codeur-Jouer-%C3%A0-la-console-avec-un-copain-650-final.jpg");
    }

}
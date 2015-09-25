package org.egencia.jojo;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * Created by wjouve on 25/09/2015.
 */
public class CommitStripTest  {

    @Test
    public void should_get_image_url () throws MalformedURLException {
        String rawData = "<p><img class=\"aligncenter size-full wp-image-13351\" src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg\" alt=\"\" width=\"650\" height=\"915\" /></p>";
        CommitStrip commitStrip = new CommitStrip(null);

        String result  = commitStrip.getValue(rawData);

        Assertions.assertThat(result).isEqualTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg");
    }

}
package org.egencia.jojo;

import org.hamcrest.Matchers;
import org.junit.Assert;
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

        Assert.assertThat(result, Matchers.equalTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg"));
    }

}
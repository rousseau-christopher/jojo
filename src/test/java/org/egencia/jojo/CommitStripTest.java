package org.egencia.jojo;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Created by wjouve on 25/09/2015.
 */
public class CommitStripTest  {

    private CommitStrip commitStrip;

    public CommitStripTest() throws MalformedURLException {
        commitStrip = new CommitStrip(null);
    }

    @Test
    public void should_get_image_url () throws Exception {
        String rawData = "<p><img class=\"aligncenter size-full wp-image-13351\" src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg\" alt=\"\" width=\"650\" height=\"915\" /></p>";

        String result  = commitStrip.getValue(rawData);

        Assertions.assertThat(result).isEqualTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Le-gars-qui-parle-trop-650-final1.jpg");
    }

    @Test
    public void should_get_image_url_with_accent() throws Exception {

        String rawData = "[<p><img class=\"aligncenter size-full wp-image-13359\" src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Enfance-du-codeur-Jouer-à-la-console-avec-un-copain-650-final.jpg\" alt=\"\" width=\"650\" height=\"915\" /></p>";

        String result  = commitStrip.getValue(rawData);

        Assertions.assertThat(result).isEqualTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Enfance-du-codeur-Jouer-%C3%A0-la-console-avec-un-copain-650-final.jpg");
    }

    @Test
    public void should_get_the_proper_image() throws UnsupportedEncodingException {
        // Given
        String rawData = "<div style=\"max-width: 650px; margin: 0 auto; padding-top: 20px; padding-bottom: 20px\">Envie de nouveaux horizons en cette rentrée ? <strong style=\"font-weigh: bold;\"><a href=\"http://bit.ly/1qGGtAM\" target=\"_blank\">ChooseYourBoss</a></strong>, notre parrain du jour, fête ses 3 ans de mise en relations entre codeurs et boites IT. Alors <strong style=\"font-weigh: bold;\"><a href=\"http://bit.ly/1qGGtAM\" target=\"_blank\">n&rsquo;hésitez pas à faire un tour</a></strong> si vous êtes en quête de nouveaux projets <img src=\"http://s.w.org/images/core/emoji/72x72/1f609.png\" alt=\"\uD83D\uDE09\" class=\"wp-smiley\" style=\"height: 1em; max-height: 1em;\" /></p>\n" +
                            "<p> Note : Vu le thème du jour, la strip est en anglais pour tout le monde !</p></div>\n" +
                            "<p><img class=\"aligncenter size-full wp-image-13399\" src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Bohemain-Rhapsody-CYB-650-final1.jpg\" alt=\"\" width=\"650\" height=\"898\" /><br />\n" +
                            "<a href=\"http://bit.ly/1qGGtAM\" target=\"_blank\"><img class=\"aligncenter size-full wp-image-6255\" title=\"Choose Your Boss\" src=\"http://www.commitstrip.com/wp-content/uploads/2013/09/cyb1.png\" alt=\"\" width=\"600\" height=\"54\" /></a></p>\n" +
                            "<style>\n" +
                            ".csplayer{\n" +
                            "text-align:center;\n" +
                            "}\n" +
                            ".csplayer object{\n" +
                            "display:inline-block;\n" +
                            "}\n" +
                            "</style>\n" +
                            "<div class=\"csplayer\">\n" +
                            "Queen &#8211; Bohemian Rhapsody</p>\n" +
                            "<p><audio controls=\"controls\">Votre navigateur ne supporte pas l&rsquo;élément <code>audio</code> element.<source src=\"http://www.commitstrip.com/wp-content/uploads/2015/09/Bohemian-Rhapsody.mp3\" type=\"audio/mp3\"></audio>\n" +
                            "</div>\n";
        // When
        String result  = commitStrip.getValue(rawData);

        // Then
        Assertions.assertThat(result).isEqualTo("http://www.commitstrip.com/wp-content/uploads/2015/09/Strip-Bohemain-Rhapsody-CYB-650-final1.jpg");
    }

}
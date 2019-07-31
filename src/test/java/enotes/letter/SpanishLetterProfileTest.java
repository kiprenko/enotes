package enotes.letter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spanish-spring-configuration.xml"})
public class SpanishLetterProfileTest
{
    @Autowired
    private Letter spanishLetter;

    private final String SPANISH_LETTER_TEXT = "Texto super secreto de una carta";

    @Test
    public void shouldReturnSpanishLetterText() {
        String text = spanishLetter.getText();
        Assert.assertEquals(SPANISH_LETTER_TEXT, spanishLetter.getText());
    }
}

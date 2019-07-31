package enotes.letter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spanish-spring-configuration.xml",
        "classpath:/spring/letter-spring-configuration.xml"})
public class SpanishLetterProfileTest {

    @Autowired
    private Letter spanishLetter;

    @Test
    public void shouldReturnSpanishLetterText() {
        String SPANISH_LETTER_TEXT = "Texto super secreto de una carta";
        assertEquals(SPANISH_LETTER_TEXT, spanishLetter.getText());
    }
}

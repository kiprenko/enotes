package enotes.letter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/english-spring-configuration.xml")
public class EnglishLetterProfileTest {
    private Letter englishLetter;
    private final String ENGLISH_LETTER_TEXT = "Super secret text of a letter";

    @Test
    public void shouldReturnEnglishLetterText() {
        String SPANISH_LETTER_TEXT = "Texto super secreto de una carta";
        assertEquals(SPANISH_LETTER_TEXT, englishLetter.getText());
    }
}

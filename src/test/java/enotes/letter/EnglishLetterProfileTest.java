package enotes.letter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/english-spring-configuration.xml",
        "classpath:/spring/letter-spring-configuration.xml"})
public class EnglishLetterProfileTest {

    @Autowired
    private Letter englishLetter;

    @Test
    public void shouldReturnEnglishLetterText() {
        String ENGLISH_LETTER_TEXT = "Super secret text of a letter";
        assertEquals(ENGLISH_LETTER_TEXT, englishLetter.getText());
    }
}

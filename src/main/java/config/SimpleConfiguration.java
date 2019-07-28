package config;

import enotes.letter.Letter;
import enotes.letter.LetterImpl;
import enotes.letter.SpanishLetter;
import enotes.postman.Postman;
import enotes.postman.PostmanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SimpleConfiguration {

    @Bean
    @Profile({"default", "english"})
    public Letter letterImpl() {
        return new LetterImpl();
    }

    @Bean
    @Profile("spanish")
    public Letter spanishLetter() {
        return new SpanishLetter();
    }

    @Bean
    public Postman postman(Letter letter) {
        return new PostmanImpl(letter);
    }
}

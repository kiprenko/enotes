package config;

import enotes.Letter;
import enotes.LetterImpl;
import enotes.SpanishLetter;
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
}

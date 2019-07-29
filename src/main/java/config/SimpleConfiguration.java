package config;

import enotes.letter.Letter;
import enotes.letter.LetterImpl;
import enotes.letter.SpanishLetter;
import enotes.postman.Postman;
import enotes.postman.PostmanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("spring/application.properties")
public class SimpleConfiguration {

    @Bean
    @Profile("english")
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
        PostmanImpl postman = new PostmanImpl();
        postman.setLetter(letter);
        return postman;
    }
}

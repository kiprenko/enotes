package enotes;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("spanish")
public class SpanishLetter implements Letter {

    @Override
    public String getText() {
        return "Hola amigo!";
    }

    @Override
    public void setText(String text) {

    }
}

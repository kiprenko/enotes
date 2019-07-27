package enotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Postman {

    private final Letter letter;

    public Postman(Letter letter) {
        this.letter = letter;
    }

    public void readLetter() {
        System.out.println(letter.getText());
    }
}

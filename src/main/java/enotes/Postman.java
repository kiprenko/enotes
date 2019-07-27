package enotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Postman {

    private Letter letter;

    @Autowired
    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public void readLetter() {
        System.out.println(letter.getText());
    }
}

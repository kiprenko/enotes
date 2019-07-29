package enotes.postman;

import enotes.letter.Letter;

public class PostmanImpl implements Postman {

    private Letter letter;

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    @Override
    public void readLetter() {
        System.out.println(letter.getText());
    }
}

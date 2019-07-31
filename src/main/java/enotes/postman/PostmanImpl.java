package enotes.postman;

import enotes.letter.Letter;

public class PostmanImpl implements Postman {

    private Letter defaultLetter;
    private Letter ukrainianLetter;
    private Letter frenchLetter;

    public void setUkrainianLetter(Letter ukrainianLetter)
    {
        this.ukrainianLetter = ukrainianLetter;
    }

    public void setFrenchLetter(Letter frenchLetter)
    {
        this.frenchLetter = frenchLetter;
    }

    public void setDefaultLetter(Letter defaultLetter) {
        this.defaultLetter = defaultLetter;
    }

    @Override
    public void readLetters() {
        System.out.println(defaultLetter.getText());
        System.out.println("=================");
        System.out.println(ukrainianLetter.getText());
        System.out.println("=================");
        System.out.println(frenchLetter.getText());
    }
}

package enotes.letter;

public class EnglishLetter implements Letter {

    private String text = "Super secret text of a letter";

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
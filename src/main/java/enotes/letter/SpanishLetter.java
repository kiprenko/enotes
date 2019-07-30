package enotes.letter;

public class SpanishLetter implements Letter {

    private String text = "Texto super secreto de una carta";

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}

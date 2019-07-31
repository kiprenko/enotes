package enotes.letter;

public class UkrainianLetter implements Letter {

    private String text = "Думи мої, думи мої,\nЛихо мені з вами!";

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}

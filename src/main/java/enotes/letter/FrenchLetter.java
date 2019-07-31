package enotes.letter;

public class FrenchLetter implements Letter
{

    private String text = "Demain, dès l’aube, à l’heure où blanchit la campagne,\nJe partirai. Vois-tu, je sais que tu m’attends.";

    @Override
    public String getText()
    {
        return text;
    }

    @Override
    public void setText(String text)
    {
        this.text = text;
    }
}

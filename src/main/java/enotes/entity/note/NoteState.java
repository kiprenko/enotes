package enotes.entity.note;

public enum NoteState {
    HIGH("High"),
    MIDDLE("Middle"),
    LOW("Low");

    private String state;
    NoteState(String state) {
        this.state = state;
    }
}

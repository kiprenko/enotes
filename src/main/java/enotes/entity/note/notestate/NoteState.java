package enotes.entity.note.notestate;

public enum NoteState {
    HIGH("High"),
    MIDDLE("Middle"),
    LOW("Low");

    private String stateAsString;

    NoteState(String state) {
        this.stateAsString = state;
    }

    public String getStateAsString() {
        return stateAsString;
    }

    public static NoteState getByStringName(String state) {
        state = state.toLowerCase();

        if (state.equals("high")) {
            return HIGH;
        } else if (state.equals("middle")) {
            return MIDDLE;
        } else if (state.equals("low")){
            return LOW;
        } else {
            throw new IllegalArgumentException("No such element in NoteState enum!");
        }
    }
}

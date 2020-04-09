package enotes.data.note;

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

        switch (state) {
            case "high":
                return HIGH;
            case "middle":
                return MIDDLE;
            case "low":
                return LOW;
            default:
                throw new IllegalArgumentException("No such element in NoteState enum!");
        }
    }
}

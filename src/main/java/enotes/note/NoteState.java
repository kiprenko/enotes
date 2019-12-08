package com.enotes.note;

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
        if (state.equals("High")) {
            return HIGH;
        } else if (state.equals("Middle")) {
            return MIDDLE;
        } else {
            return LOW;
        }
    }
}

package com.enotes.note;

public enum NoteState {
    HIGH("High"),
    MIDDLE("Middle"),
    LOW("Low");

    private String state;

    NoteState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

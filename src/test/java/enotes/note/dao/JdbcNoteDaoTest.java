package enotes.note.dao;

import enotes.note.Note;
import enotes.note.noteState.NoteState;
import enotes.user.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class JdbcNoteDaoTest {

    @Autowired
    private NoteDao noteDao;

    private static Note note;
    private static User user;
    private static final long USER_ID = 1L;
    private static final String NOTE_BODY = "A simple note body.";
    private static final String NOTE_HEADER = "A simple note header.";
    private static final NoteState NOTE_STATE = NoteState.MIDDLE;

    @Before
    void setUp() {
//        user = User.builder().id();
//        note = Note.builder().body(NOTE_BODY).header(NOTE_HEADER).state(NOTE_STATE).build();
    }

    @Test
    void findAll() {
    }

    @Test
    void find() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
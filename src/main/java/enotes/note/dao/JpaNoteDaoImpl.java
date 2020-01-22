package enotes.note.dao;

import java.util.List;

import enotes.note.Note;

public class JpaNoteDaoImpl implements NoteDao {

    @Override
    public List<Note> findAll() {
        return null;
    }

    @Override
    public Note find(Long id) {
        return null;
    }

    @Override
    public boolean add(Note entity)
    {
        return false;
    }

    @Override
    public boolean update(Note entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

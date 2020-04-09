package enotes.data.note;

import enotes.data.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAllByUser(User user);

    List<Note> findAllByUserAndIsArchivedOrderByCreatedDesc(User user, boolean isArchived);

    List<Note> findAllByUserAndIsArchivedAndIsDoneOrderByCreatedDesc(User user, boolean isArchived, boolean isDone);

    List<Note> findAllByUserAndIsArchivedAndCreated(User user, boolean isArchived, LocalDate created);
}

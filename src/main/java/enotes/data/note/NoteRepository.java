package enotes.data.note;

import enotes.data.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAllByUser(User user);

    List<Note> findAllByUserAndIsArchived(User user, boolean isArchived);

}

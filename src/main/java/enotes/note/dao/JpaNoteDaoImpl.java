package enotes.note.dao;

import enotes.note.Note;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Component
@Profile("jpa")
public class JpaNoteDaoImpl implements NoteDao {

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Note> findAll() {
        return getEntityManager().createQuery("FROM notes", Note.class).getResultList();
    }

    @Override
    public Note find(Long id) {
        return getEntityManager().find(Note.class, id);
    }

    @Override
    public void add(Note entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Note entity) {

    }

    @Override
    public void delete(Long id) {
    }
}

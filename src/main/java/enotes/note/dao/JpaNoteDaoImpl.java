package enotes.note.dao;

import enotes.note.Note;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

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
    public Optional<Note> find(Long id) {
        return Optional.ofNullable(getEntityManager().find(Note.class, id));
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
        getEntityManager().merge(entity);
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Note.class, id));
        entityManager.getTransaction().commit();
    }
}

package enotes.entity.comment.dao;

import enotes.entity.comment.Comment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Component
public class JpaCommentDaoImpl implements CommentDao {

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Comment> findAll() {
        return getEntityManager().createQuery("FROM Comment", Comment.class).getResultList();
    }

    @Override
    public Optional<Comment> find(Long id) {
        return Optional.ofNullable(getEntityManager().find(Comment.class, id));
    }

    @Override
    public void add(Comment entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Comment entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Comment.class, id));
        entityManager.getTransaction().commit();
    }
}

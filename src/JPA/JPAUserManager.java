package JPA;

import bioLabPOJOS.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;

public class JPAUserManager {

    public void createUser(User user) {
        // Llamamos a la utilidad y creamos el manager
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public User getUserById(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public List<User> getAllUsers() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM User u");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}

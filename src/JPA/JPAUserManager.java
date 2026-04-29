package JPA;
import javax.persistence.*;
import bioLabPOJOS.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class JPAUserManager {

    public void createUser(User user) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(user);
            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

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

    public User findUserByUsername(String username) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username",
                    User.class);

            query.setParameter("username", username);

            List<User> users = query.getResultList();

            if (users.isEmpty()) {
                return null;
            }

            return users.get(0);

        } finally {
            em.close();
        }
    }

    public boolean login(String username, String password) {

        User user = findUserByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}
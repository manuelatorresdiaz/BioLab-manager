package JPA;

import interfaces.UserManager;
import javax.persistence.*;
import bioLabPOJOS.User;

import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * JPA Implementation for User Authentication and Security.
 * This class handles the persistence of user credentials using ORM 
 * and ensures passwords are never stored in plain text.
 */
public class JPAUserManager implements UserManager {

    public void createUser(User user) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
         // Security: Hash the password before it ever hits the database
            String hashedPassword = encryptPassword(user.getPassword());
            user.setPassword(hashedPassword);
            
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
        	// Using JPQL (Java Persistence Query Language) for flexible searching
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
        String hashedInput = encryptPassword(password);
        return user.getPassword().equals(hashedInput);
    }

    public User loginAndReturnUser(String username, String password) {

        User user = findUserByUsername(username);

        if (user == null) {
            return null;
        }
     // Verify the input by hashing it and comparing it to the stored hash
        String hashedInput = encryptPassword(password);

        if (user.getPassword().equals(hashedInput)) {
            return user;
        }

        return null;
    }
    
    public void updateUser(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(user);
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
    
    public void deleteUser(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
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
    

    public String encryptPassword(String password) {
        try {
        	// Standard SHA-256 Hashing Implementation
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }
 // updateAndMerge and delete logic using em.merge() and em.remove()...
}
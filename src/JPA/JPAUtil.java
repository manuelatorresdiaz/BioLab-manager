package JPA;

import javax.persistence.*;
/**
 * Utility class to manage the JPA EntityManagerFactory.
 * This class follows the Singleton pattern to ensure that the 
 * Persistence Unit is only initialized once, saving system resources.
 */
public class JPAUtil {
    private static final EntityManagerFactory emf;

    static {
        try {
        	// "BioLabPU" must match the name defined in your persistence.xml file
            emf = Persistence.createEntityManagerFactory("BioLabPU");
        } catch (Throwable ex) {
            System.err.println("Error creating the EntityManager Factory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    /**
     * @return The global EntityManagerFactory instance for the BioLab application.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
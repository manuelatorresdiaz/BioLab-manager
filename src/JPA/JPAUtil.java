/*
 * package JPA; //HAY QUE REVISAR
 * 
 * import javax.persistence.EntityManager; import
 * javax.persistence.EntityManagerFactory; import javax.persistence.Persistence;
 * 
 * public class JPAUtil { // El nombre "BioLabPU" debe ser el mismo que pusiste
 * en tu persistence.xml private static final EntityManagerFactory emf =
 * Persistence.createEntityManagerFactory("BioLabPU");
 * 
 * public static EntityManager getEntityManager() { return
 * emf.createEntityManager(); }
 * 
 * public static void close() { if (emf != null && emf.isOpen()) { emf.close();
 * } } }
 */
package JPA;


import javax.persistence.*;

public class JPAUtil {
    private static final EntityManagerFactory emf;

    static {
        try {
            // "BioLabPU" debe ser el nombre que pusiste en persistence.xml
            emf = Persistence.createEntityManagerFactory("BioLabPU");
        } catch (Throwable ex) {
            System.err.println("Error al crear la Factoría de EntityManager: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
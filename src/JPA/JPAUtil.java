package JPA;
//HAY QUE REVISAR

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    // El nombre "BioLabPU" debe ser el mismo que pusiste en tu persistence.xml
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BioLabPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

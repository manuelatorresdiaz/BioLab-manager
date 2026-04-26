package main; // Asegúrate de que el package coincida

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import bioLabPOJOS.User;
import JPA.JPAUtil;
import JPA.JPAUserManager;

public class Main {
    public static void main(String[] args) {
        // 1. Obtenemos el EntityManager desde tu clase de utilidad
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 2. Creamos un usuario de prueba
            User nuevoUsuario = new User("Admin", "1234");

            // 3. Iniciamos transacción y guardamos
            tx.begin();
            em.persist(nuevoUsuario);
            tx.commit();

            System.out.println("✅ ¡Éxito! Usuario guardado con ID: " + nuevoUsuario.getId());

            // 4. Comprobamos que podemos leerlo
            User usuarioLeido = em.find(User.class, nuevoUsuario.getId());
            System.out.println("📖 Usuario recuperado de la DB: " + usuarioLeido.getUsername());

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("❌ Error en la comprobación:");
            e.printStackTrace();
        } finally {
            em.close();
            System.out.println("🔌 Conexión cerrada.");
        }
    }
}
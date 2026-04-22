import model.Physician;
import repository.DBConnection;
import repository.PhysicianRepository;
import repository.PhysicianRepositoryDB;
import java.util.List;

// ¡AQUÍ ESTÁ LA CLAVE! El nombre debe coincidir con tu archivo .java
public class TestPhysician { 
    
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DE BASE DE DATOS ===");

        DBConnection conexion = new DBConnection();
        PhysicianRepository repo = new PhysicianRepositoryDB(conexion);

        Physician medicoPrueba = new Physician(
                999, 
                "Gregory", 
                "House", 
                "Diagnóstico Médico", 
                "555-0192", 
                "house@hospital.com"
        );

        System.out.println("\n[Prueba 1] Intentando guardar al médico en la BD...");
        repo.save(medicoPrueba);

        System.out.println("\n[Prueba 2] Leyendo todos los médicos de la BD...");
        List<Physician> listaMedicos = repo.findAll();

        if (listaMedicos.isEmpty()) {
            System.out.println("❌ La tabla está vacía o hubo un error al leer.");
        } else {
            System.out.println("✅ ¡Éxito! Se encontraron " + listaMedicos.size() + " médicos:");
            for (Physician medico : listaMedicos) {
                System.out.println(" -> ID: " + medico.getPhysicianId() + 
                                   " | Nombre: " + medico.getFirstName() + " " + medico.getLastName() + 
                                   " | Especialidad: " + medico.getSpecialty());
            }
        }
        
        System.out.println("\n=== FIN DE LAS PRUEBAS ===");
    }
}
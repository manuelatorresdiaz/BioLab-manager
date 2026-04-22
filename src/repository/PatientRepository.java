package repository;

import model.Patient;
// Esta interfaz va a dictar las reglas para cumplir casi todos los requisitos de JDBC de tu rúbrica
import java.util.List;

public interface PatientRepository {
    // Para: Insert new data
    void save(Patient patient);
    
    // Para: Update data
    void update(Patient patient);
    
    // Para: Delete data
    void delete(int patientId);
    
    // Búsqueda por Llave Primaria (PK)
    Patient findById(int patientId);
    
    // Búsqueda por columna diferente a PK (Requisito de la rúbrica)
    List<Patient> findByName(String name); 
    
    // Para: Show all elements
    List<Patient> findAll();
}
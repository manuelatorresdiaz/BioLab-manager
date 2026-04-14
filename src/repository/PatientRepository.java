package repository;

import model.Patient;
import java.util.List;

public interface PatientRepository {
    void save(Patient patient);
    void update(Patient patient);
    void delete(int patientId);
    Patient findById(int patientId);
    List<Patient> findAll();
}
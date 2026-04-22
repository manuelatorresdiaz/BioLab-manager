package interfaces;

import java.util.List;

import bioLabPOJOS.Patient;

public interface PatientManager {

    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatient(int patientId);

    Patient getPatientById(int patientId);

    List<Patient> getAllPatients();
}
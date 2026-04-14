package ui;

import model.Patient;
import repository.PatientRepository;
import repository.PatientRepositoryDB;
import service.PatientService;

public class PatientUI {

    public static void main(String[] args) {
        PatientRepository patientRepository = new PatientRepositoryDB();
        PatientService patientService = new PatientService(patientRepository);

        Patient patient = new Patient(
                1,
                "Ana",
                "Lopez",
                "2000-05-10",
                "Female",
                "123456789",
                "Madrid"
        );

        patientService.addPatient(patient);
        patientService.updatePatient(patient);
        patientService.deletePatient(1);
    }
}

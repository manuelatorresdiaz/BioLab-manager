package main;
import repository.*;
import model.Patient;

public class Main {
    public static void main(String[] args) {

        DBConnection db = new DBConnection();
        PatientRepository repo = new PatientRepositoryDB(db);

        // INSERT
        Patient p = new Patient(0, "Laura", "Marin", "2005-01-01", "F", "123456", "Madrid");
        repo.save(p);

        // SELECT ALL
        System.out.println(repo.findAll());

        // SELECT BY ID
        System.out.println(repo.findById(1));

        // UPDATE
        p.setPatientId(1);
        p.setPhone("999999");
        repo.update(p);

        // DELETE
        // repo.delete(1);
    }
}
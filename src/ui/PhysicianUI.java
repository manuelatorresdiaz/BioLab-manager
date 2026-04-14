package ui;

import model.Physician;
import repository.PhysicianRepository;
import repository.PhysicianRepositoryDB;
import service.PhysicianService;

public class PhysicianUI {

    public static void main(String[] args) {

        PhysicianRepository repo = new PhysicianRepositoryDB();
        PhysicianService service = new PhysicianService(repo);

        Physician physician = new Physician(
                1,
                "Carlos",
                "Perez",
                "Cardiology",
                "987654321",
                "carlos@hospital.com"
        );

        service.addPhysician(physician);
        service.updatePhysician(physician);
        service.deletePhysician(1);
    }
}
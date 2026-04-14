package repository;

import model.Physician;
import java.util.ArrayList;
import java.util.List;

public class PhysicianRepositoryDB implements PhysicianRepository {

    private List<Physician> physicians = new ArrayList<>();

    @Override
    public void save(Physician physician) {
        physicians.add(physician);
        System.out.println("Saving physician: " + physician.getFirstName() + " " + physician.getLastName());
    }

    @Override
    public void update(Physician physician) {
        System.out.println("Updating physician with ID: " + physician.getPhysicianId());
    }

    @Override
    public void delete(int physicianId) {
        System.out.println("Deleting physician with ID: " + physicianId);
    }

    @Override
    public Physician findById(int physicianId) {
        return null;
    }

    @Override
    public List<Physician> findAll() {
        return physicians;
    }
}
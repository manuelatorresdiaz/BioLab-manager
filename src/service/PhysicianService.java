package service;

import model.Physician;
import repository.PhysicianRepository;

import java.util.List;

public class PhysicianService {

    private PhysicianRepository physicianRepository;

    public PhysicianService(PhysicianRepository physicianRepository) {
        this.physicianRepository = physicianRepository;
    }

    public void addPhysician(Physician physician) {
        physicianRepository.save(physician);
    }

    public void updatePhysician(Physician physician) {
        physicianRepository.update(physician);
    }

    public void deletePhysician(int id) {
        physicianRepository.delete(id);
    }

    public Physician getPhysician(int id) {
        return physicianRepository.findById(id);
    }

    public List<Physician> getAllPhysicians() {
        return physicianRepository.findAll();
    }
}
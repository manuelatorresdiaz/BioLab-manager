package repository;

import model.Physician;
import java.util.List;

public interface PhysicianRepository {

    void save(Physician physician);

    void update(Physician physician);

    void delete(int physicianId);

    Physician findById(int physicianId);

    List<Physician> findAll();
}
package interfaces;

import java.util.List;
import model.Physician;

public interface PhysicianManager {

    void addPhysician(Physician physician);

    void updatePhysician(Physician physician);

    void deletePhysician(int physicianId);

    Physician getPhysicianById(int physicianId);

    List<Physician> getAllPhysicians();
}
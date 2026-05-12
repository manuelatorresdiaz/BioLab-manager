package interfaces;

import java.util.List;

import bioLabPOJOS.Physician;
/**
 * Interface defining the operational contract for Physician management.
 * Provides standard persistence methods to maintain the directory of 
 * medical professionals authorized to request laboratory tests.
 */
public interface PhysicianManager {
	/**
     * Registers a new physician into the laboratory directory.
     * @param physician The Physician entity containing professional and contact details.
     */
    void addPhysician(Physician physician);
    /**
     * Updates an existing physician's information (specialty, phone, or email).
     * @param physician The Physician object with the modified data.
     */
    void updatePhysician(Physician physician);
    /**
     * Removes a physician from the system registry.
     * @param physicianId The unique identifier of the physician to be removed.
     */
    void deletePhysician(int physicianId);
    /**
     * Retrieves a physician's full profile using their ID.
     * @param physicianId Unique identifier.
     * @return The Physician object if found, otherwise null.
     */
    Physician getPhysicianById(int physicianId);
    /**
     * Lists all medical professionals currently registered in the system.
     * @return A comprehensive list of all Physicians.
     */
    List<Physician> getAllPhysicians();
}
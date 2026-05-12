package interfaces;

import java.util.List;

import bioLabPOJOS.Patient;
/**
 * Interface defining patient data management operations.
 * Includes support for demographic record keeping, search filters, 
 * and multimedia (BLOB) data handling.
 */
public interface PatientManager {
	/**
     * Registers a new patient in the database.
     * @param patient The Patient POJO containing demographic details.
     */
    void addPatient(Patient patient);
    /**
     * Modifies an existing patient's record.
     * @param patient The updated Patient entity.
     */
    void updatePatient(Patient patient);
    /**
     * Removes a patient record from the system.
     * @param patientId Unique identifier of the patient.
     */
    void deletePatient(int patientId);
    /**
     * Retrieves a full patient profile by their ID.
     * @param patientId Unique identifier.
     * @return The Patient object or null if not found.
     */
    Patient getPatientById(int patientId);
    /**
     * Retrieves a list of all patients registered in the laboratory.
     */
    List<Patient> getAllPatients();
    /**
     * Binary Data Support: Updates the patient's record with an image file.
     * This method handles the conversion of a file path to a BLOB in the database.
     * * @param patientId The ID of the patient.
     * @param imagePath The local system path to the image file.
     */
    void updatePatientImage(int patientId, String imagePath);
    /**
     * Search Filter: Finds patients based on their last name.
     * Useful for administrative lookups when the ID is unknown.
     * * @param lastName The surname to search for.
     * @return A list of patients matching the criteria.
     */
    List<Patient> findPatientsByLastName(String lastName);
}
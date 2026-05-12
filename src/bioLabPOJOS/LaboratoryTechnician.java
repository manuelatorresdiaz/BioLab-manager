package bioLabPOJOS;

/**
 * Represents a Laboratory Technician within the BioLab system.
 * This class stores profile information and work shift assignments.
 */

public class LaboratoryTechnician {
    private int technicianId;
    private String fullName;
    private String email;
    private String shiftName;

    /**
     * Default constructor for framework compatibility and manual instantiation.
     */
    
    public LaboratoryTechnician() {}

    /**
     * Parameterized constructor to initialize a technician's profile.
     * 
     * @param technicianId Unique ID for the technician.
     * @param fullName     The technician's full name.
     * @param email        Contact email address.
     * @param shiftName    The work shift assigned (stored in the lastName field).
     */
    
    public LaboratoryTechnician(int technicianId, String fullName, String email, String shiftName) {
        this.technicianId = technicianId;
        this.fullName = fullName;
        this.email = email;
        this.shiftName = shiftName;
    }

 // Getters and Setters
    
    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }
}
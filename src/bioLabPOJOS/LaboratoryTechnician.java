package bioLabPOJOS;

public class LaboratoryTechnician {
    private int technicianId;
    private String fullName;
    private String email;
    private String lastName;

    public LaboratoryTechnician() {}

    public LaboratoryTechnician(int technicianId, String fullName, String email, String shiftName) {
        this.technicianId = technicianId;
        this.fullName = fullName;
        this.email = email;
        this.lastName = shiftName;
    }

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
        return lastName;
    }

    public void setShiftName(String shiftName) {
        this.lastName = shiftName;
    }
}
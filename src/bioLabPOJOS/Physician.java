package bioLabPOJOS;

import java.io.Serializable;

/**
 * Represents a Physician (Doctor) within the BioLab system.
 * This entity tracks the medical professionals who request laboratory orders
 * and their respective specialties.
 */

public class Physician implements Serializable {

    private int physicianId;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;

    /**
     * Parameterized constructor to initialize a physician's profile.
     * 
     * @param physicianId Unique database identifier.
     * @param firstName   Physician's given name.
     * @param lastName    Physician's family name.
     * @param specialty   Medical field of expertise (e.g., Cardiology, Oncology).
     * @param phone       Contact telephone number.
     * @param email       Professional contact email.
     */
    
    public Physician(int physicianId, String firstName, String lastName, String specialty, String phone, String email) {
        this.physicianId = physicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
    }

 // Getters and Setters
    
    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
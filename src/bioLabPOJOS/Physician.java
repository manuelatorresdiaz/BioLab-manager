package bioLabPOJOS;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Represents a Physician (Doctor) within the BioLab system.
 * This entity tracks the medical professionals who request laboratory orders
 * and their respective specialties.
 */

@XmlRootElement(name = "Physician")
@XmlAccessorType(XmlAccessType.FIELD)
public class Physician implements Serializable {

    private int physicianId;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;

    /**
     * Empty constructor required by JAXB.
     */
    
    public Physician() {
    }

    /**
     * Parameterized constructor to initialize a physician's profile.
     * 
     * @param physicianId Unique database identifier.
     * @param firstName   Physician's given name.
     * @param lastName    Physician's family name.
     * @param specialty   Medical field of expertise.
     * @param phone       Contact telephone number.
     * @param email       Professional contact email.
     */

    public Physician(int physicianId, String firstName, String lastName,
                     String specialty, String phone, String email) {

        this.physicianId = physicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
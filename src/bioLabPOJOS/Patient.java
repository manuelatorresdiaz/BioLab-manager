package bioLabPOJOS;

import java.io.Serializable;

/**
 * Represents a patient within the BioLab system.
 * This entity stores demographic information and supports binary data 
 * for profile images.
 */

public class Patient implements Serializable {

	// Unique version identifier for serialization consistency
    private static final long serialVersionUID = 1L;

    private int patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String phone;
    private String address;

    /**
     * Additional field to support JDBC BLOB handling.
     * Stores the raw binary data of the patient's profile picture.
     */
    private byte[] profileImage;
    
    /**
     * Parameterized constructor for basic patient registration.
     * 
     * @param patientId   Unique database identifier.
     * @param firstName   Patient's given name.
     * @param lastName    Patient's family name.
     * @param dateOfBirth Date string (standardized format).
     * @param gender      Patient's gender identity.
     * @param phone       Contact telephone number.
     * @param address     Physical residence address.
     */
    
    public Patient(int patientId, String firstName, String lastName, String dateOfBirth,
                   String gender, String phone, String address) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

 // Getters and Setters for standard demographic fields
    
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Retrieves the binary image data.
     * @return A byte array representing the profile image.
     */
    
    public byte[] getProfileImage() {
        return profileImage;
    }

    /**
     * Sets the binary image data (BLOB).
     * @param profileImage The byte array to be stored.
     */
    
    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
package model;

import java.io.Serializable;

public class Physician implements Serializable {

    private int physicianId;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;

    public Physician(int physicianId, String firstName, String lastName, String specialty, String phone, String email) {
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
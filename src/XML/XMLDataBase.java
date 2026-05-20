package XML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import bioLabPOJOS.Role;
import bioLabPOJOS.User;
import bioLabPOJOS.Patient;
import bioLabPOJOS.Physician;
import bioLabPOJOS.LaboratoryOrder;
import bioLabPOJOS.Test;
import bioLabPOJOS.OrderTest;
import bioLabPOJOS.ReferenceRange;
import bioLabPOJOS.LaboratoryTechnician;
import bioLabPOJOS.Administrator;

/**
 * Root container for XML Serialization.
 * This class serves as the Data Transfer Object (DTO) that JAXB uses 
 * to map Java collections into a structured XML document.
 */

@XmlRootElement(name = "BioLabDatabase") // Defines the root tag <BioLabDatabase>
@XmlAccessorType(XmlAccessType.FIELD)	 // Allows JAXB to read fields directly

public class XMLDataBase {

    // Wraps the list in a <Users> tag, with each item as <User>
    @XmlElementWrapper(name = "Users")
    @XmlElement(name = "User")
    private List<User> users;

    // Wraps the list in a <Roles> tag, with each item as <Role>
    @XmlElementWrapper(name = "Roles")
    @XmlElement(name = "Role")
    private List<Role> roles;

    // Wraps the list in a <Patients> tag, with each item as <Patient>
    @XmlElementWrapper(name = "Patients")
    @XmlElement(name = "Patient")
    private List<Patient> patients;

    // Wraps the list in a <Physicians> tag, with each item as <Physician>
    @XmlElementWrapper(name = "Physicians")
    @XmlElement(name = "Physician")
    private List<Physician> physicians;

    // Wraps the list in a <LaboratoryOrders> tag, with each item as <LaboratoryOrder>
    @XmlElementWrapper(name = "LaboratoryOrders")
    @XmlElement(name = "LaboratoryOrder")
    private List<LaboratoryOrder> laboratoryOrders;

    // Wraps the list in a <Tests> tag, with each item as <Test>
    @XmlElementWrapper(name = "Tests")
    @XmlElement(name = "Test")
    private List<Test> tests;

    // Wraps the list in a <OrderTests> tag, with each item as <OrderTest>
    @XmlElementWrapper(name = "OrderTests")
    @XmlElement(name = "OrderTest")
    private List<OrderTest> orderTests;

    // Wraps the list in a <ReferenceRanges> tag, with each item as <ReferenceRange>
    @XmlElementWrapper(name = "ReferenceRanges")
    @XmlElement(name = "ReferenceRange")
    private List<ReferenceRange> referenceRanges;

    // Wraps the list in a <LaboratoryTechnicians> tag, with each item as <LaboratoryTechnician>
    @XmlElementWrapper(name = "LaboratoryTechnicians")
    @XmlElement(name = "LaboratoryTechnician")
    private List<LaboratoryTechnician> laboratoryTechnicians;

    // Wraps the list in a <Administrators> tag, with each item as <Administrator>
    @XmlElementWrapper(name = "Administrators")
    @XmlElement(name = "Administrator")
    private List<Administrator> administrators;

    /**
     * Default constructor required by JAXB.
     */

    public XMLDataBase() {

        this.users = new ArrayList<>();
        this.roles = new ArrayList<>();

        this.patients = new ArrayList<>();
        this.physicians = new ArrayList<>();
        this.laboratoryOrders = new ArrayList<>();
        this.tests = new ArrayList<>();
        this.orderTests = new ArrayList<>();
        this.referenceRanges = new ArrayList<>();
        this.laboratoryTechnicians = new ArrayList<>();
        this.administrators = new ArrayList<>();
    }

    // Standard Getters and Setters for the Marshaling process

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

    public List<LaboratoryOrder> getLaboratoryOrders() {
        return laboratoryOrders;
    }

    public void setLaboratoryOrders(List<LaboratoryOrder> laboratoryOrders) {
        this.laboratoryOrders = laboratoryOrders;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<OrderTest> getOrderTests() {
        return orderTests;
    }

    public void setOrderTests(List<OrderTest> orderTests) {
        this.orderTests = orderTests;
    }

    public List<ReferenceRange> getReferenceRanges() {
        return referenceRanges;
    }

    public void setReferenceRanges(List<ReferenceRange> referenceRanges) {
        this.referenceRanges = referenceRanges;
    }

    public List<LaboratoryTechnician> getLaboratoryTechnicians() {
        return laboratoryTechnicians;
    }

    public void setLaboratoryTechnicians(
            List<LaboratoryTechnician> laboratoryTechnicians) {

        this.laboratoryTechnicians = laboratoryTechnicians;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }
}
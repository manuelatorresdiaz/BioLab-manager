package bioLabPOJOS;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Represents a medical laboratory order within the system.
 * This class links a patient with a physician and tracks the order status.
 */

@XmlRootElement(name = "LaboratoryOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class LaboratoryOrder implements Serializable {

    private static final long serialVersionUID = 4157721529360133187L;

    private int orderId;

    @XmlTransient
    private Patient patient;

    @XmlTransient
    private Physician physician;

    private String orderDate;
    private String status;

    /**
     * Empty constructor required by JAXB.
     */

    public LaboratoryOrder() {
    }

    /**
     * Full constructor to initialize a laboratory order.
     * 
     * @param orderId   Unique identifier for the order.
     * @param patient   The Patient object associated with this order.
     * @param physician The Physician object who requested the order.
     * @param orderDate The date when the order was issued.
     * @param status    The current state of the order.
     */

    public LaboratoryOrder(int orderId, Patient patient,
                           Physician physician,
                           String orderDate,
                           String status) {

        this.orderId = orderId;
        this.patient = patient;
        this.physician = physician;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
    /*
     *  A laboratory order is a legal medical document. Once it is instantiated with an ID, 
     *  a specific patient, and a date, those core details should never be accidentally changed 
     *  in the code. If we need to update the status (e.g., from Pending to Completed), we can add 
     *  a specific setStatus() method later, but the rest of the data remains protected
     */


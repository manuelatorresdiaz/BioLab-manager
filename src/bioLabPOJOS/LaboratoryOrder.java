package bioLabPOJOS;

import java.io.Serializable;

public class LaboratoryOrder implements Serializable {

	private static final long serialVersionUID = 4157721529360133187L;
	
	private int orderId;
    private Patient patient;
    private Physician physician;
    private String orderDate;
    private String status;

    public LaboratoryOrder(int orderId, Patient patient, Physician physician, String orderDate, String status) {
        this.orderId = orderId;
        this.patient = patient;
        this.physician = physician;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Physician getPhysician() {
        return physician;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }
}

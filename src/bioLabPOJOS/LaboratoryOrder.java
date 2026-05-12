package bioLabPOJOS;

import java.io.Serializable;

/**
 * Represents a medical laboratory order within the system.
 * This class links a patient with a physician and tracks the order status.
 * it doesn't hold the actual test results (that is the job of OrderTest); 
 * instead, it holds the metadata of the transaction: Who requested it (Physician), 
 * who it is for (Patient), when it happened (orderDate), and what its current state is (status).
 */

public class LaboratoryOrder implements Serializable {

	// Unique version identifier for serialization consistency
	private static final long serialVersionUID = 4157721529360133187L;
	
	private int orderId;
    private Patient patient;
    private Physician physician;
    private String orderDate;
    private String status;

    /**
     * Full constructor to initialize a laboratory order.
     * 
     * @param orderId   Unique identifier for the order.
     * @param patient   The Patient object associated with this order.
     * @param physician The Physician object who requested the order.
     * @param orderDate The date when the order was issued.
     * @param status    The current state of the order (e.g., Pending, Completed).
     */
    
    public LaboratoryOrder(int orderId, Patient patient, Physician physician, String orderDate, String status) {
        this.orderId = orderId;
        this.patient = patient;
        this.physician = physician;
        this.orderDate = orderDate;
        this.status = status;
    }

 // Getters for retrieving order data
    
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
    /*
     *  A laboratory order is a legal medical document. Once it is instantiated with an ID, 
     *  a specific patient, and a date, those core details should never be accidentally changed 
     *  in the code. If we need to update the status (e.g., from Pending to Completed), we can add 
     *  a specific setStatus() method later, but the rest of the data remains protected
     */
}

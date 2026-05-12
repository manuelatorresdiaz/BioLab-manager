package interfaces;

import java.util.List;
import bioLabPOJOS.LaboratoryOrder;

/**
 * Interface defining the contract for Laboratory Order management.
 * Provides methods for standard CRUD operations and specific clinical filters.
 */

public interface LaboratoryOrderManager {
	/**
     * Feature 7: Registers a new laboratory order in the system.
     * @param order The LaboratoryOrder object to be persisted.
     */
    void addOrder(LaboratoryOrder order);
    /**
     * Updates the status or details of an existing order.
     * @param order The order object with updated information.
     */
    void updateOrder(LaboratoryOrder order);
    /**
     * Removes an order from the database.
     * @param orderId Unique identifier of the order.
     */
    void deleteOrder(int orderId);
    /**
     * Retrieves a single order by its primary key.
     * @param orderId Unique identifier.
     * @return The LaboratoryOrder found, or null if non-existent.
     */
    LaboratoryOrder getOrderById(int orderId);
    /**
     * Lists every laboratory order registered in the system.
     * @return A list of all LaboratoryOrders.
     */
    List<LaboratoryOrder> getAllOrders();
    /**
     * Feature 9: Retrieves the clinical history of orders for a specific patient.
     * @param patientId The ID of the patient.
     * @return List of orders belonging to that patient.
     */
    List<LaboratoryOrder> getOrdersByPatientId(int patientId);
    /**
     * Feature 11: Filters orders that are currently in 'Pending' status.
     * This fulfills the requirement of searching by a non-primary key column (Status).
     * @return List of orders awaiting processing.
     */
    List<LaboratoryOrder> getPendingOrders();
    /**
     * Retrieves all orders requested by a specific doctor.
     * @param physicianId The ID of the physician.
     * @return List of orders associated with the physician.
     */
    List<LaboratoryOrder> getOrdersByPhysicianId(int physicianId);
    
}

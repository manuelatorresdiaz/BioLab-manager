package interfaces;

import java.util.List;
import bioLabPOJOS.LaboratoryOrder;

public interface LaboratoryOrderManager {
	//Feature 7 create new laboratory order
    void addOrder(LaboratoryOrder order);

    void updateOrder(LaboratoryOrder order);

    void deleteOrder(int orderId);

    LaboratoryOrder getOrderById(int orderId);

    List<LaboratoryOrder> getAllOrders();
    
    //Feature 9 View patient orders
    List<LaboratoryOrder> getOrdersByPatientId(int patientId);
    
    //Feature 11 View pending orders and checklist Search by 1 column other than the primary key (of your choice) in at least 1 table
    List<LaboratoryOrder> getPendingOrders();
}

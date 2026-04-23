package interfaces;

import java.util.List;
import bioLabPOJOS.LaboratoryOrder;

public interface LaboratoryOrderManager {

    void addOrder(LaboratoryOrder order);

    void updateOrder(LaboratoryOrder order);

    void deleteOrder(int orderId);

    LaboratoryOrder getOrderById(int orderId);

    List<LaboratoryOrder> getAllOrders();
}
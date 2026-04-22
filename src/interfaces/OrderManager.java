package interfaces;

import java.util.List;
import model.LaboratoryOrder;

public interface OrderManager {

    void addOrder(LaboratoryOrder order);

    void updateOrder(LaboratoryOrder order);

    void deleteOrder(int orderId);

    LaboratoryOrder getOrderById(int orderId);

    List<LaboratoryOrder> getAllOrders();
}
package repository;

import model.LaboratoryOrder;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryDB implements OrderRepository {

    private List<LaboratoryOrder> orders = new ArrayList<>();

    @Override
    public void save(LaboratoryOrder order) {
        orders.add(order);
        System.out.println("Saving order ID: " + order.getOrderId());
    }

    @Override
    public void update(LaboratoryOrder order) {
        System.out.println("Updating order ID: " + order.getOrderId());
    }

    @Override
    public void delete(int orderId) {
        System.out.println("Deleting order ID: " + orderId);
    }

    @Override
    public LaboratoryOrder findById(int orderId) {
        return null;
    }

    @Override
    public List<LaboratoryOrder> findAll() {
        return orders;
    }
}
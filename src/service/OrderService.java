package service;

import model.LaboratoryOrder;
import repository.OrderRepository;
import repository.OrderRepositoryDB;

import java.util.List;

public class OrderService {

    private OrderRepository repository = new OrderRepositoryDB();

    public void createOrder(LaboratoryOrder order) {

        if(order.getPatientId() <= 0) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        if(order.getPhysicianId() <= 0) {
            System.out.println("Invalid Physician ID.");
            return;
        }

        if(order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("Pending");
        }

        repository.save(order);
    }

    public List<LaboratoryOrder> getAllOrders() {
        return repository.findAll();
    }

    public void cancelOrder(int id) {
        repository.delete(id);
    }
}
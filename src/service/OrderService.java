package service;

import model.LaboratoryOrder;
import repository.OrderRepository;

import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(LaboratoryOrder order) {
        orderRepository.save(order);
    }

    public void updateOrder(LaboratoryOrder order) {
        orderRepository.update(order);
    }

    public void deleteOrder(int id) {
        orderRepository.delete(id);
    }

    public List<LaboratoryOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}
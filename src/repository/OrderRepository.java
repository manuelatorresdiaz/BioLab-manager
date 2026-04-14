package repository;

import model.LaboratoryOrder;
import java.util.List;

public interface OrderRepository {

    void save(LaboratoryOrder order);

    void update(LaboratoryOrder order);

    void delete(int orderId);

    LaboratoryOrder findById(int orderId);

    List<LaboratoryOrder> findAll();
}
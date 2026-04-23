package interfaces;

import java.util.List;
import bioLabPOJOS.OrderTest;

public interface OrderTestManager {

    // Adds a new test result linked to an order
    void addOrderTest(OrderTest orderTest);

    // Updates an existing order-test record
    void updateOrderTest(OrderTest orderTest);

    // Deletes an order-test record using orderId and testId
    void deleteOrderTest(int orderId, int testId);

    // Finds one order-test record by its composite key
    OrderTest getOrderTestById(int orderId, int testId);

    // Returns all order-test records
    List<OrderTest> getAllOrderTests();
}
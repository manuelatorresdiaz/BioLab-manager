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
    
 // --- MÉTODOS DE ANALÍTICA (RESULTS & ANALYTICS) ---

    // 1. View test history: Obtiene todos los resultados históricos de un examen específico
    List<OrderTest> getTestHistory(int testId);

    // 2. Most requested tests: Devuelve los IDs de las pruebas más pedidas, ordenadas de mayor a menor
    List<Integer> getMostRequestedTests();

    // 3. Average test values: Calcula matemáticamente el promedio de los resultados de un examen
    double getAverageTestValue(int testId);

    // 4. Identify abnormal results: Filtra los resultados que se salen del rango normal
    List<OrderTest> getAbnormalResults(int testId, double normalMin, double normalMax);
    
    List<OrderTest> getResultsByPatientId(int patientId);
}
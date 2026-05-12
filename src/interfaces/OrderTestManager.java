package interfaces;

import java.util.List;
import bioLabPOJOS.OrderTest;
/**
 * Interface responsible for the management and analysis of clinical test results.
 * It handles the many-to-many relationship between Orders and Tests, 
 * providing specialized methods for statistical and diagnostic analytics.
 */
public interface OrderTestManager {
	/**
     * Links a specific test result to an existing laboratory order.
     * @param orderTest The result entity containing the value and IDs.
     */
    void addOrderTest(OrderTest orderTest);
    /**
     * Modifies an existing result record (e.g., correcting a measurement error).
     * @param orderTest The updated result entity.
     */
    void updateOrderTest(OrderTest orderTest);
    /**
     * Deletes a record based on its composite key.
     * @param orderId ID of the associated order.
     * @param testId ID of the associated test catalog entry.
     */
    void deleteOrderTest(int orderId, int testId);
    /**
     * Retrieves a specific result using its composite primary key.
     */
    OrderTest getOrderTestById(int orderId, int testId);
    /**
     * Lists every result record stored in the database.
     */
    List<OrderTest> getAllOrderTests();
    /**
     * Retrieves the complete historical timeline of results for a specific test type.
     * Used for clinical trend analysis.
     */
    List<OrderTest> getTestHistory(int testId);
    /**
     * Statistical Ranking: Identifies which tests are most frequently requested.
     * @return A list of Test IDs sorted by demand (Descending).
     */
    List<Integer> getMostRequestedTests();
    /**
     * Mathematical Analytics: Calculates the mean value for all results of a specific test.
     * @param testId The test type to calculate.
     * @return The arithmetic mean of the results.
     */
    double getAverageTestValue(int testId);
    /**
     * Clinical Alert System: Identifies results that fall outside of the provided healthy bounds.
     * @param testId The test type to check.
     * @param normalMin The lower healthy threshold.
     * @param normalMax The upper healthy threshold.
     * @return A filtered list of clinical results flagged as "Abnormal".
     */
    List<OrderTest> getAbnormalResults(int testId, double normalMin, double normalMax);
    /**
     * Retrieves all clinical results belonging to a specific patient.
     * @param patientId The patient identifier.
     * @return Comprehensive list of the patient's results across all orders.
     */
    List<OrderTest> getResultsByPatientId(int patientId);
}
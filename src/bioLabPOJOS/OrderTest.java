package bioLabPOJOS;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the association between a Laboratory Order and a specific Test.
 * This class stores the actual clinical results, including values, dates, and status.
 */

public class OrderTest implements Serializable {
	
	/**
     * Unique version identifier for the serialization mechanism.
     * Ensures that the sender and receiver of a serialized object maintain compatibility.
     */

    // Serializable allows this object to be converted into bytes
    // so it can be saved to a file, sent through a network, or transferred between systems.
    // serialVersionUID is the version identifier of the class during serialization.
    private static final long serialVersionUID = 1L;

    private int orderId;
    private int testId;
    private double resultValue;
    private Date resultDate;
    private String resultStatus;

    /**
     * Default constructor required for framework compatibility (JAXB/JPA).
     */
    
    public OrderTest() {
    }

    /**
     * Parameterized constructor to initialize a test result record.
     * 
     * @param orderId      The ID of the order this test belongs to.
     * @param testId       The ID of the test performed.
     * @param resultValue  The numerical value obtained from the laboratory analysis.
     * @param resultDate   The timestamp when the result was recorded.
     * @param resultStatus The current status of the result (e.g., Pending, Validated).
     */
    
    public OrderTest(int orderId, int testId, double resultValue, Date resultDate, String resultStatus) {
        this.orderId = orderId;
        this.testId = testId;
        this.resultValue = resultValue;
        this.resultDate = resultDate;
        this.resultStatus = resultStatus;
    }

 // Getters and Setters
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     * Returns a string representation of the test result for logging or debugging.
     */
    
    @Override
    public String toString() {
        return "OrderTest{" +
                "orderId=" + orderId +
                ", testId=" + testId +
                ", resultValue=" + resultValue +
                ", resultDate=" + resultDate +
                ", resultStatus='" + resultStatus + '\'' +
                '}';
    }
}
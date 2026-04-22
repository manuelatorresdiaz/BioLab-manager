package bioLabPOJOS;

import java.io.Serializable;
import java.util.Date;

public class OrderTest implements Serializable {

    // Serializable allows this object to be converted into bytes
    // so it can be saved to a file, sent through a network, or transferred between systems.
    // serialVersionUID is the version identifier of the class during serialization.
    private static final long serialVersionUID = 1L;

    private int orderId;
    private int testId;
    private double resultValue;
    private Date resultDate;
    private String resultStatus;

    public OrderTest() {
    }

    public OrderTest(int orderId, int testId, double resultValue, Date resultDate, String resultStatus) {
        this.orderId = orderId;
        this.testId = testId;
        this.resultValue = resultValue;
        this.resultDate = resultDate;
        this.resultStatus = resultStatus;
    }

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
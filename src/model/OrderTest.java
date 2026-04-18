package model;

public class OrderTest {
    private int orderId;
    private int testId;

    public OrderTest() {}

    public OrderTest(int orderId, int testId) {
        this.orderId = orderId;
        this.testId = testId;
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

    @Override
    public String toString() {
        return "OrderTest{" +
                "orderId=" + orderId +
                ", testId=" + testId +
                '}';
    }
}
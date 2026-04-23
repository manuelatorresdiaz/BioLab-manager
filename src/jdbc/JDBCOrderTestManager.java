package jdbc;

import interfaces.OrderTestManager;
import bioLabPOJOS.OrderTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderTestManager implements OrderTestManager {

    private ConnectionManager cm;

    public JDBCOrderTestManager(ConnectionManager cm) {
        this.cm = cm;
    }

    @Override
    public void addOrderTest(OrderTest orderTest) {
        // Inserts a new result for a specific order and test
        String sql = "INSERT INTO OrderTest (orderId, testId, resultValue, resultDate, resultStatus) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, orderTest.getOrderId());
            stmt.setInt(2, orderTest.getTestId());
            stmt.setDouble(3, orderTest.getResultValue());

            // We save the java.util.Date as text using toString for now
            stmt.setString(4, orderTest.getResultDate().toString());
            stmt.setString(5, orderTest.getResultStatus());

            stmt.executeUpdate();
            System.out.println("OrderTest saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving OrderTest.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void updateOrderTest(OrderTest orderTest) {
        // Updates result data of an existing order-test record
        String sql = "UPDATE OrderTest SET resultValue=?, resultDate=?, resultStatus=? WHERE orderId=? AND testId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, orderTest.getResultValue());
            stmt.setString(2, orderTest.getResultDate().toString());
            stmt.setString(3, orderTest.getResultStatus());
            stmt.setInt(4, orderTest.getOrderId());
            stmt.setInt(5, orderTest.getTestId());

            stmt.executeUpdate();
            System.out.println("OrderTest updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating OrderTest.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void deleteOrderTest(int orderId, int testId) {
        // Deletes one order-test record using both IDs
        String sql = "DELETE FROM OrderTest WHERE orderId=? AND testId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, orderId);
            stmt.setInt(2, testId);

            stmt.executeUpdate();
            System.out.println("OrderTest deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting OrderTest.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public OrderTest getOrderTestById(int orderId, int testId) {
        // Retrieves one order-test record
        String sql = "SELECT * FROM OrderTest WHERE orderId=? AND testId=?";
        OrderTest orderTest = null;

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, orderId);
            stmt.setInt(2, testId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                orderTest = new OrderTest(
                        rs.getInt("orderId"),
                        rs.getInt("testId"),
                        rs.getDouble("resultValue"),
                        new java.util.Date(), // Temporary date object for now
                        rs.getString("resultStatus")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding OrderTest.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return orderTest;
    }

    @Override
    public List<OrderTest> getAllOrderTests() {
        // Retrieves all order-test records
        List<OrderTest> orderTests = new ArrayList<>();
        String sql = "SELECT * FROM OrderTest";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderTest orderTest = new OrderTest(
                        rs.getInt("orderId"),
                        rs.getInt("testId"),
                        rs.getDouble("resultValue"),
                        new java.util.Date(), // Temporary date object for now
                        rs.getString("resultStatus")
                );

                orderTests.add(orderTest);
            }

        } catch (SQLException e) {
            System.out.println("Error getting OrderTests.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return orderTests;
    }
}
package jdbc;

import interfaces.OrderTestManager;
import bioLabPOJOS.OrderTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * JDBC Implementation for the Order-Test relationship.
 * This class handles the persistence of clinical results and performs 
 * statistical analysis on laboratory data.
 */
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
    
 // =====================================================================

    @Override
    public List<OrderTest> getTestHistory(int testId) {
        List<OrderTest> history = new ArrayList<>();
        String sql = "SELECT * FROM OrderTest WHERE testId = ? ORDER BY resultDate DESC";
        
        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, testId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderTest ot = new OrderTest(
                        rs.getInt("orderId"),
                        rs.getInt("testId"),
                        rs.getDouble("resultValue"),
                        new java.util.Date(), 
                        rs.getString("resultStatus")
                );
                history.add(ot);
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo el historial.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
        return history;
    }
 // --- ANALYTICS AND CLINICAL LOGIC ---
    @Override
    public List<Integer> getMostRequestedTests() {
        List<Integer> topTests = new ArrayList<>();
        String sql = "SELECT testId, COUNT(testId) AS total_requests " +
                     "FROM OrderTest " +
                     "GROUP BY testId " +
                     "ORDER BY total_requests DESC";
        
        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                topTests.add(rs.getInt("testId"));
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo las pruebas más pedidas.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
        return topTests;
    }

    @Override
    public double getAverageTestValue(int testId) {
        double average = 0.0;
        String sql = "SELECT AVG(resultValue) AS average_value FROM OrderTest WHERE testId = ?";
        
        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, testId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                average = rs.getDouble("average_value");
            }
        } catch (SQLException e) {
            System.out.println("Error calculando el promedio.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
        return average;
    }

    @Override
    public List<OrderTest> getAbnormalResults(int testId, double normalMin, double normalMax) {
        List<OrderTest> abnormalResults = new ArrayList<>();
        String sql = "SELECT * FROM OrderTest WHERE testId = ? AND (resultValue < ? OR resultValue > ?)";
        
        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, testId);
            stmt.setDouble(2, normalMin);
            stmt.setDouble(3, normalMax);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderTest ot = new OrderTest(
                        rs.getInt("orderId"),
                        rs.getInt("testId"),
                        rs.getDouble("resultValue"),
                        new java.util.Date(), 
                        rs.getString("resultStatus")
                );
                abnormalResults.add(ot);
            }
        } catch (SQLException e) {
            System.out.println("Error buscando resultados anormales.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
        return abnormalResults;
    }
    
    @Override
    public List<OrderTest> getResultsByPatientId(int patientId) {
        List<OrderTest> results = new ArrayList<>();

        String sql = "SELECT ot.* " +
                     "FROM OrderTest ot " +
                     "JOIN LaboratoryOrder lo ON ot.orderId = lo.orderId " +
                     "WHERE lo.patientId = ?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderTest ot = new OrderTest(
                        rs.getInt("orderId"),
                        rs.getInt("testId"),
                        rs.getDouble("resultValue"),
                        new java.util.Date(),
                        rs.getString("resultStatus")
                );

                results.add(ot);
            }

        } catch (SQLException e) {
            System.out.println("Error getting patient results.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return results;
    }
 // CRUD methods (updateOrderTest, deleteOrderTest, etc.) follow the same pattern...
}
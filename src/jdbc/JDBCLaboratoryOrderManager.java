package jdbc;

import interfaces.LaboratoryOrderManager;
import bioLabPOJOS.LaboratoryOrder;
import bioLabPOJOS.Patient;
import bioLabPOJOS.Physician;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * JDBC implementation for managing laboratory clinical orders.
 * Handles relational mapping between Orders, Patients, and Physicians.
 */
public class JDBCLaboratoryOrderManager implements LaboratoryOrderManager {

    private ConnectionManager cm;

    public JDBCLaboratoryOrderManager(ConnectionManager cm) {
        this.cm = cm;
    }

    @Override
    public void addOrder(LaboratoryOrder order) {
        String sql = "INSERT INTO LaboratoryOrder (patientId, physicianId, orderDate, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
         // Extracting IDs from the associated POJO objects (Relational Mapping)
            stmt.setInt(1, order.getPatient().getPatientId());
            stmt.setInt(2, order.getPhysician().getPhysicianId());
            stmt.setString(3, order.getOrderDate());
            stmt.setString(4, order.getStatus());

            stmt.executeUpdate();
            System.out.println("Order saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving order.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void updateOrder(LaboratoryOrder order) {
        String sql = "UPDATE LaboratoryOrder SET patientId=?, physicianId=?, orderDate=?, status=? WHERE orderId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, order.getPatient().getPatientId());
            stmt.setInt(2, order.getPhysician().getPhysicianId());
            stmt.setString(3, order.getOrderDate());
            stmt.setString(4, order.getStatus());
            stmt.setInt(5, order.getOrderId());

            stmt.executeUpdate();
            System.out.println("Order updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating order.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM LaboratoryOrder WHERE orderId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);

            stmt.executeUpdate();
            System.out.println("Order deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting order.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public LaboratoryOrder getOrderById(int orderId) {
        String sql = "SELECT * FROM LaboratoryOrder WHERE orderId=?";
        LaboratoryOrder order = null;

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	// Constructing minimal Patient/Physician objects to satisfy the constructor
                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        "", "", "", "", "", ""
                );

                Physician physician = new Physician(
                        rs.getInt("physicianId"),
                        "", "", "", "", ""
                );

                order = new LaboratoryOrder(
                        rs.getInt("orderId"),
                        patient,
                        physician,
                        rs.getString("orderDate"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding order.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return order;
    }

    @Override
    public List<LaboratoryOrder> getAllOrders() {
        List<LaboratoryOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM LaboratoryOrder";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        "", "", "", "", "", ""
                );

                Physician physician = new Physician(
                        rs.getInt("physicianId"),
                        "", "", "", "", ""
                );

                LaboratoryOrder order = new LaboratoryOrder(
                        rs.getInt("orderId"),
                        patient,
                        physician,
                        rs.getString("orderDate"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Error getting orders.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return orders;
    }
    
    @Override
    public List<LaboratoryOrder> getOrdersByPatientId(int patientId) {
    	// Feature 9: View patient orders
        List<LaboratoryOrder> orders = new ArrayList<>();

        String sql = "SELECT * FROM LaboratoryOrder WHERE patientId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        "", "", "", "", "", ""
                );

                Physician physician = new Physician(
                        rs.getInt("physicianId"),
                        "", "", "", "", ""
                );

                LaboratoryOrder order = new LaboratoryOrder(
                        rs.getInt("orderId"),
                        patient,
                        physician,
                        rs.getString("orderDate"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Error getting patient orders.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return orders;
    }
    
    @Override
    public List<LaboratoryOrder> getPendingOrders() {
    	// Feature 11: Search by status (non-primary key column)
        List<LaboratoryOrder> orders = new ArrayList<>();

        String sql = "SELECT * FROM LaboratoryOrder WHERE status = 'pending'";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        "", "", "", "", "", ""
                );

                Physician physician = new Physician(
                        rs.getInt("physicianId"),
                        "", "", "", "", ""
                );

                LaboratoryOrder order = new LaboratoryOrder(
                        rs.getInt("orderId"),
                        patient,
                        physician,
                        rs.getString("orderDate"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Error getting pending orders.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return orders;
    }
    @Override
    public List<LaboratoryOrder> getOrdersByPhysicianId(int physicianId) {

        List<LaboratoryOrder> orders = new ArrayList<>();

        String sql = "SELECT * FROM LaboratoryOrder WHERE physicianId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, physicianId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        "", "", "", "", "", ""
                );

                Physician physician = new Physician(
                        rs.getInt("physicianId"),
                        "", "", "", "", ""
                );

                LaboratoryOrder order = new LaboratoryOrder(
                        rs.getInt("orderId"),
                        patient,
                        physician,
                        rs.getString("orderDate"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Error getting physician orders.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return orders;
    }
}
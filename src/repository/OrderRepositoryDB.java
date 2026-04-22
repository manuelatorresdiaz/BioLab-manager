package repository;

import model.LaboratoryOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryDB implements OrderRepository {

    @Override
    public void save(LaboratoryOrder order) {

        String sql = "INSERT INTO LaboratoryOrder(patientId, physicianId, orderDate, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getPatientId());
            stmt.setInt(2, order.getPhysicianId());
            stmt.setString(3, order.getOrderDate());
            stmt.setString(4, order.getStatus());

            stmt.executeUpdate();

            System.out.println("Order saved in database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LaboratoryOrder> findAll() {

        List<LaboratoryOrder> orders = new ArrayList<>();

        String sql = "SELECT * FROM LaboratoryOrder";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                LaboratoryOrder order = new LaboratoryOrder();

                order.setOrderId(rs.getInt("orderId"));
                order.setPatientId(rs.getInt("patientId"));
                order.setPhysicianId(rs.getInt("physicianId"));
                order.setOrderDate(rs.getString("orderDate"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM LaboratoryOrder WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Order deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
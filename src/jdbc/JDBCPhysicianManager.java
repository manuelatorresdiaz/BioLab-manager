package jdbc;

import interfaces.PhysicianManager;
import model.Physician;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPhysicianManager implements PhysicianManager {

    private ConnectionManager connectionManager;

    public JDBCPhysicianManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void addPhysician(Physician physician) {
        String sql = "INSERT INTO Physician (firstName, lastName, specialty, phone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connectionManager.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed. Physician was not saved.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, physician.getFirstName());
            stmt.setString(2, physician.getLastName());
            stmt.setString(3, physician.getSpecialty());
            stmt.setString(4, physician.getPhone());
            stmt.setString(5, physician.getEmail());

            stmt.executeUpdate();
            System.out.println("Physician saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving physician.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }
    }

    @Override
    public void updatePhysician(Physician physician) {
        String sql = "UPDATE Physician SET firstName=?, lastName=?, specialty=?, phone=?, email=? WHERE physicianId=?";

        try (Connection conn = connectionManager.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed. Physician was not updated.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, physician.getFirstName());
            stmt.setString(2, physician.getLastName());
            stmt.setString(3, physician.getSpecialty());
            stmt.setString(4, physician.getPhone());
            stmt.setString(5, physician.getEmail());
            stmt.setInt(6, physician.getPhysicianId());

            stmt.executeUpdate();
            System.out.println("Physician updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating physician.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }
    }

    @Override
    public void deletePhysician(int physicianId) {
        String sql = "DELETE FROM Physician WHERE physicianId=?";

        try (Connection conn = connectionManager.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed. Physician was not deleted.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, physicianId);

            stmt.executeUpdate();
            System.out.println("Physician deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting physician.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }
    }

    @Override
    public Physician getPhysicianById(int physicianId) {
        String sql = "SELECT * FROM Physician WHERE physicianId=?";
        Physician physician = null;

        try (Connection conn = connectionManager.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed.");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, physicianId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                physician = new Physician(
                        rs.getInt("physicianId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialty"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding physician by ID.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }

        return physician;
    }

    @Override
    public List<Physician> getAllPhysicians() {
        String sql = "SELECT * FROM Physician";
        List<Physician> physicians = new ArrayList<>();

        try (Connection conn = connectionManager.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed.");
                return physicians;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Physician physician = new Physician(
                        rs.getInt("physicianId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialty"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                physicians.add(physician);
            }

        } catch (SQLException e) {
            System.out.println("Error finding all physicians.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }

        return physicians;
    }
}
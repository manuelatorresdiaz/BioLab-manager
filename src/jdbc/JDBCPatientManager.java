package jdbc;

import interfaces.PatientManager;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPatientManager implements PatientManager {

    private ConnectionManager dbConnection;

    public JDBCPatientManager(ConnectionManager dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (firstName, lastName, dateOfBirth, gender, phone, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed. Patient was not saved.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getDateOfBirth());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getAddress());

            stmt.executeUpdate();
            System.out.println("Patient saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving patient.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET firstName=?, lastName=?, dateOfBirth=?, gender=?, phone=?, address=? WHERE patientId=?";

        try (Connection conn = dbConnection.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed. Patient was not updated.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getDateOfBirth());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getAddress());
            stmt.setInt(7, patient.getPatientId());

            stmt.executeUpdate();
            System.out.println("Patient updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating patient.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }
    }

    @Override
    public void deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patientId=?";

        try (Connection conn = dbConnection.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed. Patient was not deleted.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);

            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting patient.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }
    }

    @Override
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM Patient WHERE patientId=?";
        Patient patient = null;

        try (Connection conn = dbConnection.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed.");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding patient by ID.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }

        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM Patient";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = dbConnection.connect()) {
            if (conn == null) {
                System.out.println("Database connection failed.");
                return patients;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.out.println("Error finding all patients.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }

        return patients;
    }
}
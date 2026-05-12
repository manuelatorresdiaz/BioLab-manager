package jdbc;

import interfaces.PatientManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bioLabPOJOS.Patient;
//FOR BLOB
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.FileInputStream; 
/**
 * JDBC implementation for Patient records.
 * Features: Standard CRUD and BLOB (Binary Large Object) management for profile images.
 */
public class JDBCPatientManager implements PatientManager {

    private ConnectionManager connectionManager;

    public JDBCPatientManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (firstName, lastName, dateOfBirth, gender, phone, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionManager.connect()) {
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
            connectionManager.disconnect();
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try (Connection conn = connectionManager.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
                patients.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving patients.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }

        return patients;
    }

    @Override
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM Patient WHERE patientId=?";
        Patient patient = null;

        try (Connection conn = connectionManager.connect()) {
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
            System.out.println("Error finding patient.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }

        return patient;
    }
    @Override
    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET firstName=?, lastName=?, dateOfBirth=?, gender=?, phone=?, address=? WHERE patientId=?";

        try (Connection conn = connectionManager.connect()) {
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
            connectionManager.disconnect();
        }
    }

    @Override
    public void deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patientId=?";

        try (Connection conn = connectionManager.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);

            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting patient.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }
    }
 // --- BLOB HANDLING ---

    /**
     * Updates the patient's profile picture using a file stream.
     * Demonstrates how to store binary data (BLOB) in a relational database.
     */
    @Override
    public void updatePatientImage(int patientId, String imagePath) {
        String sql = "UPDATE Patient SET profile_image = ? WHERE patientId = ?";

        try (Connection conn = connectionManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             FileInputStream fis = new FileInputStream(imagePath)) {

            stmt.setBinaryStream(1, fis);
            stmt.setInt(2, patientId);

            stmt.executeUpdate();
            System.out.println("Patient image saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving patient image.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }
    }
    
    @Override
    public List<Patient> findPatientsByLastName(String lastName) {

        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient WHERE lastName=?";

        try (Connection conn = connectionManager.connect()) {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lastName);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Patient p = new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address")
                );

                patients.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error searching patients by last name.");
            e.printStackTrace();
        } finally {
            connectionManager.disconnect();
        }

        return patients;
    }
 // Additional CRUD methods (getAll, getById, update, delete) follow the established pattern...
}
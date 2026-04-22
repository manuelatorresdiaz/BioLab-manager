package repository;

import model.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryDB implements PatientRepository {

    private DBConnection dbConnection;

    public PatientRepositoryDB(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void save(Patient patient) {
    	String sql = "INSERT INTO Patient (firstName, lastName, dateOfBirth, gender, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        

        Connection conn = dbConnection.connect();
        
        if (conn == null) {
            System.out.println("Database connection failed. Patient was not saved.");
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
    public void update(Patient patient) {
        String sql = "UPDATE Patient SET firstName = ?, lastName = ?, dateOfBirth = ?, gender = ?, phone = ?, address = ? WHERE patientId = ?";

        Connection conn = dbConnection.connect();
        

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
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
    public void delete(int patientId) {
        String sql = "DELETE FROM Patient WHERE patientId = ?";

        Connection conn = dbConnection.connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
    public Patient findById(int patientId) {
        String sql = "SELECT * FROM Patient WHERE patientId = ?";
        Connection conn = dbConnection.connect();
        

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Patient(
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

        return null;
    }

    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM Patient";
        List<Patient> patients = new ArrayList<>();
        Connection conn = dbConnection.connect();
        

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient(
                		rs.getInt("patientId")
                		rs.getString("firstName")
                		rs.getString("lastName")
                		rs.getString("dateOfBirth")
                		rs.getString("gender")
                		rs.getString("phone")
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
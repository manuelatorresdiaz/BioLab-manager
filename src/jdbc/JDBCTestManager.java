package jdbc;

import interfaces.TestManager;
import bioLabPOJOS.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestManager implements TestManager {

    private ConnectionManager cm;

    public JDBCTestManager(ConnectionManager cm) {
        this.cm = cm;
    }

    @Override
    public void addTest(Test test) {
        // Inserts a new test into the database
        String sql = "INSERT INTO Test (testName, unit, description) VALUES (?, ?, ?)";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, test.getTestName());
            stmt.setString(2, test.getUnit());
            stmt.setString(3, test.getDescription());

            stmt.executeUpdate();
            System.out.println("Test saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving test.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void updateTest(Test test) {
        // Updates the information of an existing test
        String sql = "UPDATE Test SET testName=?, unit=?, description=? WHERE testId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, test.getTestName());
            stmt.setString(2, test.getUnit());
            stmt.setString(3, test.getDescription());
            stmt.setInt(4, test.getTestId());

            stmt.executeUpdate();
            System.out.println("Test updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating test.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void deleteTest(int testId) {
        // Deletes a test using its ID
        String sql = "DELETE FROM Test WHERE testId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, testId);

            stmt.executeUpdate();
            System.out.println("Test deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting test.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public Test getTestById(int testId) {
        // Finds one test by ID
        String sql = "SELECT * FROM Test WHERE testId=?";
        Test test = null;

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, testId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                test = new Test(
                        rs.getInt("testId"),
                        rs.getString("testName"),
                        rs.getString("unit"),
                        rs.getString("description")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding test.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return test;
    }

    @Override
    public List<Test> getAllTests() {
        // Retrieves all tests from the database
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM Test";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Test test = new Test(
                        rs.getInt("testId"),
                        rs.getString("testName"),
                        rs.getString("unit"),
                        rs.getString("description")
                );
                tests.add(test);
            }

        } catch (SQLException e) {
            System.out.println("Error getting tests.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return tests;
    }
}

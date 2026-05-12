package jdbc;

import interfaces.ReferenceRangeManager;
import bioLabPOJOS.ReferenceRange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * JDBC Implementation for clinical reference thresholds.
 * Manages normal and critical boundaries used to interpret patient results.
 */
public class JDBCReferenceRangeManager implements ReferenceRangeManager {

    private ConnectionManager cm;

    public JDBCReferenceRangeManager(ConnectionManager cm) {
        this.cm = cm;
    }

    @Override
    public void addReferenceRange(ReferenceRange referenceRange) {
    	// Defines the safety boundaries for a specific laboratory test
    	String sql = "INSERT INTO ReferenceRange (minValue, maxValue, criticalMin, criticalMax, units) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, referenceRange.getMinValue());
            stmt.setDouble(2, referenceRange.getMaxValue());
            stmt.setDouble(3, referenceRange.getCriticalMin());
            stmt.setDouble(4, referenceRange.getCriticalMax());
            stmt.setString(5, referenceRange.getUnits());

            stmt.executeUpdate();
            System.out.println("Reference range saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving reference range.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void updateReferenceRange(ReferenceRange referenceRange) {
        // Updates an existing reference range
        String sql = "UPDATE ReferenceRange SET minValue=?, maxValue=?, criticalMin=?, criticalMax=?, units=? WHERE referenceId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, referenceRange.getMinValue());
            stmt.setDouble(2, referenceRange.getMaxValue());
            stmt.setDouble(3, referenceRange.getCriticalMin());
            stmt.setDouble(4, referenceRange.getCriticalMax());
            stmt.setString(5, referenceRange.getUnits());
            stmt.setInt(6, referenceRange.getReferenceId());

            stmt.executeUpdate();
            System.out.println("Reference range updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating reference range.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public void deleteReferenceRange(int referenceId) {
        // Deletes a reference range by its ID
        String sql = "DELETE FROM ReferenceRange WHERE referenceId=?";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, referenceId);

            stmt.executeUpdate();
            System.out.println("Reference range deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting reference range.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }
    }

    @Override
    public ReferenceRange getReferenceRangeById(int referenceId) {
        // Finds one reference range by ID
        String sql = "SELECT * FROM ReferenceRange WHERE referenceId=?";
        ReferenceRange referenceRange = null;

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, referenceId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                referenceRange = new ReferenceRange(
                        rs.getInt("referenceId"),
                        rs.getDouble("minValue"),
                        rs.getDouble("maxValue"),
                        rs.getDouble("criticalMin"),
                        rs.getDouble("criticalMax"),
                        rs.getString("units")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding reference range.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return referenceRange;
    }

    @Override
    public List<ReferenceRange> getAllReferenceRanges() {
        // Retrieves all reference ranges
        List<ReferenceRange> referenceRanges = new ArrayList<>();
        String sql = "SELECT * FROM ReferenceRange";

        try (Connection conn = cm.connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ReferenceRange referenceRange = new ReferenceRange(
                        rs.getInt("referenceId"),
                        rs.getDouble("minValue"),
                        rs.getDouble("maxValue"),
                        rs.getDouble("criticalMin"),
                        rs.getDouble("criticalMax"),
                        rs.getString("units")
                );

                referenceRanges.add(referenceRange);
            }

        } catch (SQLException e) {
            System.out.println("Error getting reference ranges.");
            e.printStackTrace();
        } finally {
            cm.disconnect();
        }

        return referenceRanges;
    }
 // updateReferenceRange, deleteReferenceRange, and getAll follow the standard JDBC pattern...
}
package interfaces;

import java.util.List;
import bioLabPOJOS.ReferenceRange;
/**
 * Interface defining the management of clinical thresholds.
 * These ranges act as the diagnostic standards used to validate 
 * and interpret laboratory test results.
 */
public interface ReferenceRangeManager {
	/**
     * Registers a new clinical threshold set (healthy and critical bounds).
     * @param referenceRange The entity containing numerical limits and units.
     */
    void addReferenceRange(ReferenceRange referenceRange);
    /**
     * Updates an existing range configuration.
     * @param referenceRange The range entity with modified thresholds.
     */
    void updateReferenceRange(ReferenceRange referenceRange);
    /**
     * Removes a clinical range definition from the system.
     * @param referenceId Unique identifier of the range.
     */
    void deleteReferenceRange(int referenceId);
    /**
     * Retrieves specific threshold data by its unique identifier.
     * @param referenceId Unique identifier.
     * @return The ReferenceRange object or null if not found.
     */
    ReferenceRange getReferenceRangeById(int referenceId);
    /**
     * Lists all clinical reference ranges stored in the system.
     * @return A list of all available ReferenceRanges.
     */
    List<ReferenceRange> getAllReferenceRanges();
}
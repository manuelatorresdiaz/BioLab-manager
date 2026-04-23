package interfaces;

import java.util.List;
import bioLabPOJOS.ReferenceRange;

public interface ReferenceRangeManager {

    // Adds a new reference range
    void addReferenceRange(ReferenceRange referenceRange);

    // Updates an existing reference range
    void updateReferenceRange(ReferenceRange referenceRange);

    // Deletes a reference range by ID
    void deleteReferenceRange(int referenceId);

    // Finds one reference range by ID
    ReferenceRange getReferenceRangeById(int referenceId);

    // Returns all reference ranges
    List<ReferenceRange> getAllReferenceRanges();
}
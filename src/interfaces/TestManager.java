package interfaces;

import java.util.List;
import bioLabPOJOS.Test;
/**
 * Interface defining the management of the Laboratory Test Catalog.
 * It serves as the primary registry for all types of clinical analyses 
 * the laboratory is equipped to perform.
 */
public interface TestManager {
	/**
     * Registers a new type of analysis in the laboratory catalog.
     * @param test The Test entity containing the name, unit, and description.
     */
    void addTest(Test test);
    /**
     * Modifies the metadata of an existing test definition.
     * @param test The Test object with updated catalog information.
     */
    void updateTest(Test test);
    /**
     * Removes a test definition from the catalog.
     * @param testId Unique identifier of the test.
     */
    void deleteTest(int testId);
    /**
     * Retrieves a specific test definition using its unique ID.
     * @param testId Unique identifier.
     * @return The Test object if found, otherwise null.
     */
    Test getTestById(int testId);
    /**
     * Returns a complete list of all tests currently supported by the lab.
     * @return A list of all Test entities in the catalog.
     */
    List<Test> getAllTests();
}

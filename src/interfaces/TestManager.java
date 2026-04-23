package interfaces;

import java.util.List;
import bioLabPOJOS.Test;

public interface TestManager {

    // Adds a new laboratory test
    void addTest(Test test);

    // Updates an existing laboratory test
    void updateTest(Test test);

    // Deletes a laboratory test by ID
    void deleteTest(int testId);

    // Finds one test by its ID
    Test getTestById(int testId);

    // Returns all tests
    List<Test> getAllTests();
}

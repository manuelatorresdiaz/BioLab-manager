package bioLabPOJOS;

/**
 * Represents a laboratory test definition within the BioLab system.
 * This entity acts as a catalog item for all medical examinations 
 * available in the laboratory.
 */

public class Test {
    private int testId;
    private String testName;
    private String unit;
    private String description;

    /**
     * Default constructor required for framework compatibility and 
     * dynamic instantiation.
     */
    
    public Test() {}

    /**
     * Parameterized constructor to initialize a test definition.
     * 
     * @param testId      Unique identifier for the test.
     * @param testName    The formal name of the clinical test (e.g., Blood Glucose).
     * @param unit        The measurement unit used for results (e.g., mg/dL, mmol/L).
     * @param description A brief explanation of the test's clinical purpose.
     */
    
    public Test(int testId, String testName, String unit, String description) {
        this.testId = testId;
        this.testName = testName;
        this.unit = unit;
        this.description = description;
    }

 // Getters and Setters for test attributes
    
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
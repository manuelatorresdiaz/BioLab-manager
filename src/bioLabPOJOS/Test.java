package bioLabPOJOS;

public class Test {
    private int testId;
    private String testName;
    private String unit;
    private String description;

    public Test() {}

    public Test(int testId, String testName, String unit, String description) {
        this.testId = testId;
        this.testName = testName;
        this.unit = unit;
        this.description = description;
    }

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
package bioLabPOJOS;

/**
 * Represents the clinical reference ranges for laboratory tests.
 * This class defines the boundaries for normal results and 
 * triggers alerts for critical values.
 */

public class ReferenceRange {
    private int referenceId;
    private double minValue;
    private double maxValue;
    private double criticalMin;
    private double criticalMax;
    private String units;

    /**
     * Default constructor for framework compatibility.
     */
    
    public ReferenceRange() {}

    /**
     * Parameterized constructor to define a complete reference range.
     * 
     * @param referenceId Unique ID for the reference setting.
     * @param minValue    The lower bound of the healthy/normal range.
     * @param maxValue    The upper bound of the healthy/normal range.
     * @param criticalMin The threshold below which a result is considered life-threatening.
     * @param criticalMax The threshold above which a result is considered life-threatening.
     * @param units       The measurement units (e.g., mg/dL, g/L).
     */
    
    public ReferenceRange(int referenceId, double minValue, double maxValue,
                          double criticalMin, double criticalMax, String units) {
        this.referenceId = referenceId;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.criticalMin = criticalMin;
        this.criticalMax = criticalMax;
        this.units = units;
    }

 // Getters and Setters
    
    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getCriticalMin() {
        return criticalMin;
    }

    public void setCriticalMin(double criticalMin) {
        this.criticalMin = criticalMin;
    }

    public double getCriticalMax() {
        return criticalMax;
    }

    public void setCriticalMax(double criticalMax) {
        this.criticalMax = criticalMax;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
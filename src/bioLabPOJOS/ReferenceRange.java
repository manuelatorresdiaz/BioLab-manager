package bioLabPOJOS;

public class ReferenceRange {
    private int referenceId;
    private double minValue;
    private double maxValue;
    private double criticalMin;
    private double criticalMax;
    private String units;

    public ReferenceRange() {}

    public ReferenceRange(int referenceId, double minValue, double maxValue,
                          double criticalMin, double criticalMax, String units) {
        this.referenceId = referenceId;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.criticalMin = criticalMin;
        this.criticalMax = criticalMax;
        this.units = units;
    }

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
package bioLabPOJOS;

import java.util.Date;

public class Sample {
    private int sampleId;
    private String sampleType;
    private Date collectionDate;
    private String status;

    public Sample() {}

    public Sample(int sampleId, String sampleType, Date collectionDate, String status) {
        this.sampleId = sampleId;
        this.sampleType = sampleType;
        this.collectionDate = collectionDate;
        this.status = status;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "sampleId=" + sampleId +
                ", sampleType='" + sampleType + '\'' +
                ", collectionDate=" + collectionDate +
                ", status='" + status + '\'' +
                '}';
    }
}
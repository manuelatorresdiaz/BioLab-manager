package bioLabPOJOS;

import java.util.Date;

public class Report {
    private int reportId;
    private String reportType;
    private Date generatedDate;

    public Report() {}

    public Report(int reportId, String reportType, Date generatedDate) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.generatedDate = generatedDate;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportType='" + reportType + '\'' +
                ", generatedDate=" + generatedDate +
                '}';
    }
}
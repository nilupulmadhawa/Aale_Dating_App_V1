package com.example.aale.model;

public class Report {
    private String report_id;
    private String receiver;
    private String author;
    private String report_type;
    private String reported_date;
    private String reportEd_time;

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getReported_date() {
        return reported_date;
    }

    public void setReported_date(String reported_date) {
        this.reported_date = reported_date;
    }

    public String getReportEd_time() {
        return reportEd_time;
    }

    public void setReportEd_time(String reportEd_time) {
        this.reportEd_time = reportEd_time;
    }

    public Report() {
    }
}

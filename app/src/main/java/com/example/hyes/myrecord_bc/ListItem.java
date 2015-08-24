package com.example.hyes.myrecord_bc;

/**
 * Created by hyes on 2015. 4. 16..
 */
public class ListItem {
    private String name, date;
    private String recordFile1, recordFile2, recordFile3, recordFile4;
    private String captureFile1, captureFile2, captureFile3, captureFile4;


    public ListItem() {
    }

    public ListItem(String name, String date, String recordFile1, String recordFile2, String recordFile3, String recordFile4, String captureFile1, String captureFile2, String captureFile3, String captureFile4) {
        this.name = name;
        this.date = date;
        this.recordFile1 = recordFile1;
        this.recordFile2 = recordFile2;
        this.recordFile3 = recordFile3;
        this.recordFile4 = recordFile4;
        this.captureFile1 = captureFile1;
        this.captureFile2 = captureFile2;
        this.captureFile3 = captureFile3;
        this.captureFile4 = captureFile4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecordFile1() {
        return recordFile1;
    }

    public void setRecordFile1(String recordFile1) {
        this.recordFile1 = recordFile1;
    }

    public String getRecordFile2() {
        return recordFile2;
    }

    public void setRecordFile2(String recordFile2) {
        this.recordFile2 = recordFile2;
    }

    public String getRecordFile3() {
        return recordFile3;
    }

    public void setRecordFile3(String recordFile3) {
        this.recordFile3 = recordFile3;
    }

    public String getRecordFile4() {
        return recordFile4;
    }

    public void setRecordFile4(String recordFile4) {
        this.recordFile4 = recordFile4;
    }

    public String getCaptureFile1() {
        return captureFile1;
    }

    public void setCaptureFile1(String captureFile1) {
        this.captureFile1 = captureFile1;
    }

    public String getCaptureFile2() {
        return captureFile2;
    }

    public void setCaptureFile2(String captureFile2) {
        this.captureFile2 = captureFile2;
    }

    public String getCaptureFile3() {
        return captureFile3;
    }

    public void setCaptureFile3(String captureFile3) {
        this.captureFile3 = captureFile3;
    }

    public String getCaptureFile4() {
        return captureFile4;
    }

    public void setCaptureFile4(String captureFile4) {
        this.captureFile4 = captureFile4;
    }
}

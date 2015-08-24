package com.example.hyes.myrecord_bc;

/**
 * Created by hyes on 2015. 4. 24..
 */
public class RecordItem {
    private String name, date, email;
    private int age;

    private String recordFile1, recordFile2, recordFile3, recordFile4, recordFile5, recordFile6 ;
    private String captureFile1, captureFile2, captureFile3, captureFile4, captureFile5, captureFile6;
    private String pos1, pos2, pos3, pos4, pos5, pos6;

    public RecordItem(String name, String date, int age, String email, String recordFile1, String captureFile1, String pos1, String recordFile2, String captureFile2, String pos2, String recordFile3, String captureFile3, String pos3, String recordFile4, String captureFile4, String pos4, String recordFile5, String captureFile5, String pos5, String recordFile6, String captureFile6, String pos6) {
        this.name = name;
        this.date = date;
        this.age = age;
        this.email = email;
        this.recordFile1 = recordFile1;
        this.captureFile1 = captureFile1;
        this.pos1 = pos1;
        this.recordFile2 = recordFile2;
        this.captureFile2 = captureFile2;
        this.pos2 = pos2;
        this.recordFile3 = recordFile3;
        this.captureFile3 = captureFile3;
        this.pos3 = pos3;
        this.recordFile4 = recordFile4;
        this.captureFile4 = captureFile4;
        this.pos4 = pos4;
        this.recordFile5 = recordFile5;
        this.captureFile5 = captureFile5;
        this.pos5 = pos5;
        this.recordFile6 = recordFile6;
        this.captureFile6 = captureFile6;
        this.pos6 = pos6;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getRecordFile1() {
        return recordFile1;
    }

    public String getRecordFile2() {
        return recordFile2;
    }

    public String getRecordFile3() {
        return recordFile3;
    }

    public String getRecordFile4() {
        return recordFile4;
    }

    public String getCaptureFile1() {
        return captureFile1;
    }

    public String getCaptureFile2() {
        return captureFile2;
    }

    public String getCaptureFile3() {
        return captureFile3;
    }

    public String getCaptureFile4() {
        return captureFile4;
    }

    public String getRecordFile5() {
        return recordFile5;
    }

    public String getRecordFile6() {
        return recordFile6;
    }

    public String getCaptureFile5() {
        return captureFile5;
    }

    public String getCaptureFile6() {
        return captureFile6;
    }

    public String getPos1() {
        return pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public String getPos3() {
        return pos3;
    }

    public String getPos4() {
        return pos4;
    }

    public String getPos5() {
        return pos5;
    }

    public String getPos6() {
        return pos6;
    }
}
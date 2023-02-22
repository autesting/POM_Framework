package com.appinventive.qa.util;

public class CID {
    private String EID;
    private String TCID;
    private String CustTCID;
    private String StepID;
    private String testType;

    public CID(String eid) {
        this.EID = eid;
    }

    public String getEID() {
        return this.EID;
    }

    public String getTCID() {
        return this.TCID;
    }

    public void setTCID(String TCID) {
        this.TCID = TCID;
    }

    public String getCustTCID() {
        return this.CustTCID;
    }

    public void setCustTCID(String custTCID) {
        this.CustTCID = custTCID;
    }

    public String getStepID() {
        return this.StepID;
    }

    public void setStepID(String stepID) {
        this.StepID = stepID;
    }

    public String getTestType() {
        return this.testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String toString() {
        return "\"CID\": {\"EID\":\"" + this.EID + '"' + ", \"TCID\":\"" + this.TCID + '"' + ", \"TestType\":\"" + this.testType + '"' + ", \"CustTCID\":\"" + this.CustTCID + '"' + ", \"StepID\":\"" + this.StepID + '"' + '}';
    }
}

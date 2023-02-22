//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.appinventive.qa.report;

import com.appinventive.qa.util.DateUtils;
//import com.mc.fact.util.DateUtils;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TestCase {
    private String author;
    private String testCaseID;
    private String testCaseDesc;
    private String AdoId;
    private Date startTime;
    private Date endTime;
    private String executionTime;
    private String status = "Not Executed";
    private List<TestStep> testSteps = new ArrayList();

    private TestCase() {
    }

    public TestCase(String author, String testCaseID, String testCaseDesc, Date startTime, String AdoId) {
        this.author = author;
        this.testCaseID = testCaseID;
        this.testCaseDesc = testCaseDesc;
        this.startTime = startTime;
        this.AdoId = AdoId;
    }

    public String getExecutionTime() {
        return this.executionTime;
    }

    public void calculateExecutionTime(Date endTime) {
        this.endTime = endTime;
        this.executionTime = DateUtils.timeDiff(this.startTime, this.endTime);
    }

    public void addStep(TestStep testStep) {
        this.testSteps.add(testStep);
    }

    public String getAuthor() {
        return this.author;
    }

    public String getAdoId() {
        return this.AdoId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTestCaseID() {
        return this.testCaseID;
    }

    public void setTestCaseID(String testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getTestCaseDesc() {
        return this.testCaseDesc;
    }

    public void setTestCaseDesc(String testCaseDesc) {
        this.testCaseDesc = testCaseDesc;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus() {
        this.status = this.verifyTestCaseResult(this.testSteps);
    }

    private String verifyTestCaseResult(List<TestStep> testSteps) {
        Iterator var2 = testSteps.iterator();

        TestStep teststep;
        do {
            if (!var2.hasNext()) {
                return "Pass";
            }

            teststep = (TestStep)var2.next();
        } while(teststep.getStatus().equalsIgnoreCase("Pass"));

        return "Fail";
    }

    public List<TestStep> getTestSteps() {
        return this.testSteps;
    }

    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }

    public String toString() {
        return "{\"testCaseID\": \"" + this.testCaseID + '"' + ", \"author\": \"" + this.author + '"' + ", \"testCaseDesc\": \"" + this.testCaseDesc + '"' + ", \"ADOId\": \"" + this.AdoId + '"' + ", \"startTime\": \"" + this.startTime + '"' + ", \"endTime\": \"" + this.endTime + '"' + ", \"executionTime\": \"" + this.executionTime + '"' + ", \"status\": \"" + this.status + '"' + ", \"testSteps\": " + this.testSteps.toString() + '}';
    }

//    public List<DBObject> testStepsDbObjectList() {
//        List<DBObject> testStepsDbObject = new ArrayList();
//        Iterator var2 = this.testSteps.iterator();
//
//        while(var2.hasNext()) {
//            TestStep testStep = (TestStep)var2.next();
//            testStepsDbObject.add(testStep.toDbObject());
//        }
//
//        return testStepsDbObject;
//    }
//
//    public BasicDBObject toDbObject() {
//        BasicDBObject document = new BasicDBObject();
//        document.put("testCaseID", this.testCaseID);
//        document.put("author", this.author);
//        document.put("testCaseDesc", this.testCaseDesc);
//        document.put("startTime", this.startTime);
//        document.put("endTime", this.endTime);
//        document.put("executionTime", this.executionTime);
//        document.put("status", this.status);
//        document.put("testSteps", this.testStepsDbObjectList());
//        return document;
//    }
}

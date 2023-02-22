//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.appinventive.qa.report;

//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
import java.util.Date;

public class TestStep {
    private String stepID;
    private Date endTime;
    private String description;
    private String status;
    private String message;
    private String screenshot;

    public TestStep(Date endTime, String description, String status, String message, String... screenshot) {
        this.endTime = endTime;
        this.description = description;
        this.status = status;
        this.message = message;
        this.screenshot = screenshot.length == 0 ? "" : screenshot[0];
    }

    public String getStepID() {
        return this.stepID;
    }

    public void setStepID(String stepID) {
        this.stepID = stepID;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScreenshot() {
        return this.screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "{\"stepID\": \"" + this.stepID + '"' + ", \"description\": \"" + this.description + '"' + ", \"message\": \"" + this.message + '"' + ", \"endTime\": \"" + this.endTime + '"' + ", \"status\": \"" + this.status + '"' + ", \"screenshot\": \"" + this.screenshot + '"' + '}';
    }

//    public DBObject toDbObject() {
//        BasicDBObject document = new BasicDBObject();
//        document.put("stepID", this.stepID);
//        document.put("description", this.description);
//        document.put("message", this.message);
//        document.put("endTime", this.endTime);
//        document.put("status", this.status);
//        document.put("screenshot", this.screenshot);
//        return document;
//    }
}

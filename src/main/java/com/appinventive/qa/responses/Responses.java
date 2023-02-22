package com.appinventive.qa.responses;


//import com.appinventive.qa.log4j.LogManager;
//import com.appinventive.qa.log4j.Logger;
import com.appinventive.qa.report.ExecutionDetails;
import com.appinventive.qa.report.TestCase;
import com.appinventive.qa.report.TestStep;
import com.appinventive.qa.util.ClientUtils;
import com.appinventive.qa.util.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Responses {
    private ThreadLocal<TestCase> currentTestCase = new ThreadLocal();
    private ExecutionDetails executionDetails;
    private static final Logger LOGGER = LogManager.getLogger(""); //todo

    private Responses() {
    }

    public Responses(String team, String testPlan, String testEnv, String testType, String adoProject) {
        LOGGER.debug(String.format("FACTReport Initiated with team - %s; testPlan - %s; testEnv - %s; testType - %s; ADOProject - %s", team, testPlan, testEnv, testType, adoProject));
        this.executionDetails = new ExecutionDetails(ClientUtils.getInstance().getCID().getEID(), team, testPlan, DateUtils.getCurrentDate(), testEnv, testType, adoProject);
    }

    public ExecutionDetails getExecutionDetails() {
        return this.executionDetails;
    }

    public String getCID() {
        return this.executionDetails.getExecutionID();
    }

    /** @deprecated */
    @Deprecated
    public void startTest(String author, String TCID, String description) {
        this.startTest(author, TCID, description, "ADOid");
    }

    public void startTest(String author, String TCID, String description, String ADOId) {
        LOGGER.debug(String.format("FACTReport -> starting test with author - %s; TCID - %s; description - %s", author, TCID, description));
        this.currentTestCase.set(new TestCase(author, TCID, description, DateUtils.getCurrentDate(), ADOId));
    }

    public void reportStep(String description, String status, String message, String... screenshot) {
        ((TestCase)this.currentTestCase.get()).addStep(new TestStep(DateUtils.getCurrentDate(), description, status, message, screenshot));
        int numTestSteps = ((TestCase)this.currentTestCase.get()).getTestSteps().size();
        LOGGER.debug(String.format("FACTReport -> reporting step with stepID - %s; description - %s; status - %s; message - %s", numTestSteps, description, status, message));
        ((TestStep)((TestCase)this.currentTestCase.get()).getTestSteps().get(numTestSteps - 1)).setStepID(String.valueOf(numTestSteps));
    }

    public void endTest() {
        LOGGER.debug("FACTReport -> ending test");
        ((TestCase)this.currentTestCase.get()).setStatus();
        ((TestCase)this.currentTestCase.get()).calculateExecutionTime(DateUtils.getCurrentDate());
        this.executionDetails.addTestCase((TestCase)this.currentTestCase.get());
    }

//    public void endReport() {
//        LOGGER.debug("FactReport -> Ending Report");
//        this.executionDetails.calculateExecutionTime(DateUtils.getCurrentDate());
//        this.executionDetails.calculateNumberOfTCs();
//        this.executionDetails.setSprint(FACTADO.getSprintNameFromADO(this.executionDetails.getAdoTeam()));
//        LOGGER.debug("ExecutionDetails: " + this.executionDetails.toString());
//        this.sendReportToFile();
//    }

//    public Document getDocument() {
//        return this.executionDetails.toDocument();
//    }

    private void sendReportToFile() {
        Path path = Paths.get("report.json");

        try {
            BufferedWriter writer = Files.newBufferedWriter(path);

            try {
                writer.write(this.executionDetails.toString());
                LOGGER.debug("Report successful");
            } catch (Throwable var6) {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (writer != null) {
                writer.close();
            }
        } catch (IOException var7) {
            LOGGER.error("Error in creating report", var7);
        }

    }

//    public void sendReportToMongoDB(String teamName) {
//        FACTMongoDB.sendData(this.getDocument(), teamName);
//    }

    public void assertEquals(String actual, String expected, String message, String... screenshot) {
        try {
            LOGGER.debug(String.format("FACTAssert -> assertEquals -> actual: %s; expected: %s; message: %s", actual, expected, message));
            Assert.assertEquals(actual, expected, message);
            this.reportStep(String.format("Actual: %s; Expected: %s", actual, expected), "Pass", message, screenshot);
        } catch (AssertionError var6) {
            LOGGER.debug("Assertion has failed", var6);
            this.reportStep(String.format("Actual: %s; Expected: %s", actual, expected), "Fail", message, screenshot);
            Assert.assertEquals(actual, expected, message);
        }

    }

    public void assertEquals(int actual, int expected, String message, String... screenshot) {
        try {
            LOGGER.debug(String.format("FACTAssert -> assertEquals -> actual: %s; expected: %s; message: %s", actual, expected, message));
            Assert.assertEquals(actual, expected, message);
            this.reportStep(String.format("Actual: %s; Expected: %s", actual, expected), "Pass", message, screenshot);
        } catch (AssertionError var6) {
            LOGGER.debug("Assertion has failed", var6);
            this.reportStep(String.format("Actual: %s; Expected: %s", actual, expected), "Fail", message, screenshot);
            Assert.assertEquals(actual, expected, message);
        }

    }

    public void info(String description, String message, String... screenshot) {
        LOGGER.debug(String.format("FACTReport -> Description: %s - Message: %s", description, message));
        this.reportStep(description, "Pass", message, screenshot);
    }

    public void assertTrue(boolean condition, String message, String... screenshot) {
        try {
            LOGGER.debug(String.format("FACTAssert -> assertTrue -> condition: %s; message: %s", condition, message));
            Assert.assertTrue(condition, message);
            this.reportStep(String.format("Condition: %s", true), "Pass", message);
        } catch (AssertionError var5) {
            LOGGER.debug("Assertion has failed", var5);
            this.reportStep(String.format("Condition: %s", condition), "Fail", message, screenshot);
            Assert.assertTrue(condition, message);
        }

    }

    public void assertContains(String actual, String expected, String message, String... screenshot) {
        LOGGER.debug(String.format("FACTAssert -> assertContains -> Actual: %s; Contains Expected: %s; message: %s", actual, expected, message));
        this.assertTrue(actual.contains(expected), message, screenshot);
    }

    public void assertNotNull(String value, String message, String... screenshot) {
        try {
            LOGGER.debug(String.format("FACTAssert -> assertNotNull -> value: %s; message: %s", value, message));
            Assert.assertNotNull(value, message);
            this.reportStep(String.format("AssertNotNull: %s", value), "Pass", message, screenshot);
        } catch (AssertionError var5) {
            LOGGER.debug("Assertion has failed", var5);
            this.reportStep(String.format("AssertNotNull: %s", value), "Fail", message, screenshot);
            Assert.assertNotNull(value, message);
        }

    }

    public void assertFail(String message, String... screenshot) {
        LOGGER.debug(String.format("FACTAssert -> assetFail; message: %s", message));
        this.reportStep("AssertFail", "Fail", message, screenshot);
        Assert.fail("AssertFail");
    }

    public void assertNotEquals(int actual, int expected, String message, String... screenshot) {
        try {
            LOGGER.debug(String.format("FACTAssert -> assertNotEqual; actual: %s; expected :%s; message: %s", actual, expected, message));
            Assert.assertNotEquals(actual, expected, message);
            this.reportStep(String.format("AssertNotEquals: actual: %s; expected: %s; message: %s", actual, expected, message), "Pass", message, screenshot);
        } catch (AssertionError var6) {
            LOGGER.debug("FACTAssert -> assertNotEqualsAssertion has failed", var6);
            this.reportStep(String.format("AssertNotEquals: actual: %s; expected: %s; message: %s", actual, expected, message), "Fail", message, screenshot);
            Assert.assertNotEquals(actual, expected, message);
        }

    }
}

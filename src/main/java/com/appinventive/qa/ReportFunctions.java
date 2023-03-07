package com.appinventive.qa;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.transform.Transformer;
        import javax.xml.transform.TransformerFactory;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;

        import java.util.*;
        import java.text.*;

        import com.appinventive.qa.pages.DriverScript;
        import com.appinventive.qa.pages.UtilityFunctions;
        import com.appinventive.qa.testcases.Appinventive_User;
        import org.w3c.dom.Attr;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.NodeList;
        import java.io.File;
        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.xpath.XPath;
        import javax.xml.xpath.XPathConstants;
        import javax.xml.xpath.XPathFactory;
        import java.awt.AWTException;
        import java.awt.Rectangle;
        import java.awt.Robot;
        import java.awt.Toolkit;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.sql.SQLException;

        import javax.imageio.ImageIO;
        import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.StaleElementReferenceException;
        import org.openqa.selenium.TakesScreenshot;

public class ReportFunctions extends Setup {

    public static int tsID;
    public static int tcID;						//'This variable is for ID creation for test case
    public static int tstpID;					//'This variable is for ID creation for test step
    public static String dtPJStartTime;			//'This variable is for project start time creation
    public static String dtTSStartTime;			//'This variable is for test suite start time creation
    public static String dtTCStartTime;			//'This variable is for testcase start time creation
    public static String dtTstpStartTime;			//'This variable is for test step start time creation
    public static String dttstpInfoStartTime;		//'This variable is for test step info start time creation
    public static String dtPJEndTime;			//'This variable is for project start time creation
    public static String dtTSEndTime;			//'This variable is for test suite start time creation
    public static String dtTCEndTime;			//'This variable is for testcase start time creation
    public static String dtTstpEndTime;			//'This variable is for test step start time creation
    public static String dttstpInfoEndTime;		//'This variable is for test step info start time creation

    public static String xmlSavedLocation = null;			//'This variable is for storing the xml saved location path
    //static Document docXml=null;
    public static String strTestSuiteName;
    public static String strTestCaseName;
    public static String strTestStepName;
    public static String strStepType = null;
    public static String strStatusFlag = "fail";

    public static TransformerFactory transformerFactory = null;
    public static Transformer transformer = null;
    public static DOMSource source = null;
    public static StreamResult result = null;
    public static Document doc = null;
    public static DocumentBuilderFactory dbFactory = null;
    public static DocumentBuilder dBuilder = null;
    public String testStatusValue = null;
    public static String FinalStatus = null;
    public static String StrStepDescription = null;
    public static String strStepStatus = null;
    public static String ReportSuiteName;
    public static String ReportCaseName;
    public static String ReportStepName;
    public static int iteratorCnt = 0;
    Date dNow = null;
    String hmsTime = null;
    static DateFormat hms = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
    public static String screenshotFilePath = null;
    public static void main(String argv[]) {

        ReportFunctions objVal = new ReportFunctions();
        //objVal.LogRepoter(strStepStatus,strStepType,StrStepDescription);

    }

    public ReportFunctions() {
        //System.out.println("Map: "+hmap);
        //System.out.println("Map: "+CommonFunctions.hmap);
    }

    public static void LogRepoter(String strStepStatus1, String strStepType1, String StrStepDescription1) {

        if (hmap.containsKey(suitename)) {
            strTestSuiteName = hmap.get(suitename);
        }
        if (hmap.containsKey(Tcase)) {
            strTestCaseName = hmap.get(Tcase);
        }
        if (hmap.containsKey(Tstep)) {
            //strTestStepName = hmap.get(Tstep);
            strTestStepName = StrStepDescription1;

        }

        strStepType = strStepType1;
        //StrStepDescription = StrStepDescription1;
        StrStepDescription = hmap.get(Tstep);
        strStepStatus = strStepStatus1;
        xmlSavedLocation = savedlocation+"//XmlReport.xml";
        screenshotFilePath = savedlocation + "//img/screenshots//";
		// ReportSuiteName = strTestSuiteName;
		// ReportCaseName = strTestCaseName;
		// ReportStepName = strTestStepName;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.newDocument();
            File inputFile = new File(xmlSavedLocation);
            boolean exists = inputFile.exists();
            //System.out.println(exists);

            if (exists) {
                AppendRootNode(dbFactory, dBuilder);
            } else {
                CreateRootDirectory(doc, strTestSuiteName, strTestCaseName, strTestStepName);
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String AppendRootNode(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder) {
        try {
            String projExpression;
            String tsExpression;
            String tcExpression;
            String tstpExpression;
            dbFactory.setIgnoringComments(true);
            Document docXml = dBuilder.parse(new File(xmlSavedLocation));
            // System.out.println("hmapKeyName is " + hmap.get("StepName"));
            Element root = docXml.getDocumentElement();
            NodeList projects = docXml.getElementsByTagName("Project");
            // System.out.println("length is:"+projects.getLength());
            Element project = (Element) ((NodeList) projects).item(0);
            XPath xPath = XPathFactory.newInstance().newXPath();

            if (iteratorCnt == 0) {
                iteratorCnt = 1;
                projExpression = "//AutomationReportViewer/Project";
                tsExpression = "//AutomationReportViewer/Project/TestSuite[@Name='" + hmap.get(ReportSuiteName) + "'][@ID=" + tsID + "]";
                tcExpression = "//AutomationReportViewer/Project/TestSuite[@Name='" + hmap.get(ReportSuiteName) + "'][@ID=" + tsID + "]/TestCase[@Name='" + hmap.get(ReportCaseName) + "'][@ID=" + tcID + "]";
                tstpExpression = "//AutomationReportViewer/Project/TestSuite[@Name='" + hmap.get(ReportSuiteName) + "'][@ID=" + tsID + "]/TestCase[@Name='" + hmap.get(ReportCaseName) + "'][@ID=" + tcID + "]/TestStep[@Name='" + hmap.get(ReportStepName) + "'][@ID=" + tstpID + "]";
            } else {
                projExpression = "//AutomationReportViewer/Project";
                tsExpression = "//AutomationReportViewer/TestSuite[@Name='" + hmap.get(ReportSuiteName) + "'][@ID=" + tsID + "]";
                tcExpression = "//AutomationReportViewer/TestSuite[@Name='" + hmap.get(ReportSuiteName) + "'][@ID=" + tsID + "]/TestCase[@Name='" + hmap.get(ReportCaseName) + "'][@ID=" + tcID + "]";
                tstpExpression = "//AutomationReportViewer/TestSuite[@Name='" + hmap.get(ReportSuiteName) + "'][@ID=" + tsID + "]/TestCase[@Name='" + hmap.get(ReportCaseName) + "'][@ID=" + tcID + "]/TestStep[@Name='" + hmap.get(ReportStepName) + "'][@ID=" + tstpID + "]";
            }

            //System.out.println("TestStpeID is "+tstpID);
//				String projExpression = "//AutomationReportViewer/Project";
//				String tsExpression = "//AutomationReportViewer/Project/TestSuite[@Name='"+hmap.get(ReportSuiteName)+"'][@ID="+tsID+"]";
//				String tcExpression = "//AutomationReportViewer/Project/TestSuite[@Name='"+hmap.get(ReportSuiteName)+"'][@ID="+tsID+"]/TestCase[@Name='"+hmap.get(ReportCaseName)+"'][@ID="+tcID+"]";
//				String tstpExpression = "//AutomationReportViewer/Project/TestSuite[@Name='"+hmap.get(ReportSuiteName)+"'][@ID="+tsID+"]/TestCase[@Name='"+hmap.get(ReportCaseName)+"'][@ID="+tcID+"]/TestStep[@Name='"+hmap.get(ReportStepName)+"'][@ID="+tstpID+"]";
            NodeList prjNodes = (NodeList) xPath.evaluate(projExpression, docXml.getDocumentElement(), XPathConstants.NODESET);
            NodeList tsNodes = (NodeList) xPath.evaluate(tsExpression, docXml.getDocumentElement(), XPathConstants.NODESET);
            NodeList tcNodes = (NodeList) xPath.evaluate(tcExpression, docXml.getDocumentElement(), XPathConstants.NODESET);
            NodeList tstpNodes = (NodeList) xPath.evaluate(tstpExpression, docXml.getDocumentElement(), XPathConstants.NODESET);

            //  System.out.println(((NodeList)tsNodes).getLength());
            prjElement = (Element) ((NodeList) prjNodes).item(0);
            //System.out.println(prjElement);

            if (((tsNodes.getLength()) > 0) && ((hmap.get(ReportSuiteName)).equalsIgnoreCase(strTestSuiteName))) {
                prjElement = (Element) ((NodeList) prjNodes).item(0);
                //System.out.println(prjElement);

                //     System.out.println("TestSuite Node length " +tsNodes.getLength());
                if (((tcNodes.getLength()) > 0) && ((hmap.get(ReportCaseName)).equalsIgnoreCase(strTestCaseName))) {
                    tsElement = (Element) ((NodeList) tsNodes).item(0);
                    //    	 System.out.println(tsElement);
                    //	Element tcElement = null;
                    //         System.out.println("TestCase Node length " +tcNodes.getLength());
                    if (((tstpNodes.getLength()) == 1) && ((hmap.get(ReportStepName)).equalsIgnoreCase(strTestStepName))) {
                        tcElement = (Element) ((NodeList) tcNodes).item(0);
                        //	Element tstpElement = null;
                        tstpElement = (Element) ((NodeList) tstpNodes).item(0);
                        //	         System.out.println("TestStep Node length " +tstpNodes.getLength());
                        strStatusFlag = AppendTestStepLog(dbFactory, dBuilder, docXml, root, prjElement, tsElement, tcElement, tstpElement);
                    } else {
                        tcElement = (Element) ((NodeList) tcNodes).item(0);
                        strStatusFlag = AppendTestStep(dbFactory, dBuilder, docXml, root, prjElement, tsElement, tcElement, strTestStepName);
                    }
                } else {
                    tsElement = (Element) ((NodeList) tsNodes).item(0);
                    strStatusFlag = AppendTestCase(dbFactory, dBuilder, docXml, root, prjElement, tsElement, strTestCaseName, strTestStepName);
                }
            } else {
                prjElement = (Element) ((NodeList) prjNodes).item(0);
                strStatusFlag = AppendTestSuite(dbFactory, dBuilder, docXml, root, prjElement, strTestSuiteName, strTestCaseName, strTestStepName);
            }

            Date dnow = new Date();
            dtPJEndTime = hms.format(dnow);
            Attr prjEndTime = docXml.createAttribute("EndTime");
            prjEndTime.setValue(dtPJEndTime);
            project.setAttributeNode(prjEndTime);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                strStatusFlag = "PASS";
            } else {
                strStatusFlag = "FAIL";
            }
            Attr prjStatus = docXml.createAttribute("Status");
            prjStatus.setValue(strStatusFlag);
            project.setAttributeNode(prjStatus);
            Attr prjDuration = docXml.createAttribute("Duration");
            String sec = timeDifference(dtPJStartTime, dtPJEndTime);
            prjDuration.setValue(String.valueOf(sec));
            project.setAttributeNode(prjDuration);
            root.appendChild(project);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(docXml);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);
            return strStatusFlag;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String CreateRootDirectory(Document doc, String strTestSuiteName, String strTestCaseName, String strTestStepName) {

        try {

            Element rootElement = doc.createElement("AutomationReportViewer");
            doc.appendChild(rootElement);
            //  Project element creation
            Element project = doc.createElement("Project");
            Attr projName = doc.createAttribute("Name");
            projName.setValue("Selenium Framework");
            project.setAttributeNode(projName);
            Date dnow = new Date();
            dtPJStartTime = hms.format(dnow);
            Attr prjStrtTime = doc.createAttribute("StartTime");
            prjStrtTime.setValue(dtPJStartTime);
            project.setAttributeNode(prjStrtTime);
            strStatusFlag = CreateTestSuite(dbFactory, dBuilder, doc, rootElement, project, strTestSuiteName, strTestCaseName, strTestStepName);
            Date dnowe = new Date();
            dtPJEndTime = hms.format(dnowe);
            Attr prjEndTime = doc.createAttribute("EndTime");
            prjEndTime.setValue(dtPJEndTime);
            project.setAttributeNode(prjEndTime);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                strStatusFlag = "PASS";
            } else {
                strStatusFlag = "FAIL";
            }
            Attr prjStatus = doc.createAttribute("Status");
            prjStatus.setValue(strStatusFlag);
            project.setAttributeNode(prjStatus);
            String sec = timeDifference(dtPJStartTime, dtPJEndTime);
            Attr prjDuration = doc.createAttribute("Duration");
            prjDuration.setValue(String.valueOf(sec));
            project.setAttributeNode(prjDuration);
            rootElement.appendChild(project);

            //  EnvironmentDetails element creation
            Element environ = doc.createElement("EnvironmentDetails");
            Attr userName = doc.createAttribute("UserName");
            userName.setValue("Murali");
            environ.setAttributeNode(userName);
            Attr hstName = doc.createAttribute("HostName");
            hstName.setValue("Appinventiv");
            environ.setAttributeNode(hstName);
            Attr thrValue = doc.createAttribute("ThresholdValue");
            thrValue.setValue("90");
            environ.setAttributeNode(thrValue);
            Attr prURL = doc.createAttribute("URL");
            prURL.setValue(url);
            environ.setAttributeNode(prURL);
            Attr instanceNme = doc.createAttribute("InstanceName");
            instanceNme.setValue(environmentname);
            environ.setAttributeNode(instanceNme);
            project.appendChild(environ);
            rootElement.appendChild(project);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, consoleResult);
            return strStatusFlag;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String CreateTestSuite(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element project, String strTestSuiteName, String strTestCaseName, String strTestStepName) {

        try {
            if (hmap.containsKey(ReportSuiteName)) {

                if (hmap.get(ReportSuiteName).equals(strTestSuiteName)) {

                } else {
                    tsID++;
                    hmap.put(ReportSuiteName, strTestSuiteName);
                }
            } else {
                tsID = 1;
                if (ReportSuiteName == null) {
                    ReportSuiteName = "ReportSuiteName";
                }
                hmap.put(ReportSuiteName, strTestSuiteName);
            }
            //  TestSuite element creation
            Element testSuite = doc.createElement("TestSuite");
            Attr suiteName = doc.createAttribute("Name");
            suiteName.setValue(strTestSuiteName);
            testSuite.setAttributeNode(suiteName);
            Attr testSuiteID = doc.createAttribute("ID");
            testSuiteID.setValue(String.valueOf(tsID));
            testSuite.setAttributeNode(testSuiteID);
            Date dnow = new Date();
            dtTSStartTime = hms.format(dnow);
            Attr suiteStrtTime = doc.createAttribute("StartTime");
            suiteStrtTime.setValue(dtTSStartTime);
            testSuite.setAttributeNode(suiteStrtTime);
            strStatusFlag = CreateTestCase(dbFactory, dBuilder, doc, rootElement, project, testSuite, strTestCaseName, strTestStepName);
            Date dnowe = new Date();
            dtTSEndTime = hms.format(dnowe);
            Attr suiteEndTime = doc.createAttribute("EndTime");
            suiteEndTime.setValue(dtTSEndTime);
            testSuite.setAttributeNode(suiteEndTime);

            Attr suiteExpStatus = doc.createAttribute("ExpectedStatus");
            suiteExpStatus.setValue("PASS");
            testSuite.setAttributeNode(suiteExpStatus);
            Attr suiteActStatus = doc.createAttribute("ActualStatus");
            suiteActStatus.setValue(strStatusFlag.toUpperCase());
            testSuite.setAttributeNode(suiteActStatus);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
            }
            Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
            suiteFinalStatus.setValue(FinalStatus);
            testSuite.setAttributeNode(suiteFinalStatus);
            Attr suiteDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dtTSStartTime, dtTSEndTime);
            suiteDuration.setValue(String.valueOf(sec));
            testSuite.setAttributeNode(suiteDuration);
            project.appendChild(testSuite);
            rootElement.appendChild(project);
            //tsID=0;
            return FinalStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String CreateTestCase(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element project, Element testSuite, String strTestCaseName, String strTestStepName) {

        try {
            if (hmap.containsKey(ReportCaseName)) {

                if (hmap.get(ReportCaseName).equals(strTestCaseName)) {

                } else {
                    tcID++;
                    hmap.put(ReportCaseName, strTestCaseName);
                }
            } else {
                tcID = 1;
                if (ReportCaseName == null) {
                    ReportCaseName = "ReportCaseName";
                }
                hmap.put(ReportCaseName, strTestCaseName);
            }
            //  TestSuite element creation
            Element testCase = doc.createElement("TestCase");
            Attr caseName = doc.createAttribute("Name");
            caseName.setValue(strTestCaseName);
            testCase.setAttributeNode(caseName);
            Attr testCaseID = doc.createAttribute("ID");
            testCaseID.setValue(String.valueOf(tcID));
            testCase.setAttributeNode(testCaseID);
            Date dnow = new Date();
            dtTCStartTime = hms.format(dnow);
            Attr caseStrtTime = doc.createAttribute("StartTime");
            caseStrtTime.setValue(dtTCStartTime);
            testCase.setAttributeNode(caseStrtTime);
            strStatusFlag = CreateTestStep(dbFactory, dBuilder, doc, rootElement, testCase, strTestStepName);
            Date dnowe = new Date();
            dtTCEndTime = hms.format(dnowe);
            Attr caseEndTime = doc.createAttribute("EndTime");
            caseEndTime.setValue(dtTCEndTime);
            testCase.setAttributeNode(caseEndTime);
            Attr caseExpStatus = doc.createAttribute("ExpectedStatus");
            caseExpStatus.setValue("PASS");
            testCase.setAttributeNode(caseExpStatus);
            Attr caseActStatus = doc.createAttribute("ActualStatus");
            caseActStatus.setValue(strStatusFlag);
            testCase.setAttributeNode(caseActStatus);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
            }
            Attr caseFinalStatus = doc.createAttribute("FinalStatus");
            caseFinalStatus.setValue(FinalStatus);
            testCase.setAttributeNode(caseFinalStatus);
            Attr caseDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dtTCStartTime, dtTCEndTime);
            caseDuration.setValue(String.valueOf(sec));
            testCase.setAttributeNode(caseDuration);
            testSuite.appendChild(testCase);
            return FinalStatus;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String CreateTestStep(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element testCase, String strTestStepName) {
        try {
            if (hmap.containsKey(ReportStepName)) {

                if (hmap.get(ReportStepName).equals(strTestStepName)) {

                } else {
                    tstpID++;

                    hmap.put(ReportStepName, strTestStepName);
                }
            } else {
                tstpID = 1;
                if (ReportStepName == null) {
                    ReportStepName = "ReportStepName";
                }
                hmap.put(ReportStepName, strTestStepName);
            }
            //  TestStep element creation
            Element testStep = doc.createElement("TestStep");
            Attr stepName = doc.createAttribute("Name");
            stepName.setValue(strTestStepName);
            testStep.setAttributeNode(stepName);
            Attr testStepID = doc.createAttribute("ID");
            testStepID.setValue(String.valueOf(tstpID));
            testStep.setAttributeNode(testStepID);
            Date dnow = new Date();
            dtTstpStartTime = hms.format(dnow);
            Attr stepStrtTime = doc.createAttribute("StartTime");
            stepStrtTime.setValue(dtTstpStartTime);
            testStep.setAttributeNode(stepStrtTime);
            strStatusFlag = CreateTestStepLog(dbFactory, dBuilder, doc, rootElement, testStep);
            Date dnowe = new Date();
            dtTstpEndTime = hms.format(dnowe);
            Attr stepEndTime = doc.createAttribute("EndTime");
            stepEndTime.setValue(dtTstpEndTime);
            testStep.setAttributeNode(stepEndTime);
            Attr stepExpStatus = doc.createAttribute("ExpectedStatus");
            stepExpStatus.setValue("PASS");
            testStep.setAttributeNode(stepExpStatus);
            Attr stepActStatus = doc.createAttribute("ActualStatus");
            stepActStatus.setValue(strStatusFlag);
            testStep.setAttributeNode(stepActStatus);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
            }
            Attr stepFinalStatus = doc.createAttribute("FinalStatus");
            stepFinalStatus.setValue(FinalStatus);
            testStep.setAttributeNode(stepFinalStatus);

            Attr stepDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dtTstpStartTime, dtTstpEndTime);
            stepDuration.setValue(String.valueOf(sec));
            testStep.setAttributeNode(stepDuration);
            testCase.appendChild(testStep);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);
            if (strStepStatus.toUpperCase().equals("FAIL")) {
                hmap.put(updstpstatus, strStepStatus.toUpperCase());
                String screenshotFileName = getscreenshot(screenshotFilePath);
                Attr logImage = doc.createAttribute("Screenshot");
                logImage.setValue("img/screenshots/" + screenshotFileName);
                testStep.setAttributeNode(logImage);
            }
            return FinalStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String CreateTestStepLog(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element testStep) {
        try {

            //  TestSuite element creation
            Element testStepLog = doc.createElement("Log");
            Attr logName = doc.createAttribute("Name");
            logName.setValue(StrStepDescription);
            testStepLog.setAttributeNode(logName);
            Attr logType = doc.createAttribute("Type");
            logType.setValue(strStepType);
            testStepLog.setAttributeNode(logType);
            Date dnow = new Date();
            dttstpInfoStartTime = hms.format(dnow);
            Attr logStrtTime = doc.createAttribute("StartTime");
            logStrtTime.setValue(dttstpInfoStartTime);
            testStepLog.setAttributeNode(logStrtTime);
            Date dnowe = new Date();
            dttstpInfoEndTime = hms.format(dnowe);
            Attr logEndTime = doc.createAttribute("EndTime");
            logEndTime.setValue(dttstpInfoEndTime);
            testStepLog.setAttributeNode(logEndTime);
            Attr logStatus = doc.createAttribute("Status");
            logStatus.setValue(strStepStatus.toUpperCase());
            testStepLog.setAttributeNode(logStatus);
//				if(strStepStatus.toUpperCase().equals("FAIL")){
//					String screenshotFileName = captureScreenShot(screenshotFilePath);
//					Attr logImage = doc.createAttribute("Screenshot");
//					logImage.setValue("img/screenshots/"+ screenshotFileName);
//					testStepLog.setAttributeNode(logImage);
//				}
            if (strStepStatus.toUpperCase().equals("FAIL")) {

                hmap.put(updstpstatus, strStepStatus.toUpperCase());
                String screenshotFileName = getscreenshot(screenshotFilePath);
                Attr logImage = doc.createAttribute("Screenshot");
                logImage.setValue("img/screenshots/" + screenshotFileName);
                testStepLog.setAttributeNode(logImage);
            }
            Attr logDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dttstpInfoStartTime, dttstpInfoEndTime);
            logDuration.setValue(String.valueOf(sec));
            testStepLog.setAttributeNode(logDuration);
            testStep.appendChild(testStepLog);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);

            return strStepStatus.toUpperCase();

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String AppendTestSuite(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element project, String strTestSuiteName, String strTestCaseName, String strTestStepName) {
        try {

            if (hmap.containsKey(ReportSuiteName)) {

                if (hmap.get(ReportSuiteName).equals(strTestSuiteName)) {

                } else {
                    tsID++;
                    hmap.put(ReportSuiteName, strTestSuiteName);
                }
            } else {
                tsID = 1;
                if (ReportSuiteName == null) {
                    ReportSuiteName = "ReportSuiteName";
                }
                hmap.put(ReportSuiteName, strTestSuiteName);
            }

            //  TestSuite element creation
            Element testSuite = doc.createElement("TestSuite");
            Attr suiteName = doc.createAttribute("Name");
            suiteName.setValue(strTestSuiteName);
            testSuite.setAttributeNode(suiteName);
            Attr testSuiteID = doc.createAttribute("ID");
            testSuiteID.setValue(String.valueOf(tsID));
            testSuite.setAttributeNode(testSuiteID);
            Date dnow = new Date();
            dtTSStartTime = hms.format(dnow);
            Attr suiteStrtTime = doc.createAttribute("StartTime");
            suiteStrtTime.setValue(dtTSStartTime);
            testSuite.setAttributeNode(suiteStrtTime);
            strStatusFlag = CreateTestCase(dbFactory, dBuilder, doc, rootElement, project, testSuite, strTestCaseName, strTestStepName);
            Date dnowe = new Date();
            dtTSEndTime = hms.format(dnowe);
            Attr suiteEndTime = doc.createAttribute("EndTime");
            suiteEndTime.setValue(dtTSEndTime);
            testSuite.setAttributeNode(suiteEndTime);
            Attr suiteExpStatus = doc.createAttribute("ExpectedStatus");
            suiteExpStatus.setValue("PASS");
            testSuite.setAttributeNode(suiteExpStatus);
            Attr suiteActStatus = doc.createAttribute("ActualStatus");
            suiteActStatus.setValue(strStepStatus.toUpperCase());
            testSuite.setAttributeNode(suiteActStatus);

            if ((strStepStatus.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
                if (hmap.containsKey(updstpstatus)) {
                    Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                    updsuiteActStatus.setValue("FAIL");
                    testSuite.setAttributeNode(updsuiteActStatus);
                    Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                    suiteFinalStatus.setValue("FAIL");
                    testSuite.setAttributeNode(suiteFinalStatus);
                } else {
                    Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                    updsuiteActStatus.setValue("PASS");
                    testSuite.setAttributeNode(updsuiteActStatus);
                    Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                    suiteFinalStatus.setValue("PASS");
                    testSuite.setAttributeNode(suiteFinalStatus);
                }
            }

            Attr suiteDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dtTSStartTime, dtTSEndTime);
            suiteDuration.setValue(String.valueOf(sec));
            testSuite.setAttributeNode(suiteDuration);
            doc.getDocumentElement().appendChild(testSuite);
            rootElement.appendChild(project);
            return FinalStatus;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String AppendTestCase(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element project, Element testSuite, String strTestCaseName, String strTestStepName) {
        try {
            if (hmap.containsKey(ReportCaseName)) {

                if (hmap.get(ReportCaseName).equals(strTestCaseName)) {

                } else {
                    tcID++;
                    hmap.put(ReportCaseName, strTestCaseName);
                }
            } else {
                tcID = 1;
                if (ReportCaseName == null) {
                    ReportCaseName = "ReportCaseName";
                }
                hmap.put(ReportCaseName, strTestCaseName);
            }
            //System.out.println(tcID);

            //  TestSuite element creation
            Element testCase = doc.createElement("TestCase");
            Attr caseName = doc.createAttribute("Name");
            caseName.setValue(strTestCaseName);
            testCase.setAttributeNode(caseName);
            Attr testCaseID = doc.createAttribute("ID");
            testCaseID.setValue(String.valueOf(tcID));
            testCase.setAttributeNode(testCaseID);
            Date dnow = new Date();
            dtTCStartTime = hms.format(dnow);
            Attr caseStrtTime = doc.createAttribute("StartTime");
            caseStrtTime.setValue(dtTCStartTime);
            testCase.setAttributeNode(caseStrtTime);
            strStatusFlag = CreateTestStep(dbFactory, dBuilder, doc, rootElement, testCase, strTestStepName);
            Date dnowe = new Date();
            dtTCEndTime = hms.format(dnowe);
            Attr caseEndTime = doc.createAttribute("EndTime");
            caseEndTime.setValue(dtTCEndTime);
            testCase.setAttributeNode(caseEndTime);
            Attr caseExpStatus = doc.createAttribute("ExpectedStatus");
            caseExpStatus.setValue("PASS");
            testCase.setAttributeNode(caseExpStatus);
            Attr caseActStatus = doc.createAttribute("ActualStatus");
            caseActStatus.setValue(strStatusFlag.toUpperCase());
            testCase.setAttributeNode(caseActStatus);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
            }
            Attr caseFinalStatus = doc.createAttribute("FinalStatus");
            caseFinalStatus.setValue(FinalStatus);
            testCase.setAttributeNode(caseFinalStatus);
            Attr caseDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dtTCStartTime, dtTCEndTime);
            caseDuration.setValue(String.valueOf(sec));
            testCase.setAttributeNode(caseDuration);

            Date dnowsuit = new Date();
            dtTSEndTime = hms.format(dnowsuit);
            Attr suiteEndTime = doc.createAttribute("EndTime");
            suiteEndTime.setValue(dtTSEndTime);
            testSuite.setAttributeNode(suiteEndTime);
            Attr suiteExpStatus = doc.createAttribute("ExpectedStatus");
            suiteExpStatus.setValue("PASS");
            testSuite.setAttributeNode(suiteExpStatus);
            Attr suiteActStatus = doc.createAttribute("ActualStatus");
            suiteActStatus.setValue(strStepStatus.toUpperCase());
            testSuite.setAttributeNode(suiteActStatus);

            if (hmap.containsKey(updstpstatus)) {
                Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                updsuiteActStatus.setValue("FAIL");
                testSuite.setAttributeNode(updsuiteActStatus);
                Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                suiteFinalStatus.setValue("FAIL");
                testSuite.setAttributeNode(suiteFinalStatus);
            } else {
                Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                updsuiteActStatus.setValue("PASS");
                testSuite.setAttributeNode(updsuiteActStatus);
                Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                suiteFinalStatus.setValue("PASS");
                testSuite.setAttributeNode(suiteFinalStatus);
            }

            Attr suiteDuration = doc.createAttribute("Duration");
            String tssec = timeDifference(dtTSStartTime, dtTSEndTime);
            suiteDuration.setValue(String.valueOf(tssec));
            testSuite.setAttributeNode(suiteDuration);

            testSuite.appendChild(testCase);
            doc.getDocumentElement().appendChild(testSuite);
            rootElement.appendChild(project);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);
            return FinalStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String AppendTestStep(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element project, Element testSuite, Element testCase, String strTestStepName) {
        try {
            if (hmap.containsKey(ReportStepName)) {

                if (hmap.get(ReportStepName).equals(strTestStepName)) {

                } else {
                    tstpID++;
                    hmap.put(ReportStepName, strTestStepName);
                }
            } else {
                tstpID = 1;
                if (ReportStepName == null) {
                    ReportStepName = "ReportStepName";
                }
                hmap.put(ReportStepName, strTestStepName);
            }

            //  TestStep element creation
            Element testStep = doc.createElement("TestStep");
            Attr stepName = doc.createAttribute("Name");
            stepName.setValue(strTestStepName);
            testStep.setAttributeNode(stepName);
            Attr testStepID = doc.createAttribute("ID");
            testStepID.setValue(String.valueOf(tstpID));
            testStep.setAttributeNode(testStepID);
            Date dnow = new Date();
            dtTstpStartTime = hms.format(dnow);
            Attr stepStrtTime = doc.createAttribute("StartTime");
            stepStrtTime.setValue(dtTstpStartTime);
            testStep.setAttributeNode(stepStrtTime);
            strStatusFlag = CreateTestStepLog(dbFactory, dBuilder, doc, rootElement, testStep);
            Date dnowE = new Date();
            dtTstpEndTime = hms.format(dnowE);
            Attr stepEndTime = doc.createAttribute("EndTime");
            stepEndTime.setValue(dtTstpEndTime);
            testStep.setAttributeNode(stepEndTime);
            Attr stepExpStatus = doc.createAttribute("ExpectedStatus");
            stepExpStatus.setValue("PASS");
            testStep.setAttributeNode(stepExpStatus);
            Attr stepActStatus = doc.createAttribute("ActualStatus");
            stepActStatus.setValue(strStatusFlag);
            testStep.setAttributeNode(stepActStatus);
            if ((strStatusFlag.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
            }
            Attr stepFinalStatus = doc.createAttribute("FinalStatus");
            stepFinalStatus.setValue(FinalStatus);
            testStep.setAttributeNode(stepFinalStatus);
            Attr stepDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dtTstpStartTime, dtTstpEndTime);
            stepDuration.setValue(String.valueOf(sec));
            testStep.setAttributeNode(stepDuration);

            Date dnowe = new Date();
            dtTCEndTime = hms.format(dnowe);
            Attr caseEndTime = doc.createAttribute("EndTime");
            caseEndTime.setValue(dtTCEndTime);
            testCase.setAttributeNode(caseEndTime);
            Attr caseExpStatus = doc.createAttribute("ExpectedStatus");
            caseExpStatus.setValue("PASS");
            testCase.setAttributeNode(caseExpStatus);
            Attr caseActStatus = doc.createAttribute("ActualStatus");
            caseActStatus.setValue(strStepStatus.toUpperCase());
            testCase.setAttributeNode(caseActStatus);
            if ((strStepStatus.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
                if (hmap.containsKey(updstpstatus)) {
                    Attr caseFinalStatus = doc.createAttribute("FinalStatus");
                    caseFinalStatus.setValue("FAIL");
                    testCase.setAttributeNode(caseFinalStatus);
                } else {
                    Attr caseFinalStatus = doc.createAttribute("FinalStatus");
                    caseFinalStatus.setValue("PASS");
                    testCase.setAttributeNode(caseFinalStatus);
                }
            }

            Attr caseDuration = doc.createAttribute("Duration");
            String tcsec = timeDifference(dtTCStartTime, dtTCEndTime);
            caseDuration.setValue(String.valueOf(tcsec));
            testCase.setAttributeNode(caseDuration);

            Date dnowsuit = new Date();
            dtTSEndTime = hms.format(dnowsuit);
            Attr suiteEndTime = doc.createAttribute("EndTime");
            suiteEndTime.setValue(dtTSEndTime);
            testSuite.setAttributeNode(suiteEndTime);
            Attr suiteExpStatus = doc.createAttribute("ExpectedStatus");
            suiteExpStatus.setValue("PASS");
            testSuite.setAttributeNode(suiteExpStatus);
            Attr suiteActStatus = doc.createAttribute("ActualStatus");
            suiteActStatus.setValue(strStepStatus.toUpperCase());
            testSuite.setAttributeNode(suiteActStatus);

//				if((strStepStatus.toUpperCase()).equals("PASS")){
//					FinalStatus = "PASS";
//				}
//				else{
//					FinalStatus = "FAIL";
            if (hmap.containsKey(updstpstatus)) {
                Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                updsuiteActStatus.setValue("FAIL");
                testSuite.setAttributeNode(updsuiteActStatus);
                Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                suiteFinalStatus.setValue("FAIL");
                testSuite.setAttributeNode(suiteFinalStatus);
            } else {
                Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                updsuiteActStatus.setValue("PASS");
                testSuite.setAttributeNode(updsuiteActStatus);
                Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                suiteFinalStatus.setValue("PASS");
                testSuite.setAttributeNode(suiteFinalStatus);
            }
//				}

            Attr suiteDuration = doc.createAttribute("Duration");
            String tssec = timeDifference(dtTSStartTime, dtTSEndTime);
            suiteDuration.setValue(String.valueOf(tssec));
            testSuite.setAttributeNode(suiteDuration);

            testCase.appendChild(testStep);
            testSuite.appendChild(testCase);
            doc.getDocumentElement().appendChild(testSuite);
            rootElement.appendChild(project);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);
            if (strStepStatus.toUpperCase().equals("FAIL")) {
                hmap.put(updstpstatus, strStepStatus.toUpperCase());
                String screenshotFileName = getscreenshot(screenshotFilePath);
                Attr logImage = doc.createAttribute("Screenshot");
                logImage.setValue("img/screenshots/" + screenshotFileName);
                testStep.setAttributeNode(logImage);
            }
            return FinalStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String AppendTestStepLog(DocumentBuilderFactory dbFactory, DocumentBuilder dBuilder, Document doc, Element rootElement, Element project, Element testSuite, Element testCase, Element testStep) {
        try {

            Date dnow = new Date();
            dttstpInfoStartTime = hms.format(dnow);
            //  TestSuite element creation
            Element testStepLog = doc.createElement("Log");
            Attr logName = doc.createAttribute("Name");
            logName.setValue(StrStepDescription);
            testStepLog.setAttributeNode(logName);
            Attr logType = doc.createAttribute("Type");
            logType.setValue(strStepType);
            testStepLog.setAttributeNode(logType);
            Attr logStrtTime = doc.createAttribute("StartTime");
            logStrtTime.setValue(dttstpInfoStartTime);
            testStepLog.setAttributeNode(logStrtTime);
            Date dnowE = new Date();
            dttstpInfoEndTime = hms.format(dnowE);
            Attr logEndTime = doc.createAttribute("EndTime");
            logEndTime.setValue(dttstpInfoEndTime);
            testStepLog.setAttributeNode(logEndTime);
            Attr logStatus = doc.createAttribute("Status");
            logStatus.setValue(strStepStatus.toUpperCase());
            testStepLog.setAttributeNode(logStatus);

            if (strStepStatus.toUpperCase().equals("FAIL")) {
                final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
                //String updstpstatus = "updstpstatus";
                hmap.put(updstpstatus, strStepStatus.toUpperCase());
                String screenshotFileName = getscreenshot(screenshotFilePath);
                Attr logImage = doc.createAttribute("Screenshot");
                logImage.setValue("img/screenshots/" + screenshotFileName);
                testStepLog.setAttributeNode(logImage);
            }
            Attr logDuration = doc.createAttribute("Duration");
            String sec = timeDifference(dttstpInfoStartTime, dttstpInfoEndTime);
            logDuration.setValue(String.valueOf(sec));
            testStepLog.setAttributeNode(logDuration);

            Date dnowstE = new Date();
            dtTstpEndTime = hms.format(dnowstE);
            Attr stepEndTime = doc.createAttribute("EndTime");
            stepEndTime.setValue(dtTstpEndTime);
            testStep.setAttributeNode(stepEndTime);
            Attr stepExpStatus = doc.createAttribute("ExpectedStatus");
            stepExpStatus.setValue("PASS");
            testStep.setAttributeNode(stepExpStatus);
            Attr stepActStatus = doc.createAttribute("ActualStatus");
            stepActStatus.setValue(strStepStatus.toUpperCase());
            testStep.setAttributeNode(stepActStatus);
            if ((strStepStatus.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
            }
            Attr stepFinalStatus = doc.createAttribute("FinalStatus");
            stepFinalStatus.setValue(FinalStatus);
            testStep.setAttributeNode(stepFinalStatus);
            Attr stepDuration = doc.createAttribute("Duration");
            String tstpsec = timeDifference(dtTstpStartTime, dtTstpEndTime);
            stepDuration.setValue(String.valueOf(tstpsec));
            testStep.setAttributeNode(stepDuration);

            Date dnowe = new Date();
            dtTCEndTime = hms.format(dnowe);
            Attr caseEndTime = doc.createAttribute("EndTime");
            caseEndTime.setValue(dtTCEndTime);
            testCase.setAttributeNode(caseEndTime);
            Attr caseExpStatus = doc.createAttribute("ExpectedStatus");
            caseExpStatus.setValue("PASS");
            testCase.setAttributeNode(caseExpStatus);
            Attr caseActStatus = doc.createAttribute("ActualStatus");
            caseActStatus.setValue(strStepStatus.toUpperCase());
            testCase.setAttributeNode(caseActStatus);
            if ((strStepStatus.toUpperCase()).equals("PASS")) {
                FinalStatus = "PASS";
            } else {
                FinalStatus = "FAIL";
                if (hmap.containsKey(updstpstatus)) {
                    Attr caseFinalStatus = doc.createAttribute("FinalStatus");
                    caseFinalStatus.setValue("FAIL");
                    testCase.setAttributeNode(caseFinalStatus);
                } else {
                    Attr caseFinalStatus = doc.createAttribute("FinalStatus");
                    caseFinalStatus.setValue("PASS");
                    testCase.setAttributeNode(caseFinalStatus);
                }
            }

            Attr caseDuration = doc.createAttribute("Duration");
            String tcsec = timeDifference(dtTCStartTime, dtTCEndTime);
            caseDuration.setValue(String.valueOf(tcsec));
            testCase.setAttributeNode(caseDuration);

            Date dnowsuit = new Date();
            dtTSEndTime = hms.format(dnowsuit);
            Attr suiteEndTime = doc.createAttribute("EndTime");
            suiteEndTime.setValue(dtTSEndTime);
            testSuite.setAttributeNode(suiteEndTime);
            Attr suiteExpStatus = doc.createAttribute("ExpectedStatus");
            suiteExpStatus.setValue("PASS");
            testSuite.setAttributeNode(suiteExpStatus);
            Attr suiteActStatus = doc.createAttribute("ActualStatus");
            suiteActStatus.setValue(strStepStatus.toUpperCase());
            testSuite.setAttributeNode(suiteActStatus);

//				if((strStepStatus.toUpperCase()).equals("PASS")){
//					FinalStatus = "PASS";
//				}
//				else{
//					FinalStatus = "FAIL";
            if (hmap.containsKey(updstpstatus)) {
                Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                updsuiteActStatus.setValue("FAIL");
                testSuite.setAttributeNode(updsuiteActStatus);
                Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                suiteFinalStatus.setValue("FAIL");
                testSuite.setAttributeNode(suiteFinalStatus);
            } else {
                Attr updsuiteActStatus = doc.createAttribute("ActualStatus");
                updsuiteActStatus.setValue("PASS");
                testSuite.setAttributeNode(updsuiteActStatus);
                Attr suiteFinalStatus = doc.createAttribute("FinalStatus");
                suiteFinalStatus.setValue("PASS");
                testSuite.setAttributeNode(suiteFinalStatus);
            }
//				}

            Attr suiteDuration = doc.createAttribute("Duration");
            String tssec = timeDifference(dtTSStartTime, dtTSEndTime);
            suiteDuration.setValue(String.valueOf(tssec));
            testSuite.setAttributeNode(suiteDuration);

            testStep.appendChild(testStepLog);
            testCase.appendChild(testStep);
            testSuite.appendChild(testCase);
            doc.getDocumentElement().appendChild(testSuite);
            rootElement.appendChild(project);
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlSavedLocation));
            transformer.transform(source, result);
            return strStepStatus.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String timeDifference(String startTime, String endTime)
            throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");

        Date time1 = dateFormat.parse(startTime);
        Date time2 = dateFormat.parse(endTime);
        long t1 = time1.getTime();

        long t2 = time2.getTime();

        long secs = (Math.abs(t2 - t1) / 1000);
        display = String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60));
        return (display);
    }

    public static String captureScreenShot(String ssfilePath) throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        try {
            //WAITTIME("WAITTIME->10");
            final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
            Date instant = new Date(MSEC_SINCE_EPOCH);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(instant);
            time = time.replace(':', '-');
            Robot robot = new Robot();
            String format = "jpg";
            String fileName1 = time + "Screenshot." + format;
            String fileName = ssfilePath + fileName1;

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));

            //System.out.println("A full screenshot saved!");
            return fileName1;
        } catch (AWTException | IOException ex) {
            //System.err.println(ex);
            return "Error";
        }
    }

    public static String getscreenshot(String ssfilePath) throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {

        String fileName1;
        try {

           // WAITTIME("WAITTIME->10");
            final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
            Date instant = new Date(MSEC_SINCE_EPOCH);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(instant);
            time = time.replace(':', '-');
            Robot robot = new Robot();
            String format = "jpg";
            File scrFile = null;
            fileName1 = time + "Screenshot." + format;
            String fileName = ssfilePath + fileName1;

            {
                if(Appinventive_User.Flag) {
                   // DriverScript.driver = UtilityFunctions.LaunchBrowser();
                    scrFile = ((TakesScreenshot) DriverScript.driver).getScreenshotAs(OutputType.FILE);
                }
                else{
                    System.out.println("driver not required");
                }

                // System.out.println(scrFile);
                //The below method will save the screen shot in d drive with name "screenshot.png"
                File ds = new File(fileName);
                FileUtils.copyFile(scrFile, new File(fileName));

            }
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
            return "Error";
        }
        return fileName1;

    }


}


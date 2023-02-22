//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.appinventive.qa.report;

import com.appinventive.qa.util.DateUtils;
//import com.mc.fact.util.DateUtils;
//import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import org.bson.Document;
import org.w3c.dom.*;

public class ExecutionDetails {
    private String executionID;
    private String team;
    private List<TestCase> testCases = new ArrayList();
    private String testPlan;
    private Date startTime;
    private Date endTime;
    private String executionTime;
    private Long executionTimeMs;
    private String testEnv;
    private String testType;
    private String sprint = "NA";
    private String adoTeam;
    private int totalTc = 0;
    private int passed = 0;
    private int failed = 0;
    private int notExecuted = 0;

    public ExecutionDetails(String executionID, String team, String testPlan, Date startTime, String testEnv, String testType, String adoTeam) {
        this.executionID = executionID;
        this.team = team;
        this.testPlan = testPlan;
        this.startTime = startTime;
        this.testEnv = testEnv;
        this.testType = testType;
        this.adoTeam = adoTeam;
    }

    public void calculateNumberOfTCs() {
        Iterator var1 = this.testCases.iterator();

        while(var1.hasNext()) {
            TestCase testCase = (TestCase)var1.next();
            ++this.totalTc;
            String var3 = testCase.getStatus();
            byte var4 = -1;
            switch(var3.hashCode()) {
                case 2181950:
                    if (var3.equals("Fail")) {
                        var4 = 1;
                    }
                    break;
                case 2480177:
                    if (var3.equals("Pass")) {
                        var4 = 0;
                    }
            }

            switch(var4) {
                case 0:
                    ++this.passed;
                    break;
                case 1:
                    ++this.failed;
                    break;
                default:
                    ++this.notExecuted;
            }
        }

    }

    public List<TestCase> getTestCases() {
        return this.testCases;
    }

    public String getAdoTeam() {
        return this.adoTeam;
    }

    public void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }

    public String getExecutionID() {
        return this.executionID;
    }

    public void setExecutionID(String executionID) {
        this.executionID = executionID;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTestPlan() {
        return this.testPlan;
    }

    public void setTestPlan(String testPlan) {
        this.testPlan = testPlan;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getExecutionTime() {
        return this.executionTime;
    }

    public String getTestEnv() {
        return this.testEnv;
    }

    public void setTestEnv(String testEnv) {
        this.testEnv = testEnv;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getTestType() {
        return this.testType;
    }

    public void calculateExecutionTime(Date endTime) {
        this.endTime = endTime;
        this.executionTime = DateUtils.timeDiff(this.startTime, this.endTime);
        this.executionTimeMs = DateUtils.timeDiffMs(this.startTime, this.endTime);
    }

    public String toString() {
        return "{\"executionID\":\"" + this.executionID + '"' + ", \"project\" :\"" + this.team + '"' + ", \"testPlan\" :\"" + this.testPlan + '"' + ", \"team\" :\"" + this.team + '"' + ", \"testEnv\" :\"" + this.testEnv + '"' + ", \"testType\" :\"" + this.testType + '"' + ", \"sprint\" :\"" + this.sprint + '"' + ", \"startTime\" :\"" + this.startTime + '"' + ", \"endTime\" :\"" + this.endTime + '"' + ", \"executionTime\" :\"" + this.executionTime + '"' + ", \"executionTimeMs\" :\"" + this.executionTimeMs + '"' + ", \"totalTc\" :\"" + this.totalTc + '"' + ", \"passed\" :\"" + this.passed + '"' + ", \"failed\" :\"" + this.failed + '"' + ", \"notExecuted\" :\"" + this.notExecuted + '"' + ", \"testCases\": " + this.testCases.toString() + '}';
    }

//    public List<DBObject> testCasesDbObjectList() {
//        List<DBObject> testCasesDBobject = new ArrayList();
//        Iterator var2 = this.testCases.iterator();
//
//        while(var2.hasNext()) {
//            TestCase testCase = (TestCase)var2.next();
//            testCasesDBobject.add(testCase.toDbObject());
//        }
//
//        return testCasesDBobject;
//    }

    public Document toDocument() {
        Document document = new Document() {
            @Override
            public String getNodeName() {
                return null;
            }

            @Override
            public String getNodeValue() throws DOMException {
                return null;
            }

            @Override
            public void setNodeValue(String nodeValue) throws DOMException {

            }

            @Override
            public short getNodeType() {
                return 0;
            }

            @Override
            public Node getParentNode() {
                return null;
            }

            @Override
            public NodeList getChildNodes() {
                return null;
            }

            @Override
            public Node getFirstChild() {
                return null;
            }

            @Override
            public Node getLastChild() {
                return null;
            }

            @Override
            public Node getPreviousSibling() {
                return null;
            }

            @Override
            public Node getNextSibling() {
                return null;
            }

            @Override
            public NamedNodeMap getAttributes() {
                return null;
            }

            @Override
            public Document getOwnerDocument() {
                return null;
            }

            @Override
            public Node insertBefore(Node newChild, Node refChild) throws DOMException {
                return null;
            }

            @Override
            public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node removeChild(Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node appendChild(Node newChild) throws DOMException {
                return null;
            }

            @Override
            public boolean hasChildNodes() {
                return false;
            }

            @Override
            public Node cloneNode(boolean deep) {
                return null;
            }

            @Override
            public void normalize() {

            }

            @Override
            public boolean isSupported(String feature, String version) {
                return false;
            }

            @Override
            public String getNamespaceURI() {
                return null;
            }

            @Override
            public String getPrefix() {
                return null;
            }

            @Override
            public void setPrefix(String prefix) throws DOMException {

            }

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public boolean hasAttributes() {
                return false;
            }

            @Override
            public String getBaseURI() {
                return null;
            }

            @Override
            public short compareDocumentPosition(Node other) throws DOMException {
                return 0;
            }

            @Override
            public String getTextContent() throws DOMException {
                return null;
            }

            @Override
            public void setTextContent(String textContent) throws DOMException {

            }

            @Override
            public boolean isSameNode(Node other) {
                return false;
            }

            @Override
            public String lookupPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public boolean isDefaultNamespace(String namespaceURI) {
                return false;
            }

            @Override
            public String lookupNamespaceURI(String prefix) {
                return null;
            }

            @Override
            public boolean isEqualNode(Node arg) {
                return false;
            }

            @Override
            public Object getFeature(String feature, String version) {
                return null;
            }

            @Override
            public Object setUserData(String key, Object data, UserDataHandler handler) {
                return null;
            }

            @Override
            public Object getUserData(String key) {
                return null;
            }

            @Override
            public DocumentType getDoctype() {
                return null;
            }

            @Override
            public DOMImplementation getImplementation() {
                return null;
            }

            @Override
            public Element getDocumentElement() {
                return null;
            }

            @Override
            public Element createElement(String tagName) throws DOMException {
                return null;
            }

            @Override
            public DocumentFragment createDocumentFragment() {
                return null;
            }

            @Override
            public Text createTextNode(String data) {
                return null;
            }

            @Override
            public Comment createComment(String data) {
                return null;
            }

            @Override
            public CDATASection createCDATASection(String data) throws DOMException {
                return null;
            }

            @Override
            public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
                return null;
            }

            @Override
            public Attr createAttribute(String name) throws DOMException {
                return null;
            }

            @Override
            public EntityReference createEntityReference(String name) throws DOMException {
                return null;
            }

            @Override
            public NodeList getElementsByTagName(String tagname) {
                return null;
            }

            @Override
            public Node importNode(Node importedNode, boolean deep) throws DOMException {
                return null;
            }

            @Override
            public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
                return null;
            }

            @Override
            public Element getElementById(String elementId) {
                return null;
            }

            @Override
            public String getInputEncoding() {
                return null;
            }

            @Override
            public String getXmlEncoding() {
                return null;
            }

            @Override
            public boolean getXmlStandalone() {
                return false;
            }

            @Override
            public void setXmlStandalone(boolean xmlStandalone) throws DOMException {

            }

            @Override
            public String getXmlVersion() {
                return null;
            }

            @Override
            public void setXmlVersion(String xmlVersion) throws DOMException {

            }

            @Override
            public boolean getStrictErrorChecking() {
                return false;
            }

            @Override
            public void setStrictErrorChecking(boolean strictErrorChecking) {

            }

            @Override
            public String getDocumentURI() {
                return null;
            }

            @Override
            public void setDocumentURI(String documentURI) {

            }

            @Override
            public Node adoptNode(Node source) throws DOMException {
                return null;
            }

            @Override
            public DOMConfiguration getDomConfig() {
                return null;
            }

            @Override
            public void normalizeDocument() {

            }

            @Override
            public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }
        };
//        document.append("executionID", this.executionID);
//        document.append("team", this.team);
//        document.append("testPlan", this.testPlan);
//        document.append("team", this.team);
//        document.append("testEnv", this.testEnv);
//        document.append("testType", this.testType);
//        document.append("sprint", this.sprint);
//        document.append("startTime", this.startTime);
//        document.append("endTime", this.endTime);
//        document.append("executionTime", this.executionTime);
//        document.append("executionTimeMs", this.executionTimeMs);
//        document.append("totalTc", this.totalTc);
//        document.append("passed", this.passed);
//        document.append("failed", this.failed);
//        document.append("notExecuted", this.notExecuted);
//        document.append("testCases", this.testCasesDbObjectList());
        return document;
    }
}

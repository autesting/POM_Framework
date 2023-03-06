package com.appinventive.qa;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Element;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Setup {

    public final static HashMap<String, String> hmap = new HashMap<String, String>();
    public static java.util.Date odate;
    public static String Tcase="Tcase";
    public static int timer;
    public static String user;
    public static String Tsuitename;
    public static String suitename;
    public static String Tcasename;
    public static String updstpstatus = null;
    public static String envfilepath = null;
    public static String savedlocation = null;
    public static String Tstep = "Tstep";
    public static String locator;
    public static Element prjElement = null;
    public static Element tsElement = null;
    public static Element tcElement = null;
    public static Element tstpElement = null;
    public static String releasename;
    public static String environmentname;
    public static String url;
    public static String display;
    public static String ExecutionMode;
    public static WebDriver driver;



    public static void FOLDERSTRUCTURE(String suitename)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        try {
            final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
            String workingDirectory = new java.io.File(".").getCanonicalPath();
            String opath = workingDirectory;
            opath = opath.replace("IRScripts", "");
            opath = opath.replace('\\', '/');
            File srcDir = new File(opath + "/HTMLTemplates/");
            File destDir = new File(opath + "/Reports/");
            // Calendar calendar = Calendar.getInstance();
            // Date now = calendar.getTime();
            Date instant = new Date(MSEC_SINCE_EPOCH);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
            String time = sdf.format(instant);
            time = time.replace(':', '-');
            suitename = suitename + "_" + time;
            File dir = new File(opath + "/Reports/" + suitename);
            dir.mkdir();
            FileUtils.copyDirectory(srcDir, dir);
            savedlocation = opath + "/Reports/" + suitename;
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }
    }

}

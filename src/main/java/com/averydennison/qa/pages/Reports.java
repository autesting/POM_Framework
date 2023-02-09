package com.averydennison.qa.pages;

import static com.sun.javafx.util.Utils.split;

import java.awt.AWTException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import static org.hamcrest.Matchers.array;
//import static org.hamcrest.collection.IsArray.array;
import org.openqa.selenium.StaleElementReferenceException;

@SuppressWarnings({ "unused" })
public class Reports {

	public final static HashMap<String, String> hmap = new HashMap<String, String>();
	public static String suitename;
	public static String ESuitestatus;
	public static String ESuiteDuration = null;
	public static int TCfailcount = 0;
	public static String suiteflag = "PASS";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String, List> hmapsteps = new HashMap();
	public static ArrayList<String> TSteps = new ArrayList<String>();
	public static ArrayList<String> exsteps = new ArrayList<String>();
	public static HashMap<String, Integer> casestatus = new HashMap();


	/* Function Name: Automation_Report()
    Author :  QA Automation
    Version: 1.0
    Parameters: null
    Description: This is Automation Report  Function
                 creates excel with suite name appended with timestamp
                 function reads data from two ArrayLists 1. excellist arraylist and 2. TCname arraylist
                 excellist has hasmap(hmapsteps) has items.
                 it loops through the excellist and gets the Testcase names and Teststeps for the corresponding testcase,
                 hmapsteps is an hashmap contains test case name as key ,and arraylist Tsteps as value
    */
	
	
	
	public static void Automation_Report()
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		Boolean Status = true;
		final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
		try {
			String datasheet = "AD_Reports";
			Date instant = new Date(MSEC_SINCE_EPOCH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
			String time = sdf.format(instant);
			time = time.replace(':', '-');
			datasheet = datasheet + "_" + time;
			String workingDirectory = new java.io.File(".").getCanonicalPath();
			// System.out.println(workingDirectory);
			String path = workingDirectory + "/Reports";
			File file1 = new File(path + "/" + datasheet + ".xlsx");
			//File file = new File(path);
			file1.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(file1);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet1;
			XSSFSheet spreadsheet2;
			spreadsheet1 = workbook.createSheet(datasheet);
			XSSFRow Srow;
			XSSFCellStyle style = workbook.createCellStyle();
			style.setBorderTop(BorderStyle.DOUBLE);
			style.setBorderBottom(BorderStyle.DOUBLE);
		//	style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
			XSSFFont font = workbook.createFont();
			font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			font.setFontHeightInPoints((short) 10);
			font.setBold(true);
		//	font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
			style.setFont(font);
			XSSFRow Srow1 = null;
			XSSFCell Scell1;
			XSSFCell Scell2;
			XSSFCell Scell3;
			XSSFFont whitefont = workbook.createFont();
		//	whitefont.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
			XSSFFont blackfont = workbook.createFont();
		//	blackfont.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
			XSSFCellStyle style1 = workbook.createCellStyle();
			XSSFCellStyle style2 = workbook.createCellStyle();
			XSSFCellStyle style3 = workbook.createCellStyle();
			XSSFCellStyle style4 = workbook.createCellStyle();
			XSSFCellStyle style5 = workbook.createCellStyle();
			XSSFCellStyle style6 = workbook.createCellStyle();
			XSSFCellStyle style7 = workbook.createCellStyle();
			XSSFCellStyle style8 = workbook.createCellStyle();
			XSSFFont font3 = workbook.createFont();
			XSSFFont font4 = workbook.createFont();
			font3.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			font3.setFontHeightInPoints((short) 10);
			font3.setBold(true);
			style7.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         //   style7.setFillForegroundColor(IndexedColors.YELLOW1.getIndex());
			style7.setFont(font3);
			font4.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			font4.setFontHeightInPoints((short) 10);
			font4.setBold(true);
			style8.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style8.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style8.setAlignment(HorizontalAlignment.CENTER);
			style8.setFont(font3);
			XSSFFont font2 = workbook.createFont();
			font2.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			font2.setFontHeightInPoints((short) 10);
			font2.setBold(true);
		//	font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
			style6.setFont(font2);
			XSSFFont font1 = workbook.createFont();
			font1.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			font1.setFontHeightInPoints((short) 10);
			font1.setBold(true);
		//	font1.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
			style5.setFont(font1);
			style1.setFont(font);
			style1.setFont(whitefont);
			style1.setBorderBottom(BorderStyle.THIN);
			style2.setFont(font);
			style2.setFont(whitefont);
			style2.setBorderBottom(BorderStyle.THIN);
			style3.setFont(font);
			style3.setFont(blackfont);
			style3.setBorderBottom(BorderStyle.THIN);
			// style4.setBorderBottom(BorderStyle.THIN);
			style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style4.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			Srow = spreadsheet1.createRow(0);
			XSSFCell Scell;
			Scell = Srow.createCell(0);
			Scell.setCellValue("Automation Summary");
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(1);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Suite Name");
			Scell = Srow.createCell(1);
			Scell.setCellValue(DriverScript.prop.getProperty("SuiteName"));
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(2);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Suite Final Status ");
			Scell = Srow.createCell(1);
			Scell.setCellValue(suiteflag);
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(3);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Total Test Cases Executed ");
			Scell = Srow.createCell(1);
			Scell.setCellValue(ExecutionFlow.loop);
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(4);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Total Suite Execution Time");
			Scell = Srow.createCell(1);
			Scell.setCellValue(ExecutionFlow.hms);
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(5);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Total Cases Passed ");
			Scell = Srow.createCell(1);
			Scell.setCellValue(ExecutionFlow.loop-casestatus.size());
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(6);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Total Cases Failed ");
			Scell = Srow.createCell(1);
			Scell.setCellValue(casestatus.size());
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(7);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Release Name");
			Scell = Srow.createCell(1);
			Scell.setCellValue(DriverScript.prop.getProperty("ReleaseName"));
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(8);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Environment and URL");
			Scell = Srow.createCell(1);
			Scell.setCellValue(DriverScript.prop.getProperty("URL"));
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(9);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Executed By");
			Scell = Srow.createCell(1);
			Scell.setCellValue(DriverScript.prop.getProperty("ExecutedBy"));
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style);
			Srow = spreadsheet1.createRow(11);
			Scell = Srow.createCell(0);
			Scell.setCellValue("Test Case Name");
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style8);
			//Scell = Srow.createCell(1);
			/*
			 * Scell.setCellValue("Execution Time ");
			 * style.setAlignment(HorizontalAlignment.CENTER); Scell.setCellStyle(style8);
			 */
			Scell = Srow.createCell(2);
			Scell.setCellValue("Status ");
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style8);
			Scell = Srow.createCell(1);
			Scell.setCellValue("Steps ");
			style.setAlignment(HorizontalAlignment.CENTER);
			Scell.setCellStyle(style8);
			int rownum = 12;
			for (int j = 0; j < ExecutionFlow.excellist.size(); j++) {
				Srow1 = spreadsheet1.createRow(rownum);
				Scell1 = Srow1.createCell(0);
				Scell1.setCellStyle(style7);
				Scell1.setCellValue(ExecutionFlow.TCname.get(j));
				//Scell1 = Srow1.createCell(1);
				//Scell1.setCellStyle(style);
				//Scell1.setCellValue(ExecutionFlow.TCtime.get(j));
				if (ExecutionFlow.excellist.get(j).containsKey(ExecutionFlow.TCname.get(j))) {
					exsteps = (ArrayList<String>) ExecutionFlow.excellist.get(j).get(ExecutionFlow.TCname.get(j));
					Scell3 = Srow1.createCell(1);
					Scell2 = Srow1.createCell(2);
					for (int k = 0; k <= exsteps.size() - 1; k++) {
						String stepnmae = exsteps.get(k);
						String[] arg = split(stepnmae, "->");
						Scell2 = Srow1.createCell(2);
						Scell2.setCellStyle(style7);
						Scell2.setCellValue(arg[0]);
						Scell3 = Srow1.createCell(1);
						Scell3.setCellStyle(style7);
						Scell3.setCellValue(arg[1]);
						 Scell2 = workbook.getSheetAt(0).getRow(rownum).getCell(2);
						 Scell3 = workbook.getSheetAt(0).getRow(rownum).getCell(1);
			                if (Scell2.toString().equalsIgnoreCase("PASS")) {
			                    style1.setAlignment(HorizontalAlignment.CENTER);
			                    style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			                    style1.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			                    Scell2.setCellStyle(style1);
			                } else {
			                    style2.setAlignment(HorizontalAlignment.CENTER);
			                    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			                    style2.setFillForegroundColor(IndexedColors.RED.getIndex());
			                    Scell2.setCellStyle(style2);
			                }
			                if(Scell3.toString().toUpperCase().contains("FAILED TO")||(Scell3.toString().toUpperCase().contains("UNABLE TO"))) 
			                {
			                	Scell3.setCellStyle(style6);
			                } else {
			                	Scell3.setCellStyle(style5);
			                }
						rownum = rownum+1;
						Srow1 = spreadsheet1.createRow(rownum);
						Scell1 = Srow1.createCell(0);
	                    Scell3 = Srow1.createCell(2);
	                    //Scell1.setCellStyle(style4);

					}
					rownum=rownum+1;

				}

			}

//	          spreadsheet1.setColumnWidth(2, 2000);
			for (int m = 0; m < 200; m++) {
				spreadsheet1.autoSizeColumn(m);
			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {

			System.out.println("Automation Summary" + e);
		}

	}

	/* Function Name: log()
    Author :  QA Automation
    Version: 1.0
    Parameters: (pass/Fail, Login)
    Description: This function  used for reporting purpose
                  accepts step status and step name as input
                  and captures total test cases failed*/
	
	
	public static void log(String Status, String stepname) {
		
		if(Status.toUpperCase().equalsIgnoreCase("FAIL")) {
		if(!(casestatus.containsKey(ExecutionFlow.testcasename))){
				TCfailcount=TCfailcount+1;
				suiteflag="FAIL";
			}
			casestatus.put(ExecutionFlow.testcasename, TCfailcount);
		} 
		TSteps.add(Status + "->" + stepname);

	}

}

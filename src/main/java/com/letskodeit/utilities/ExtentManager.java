package com.letskodeit.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ExtentManager {
    private static final Logger log = LogManager.getLogger(ExtentManager.class.getName());
    private static ExtentReports extent;
    public static ExtentReports getInstance(){ //we want only one instance for extentReports as we dont
        //want multiple reports generated by different instances.
        if(extent==null){
            createInstance();
        }
        return extent;
    }
    public static synchronized ExtentReports createInstance(){
        String fileName = Util.getReportName();
        String reportsDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportsDirectory).mkdir();
        String path = reportsDirectory + "/" + fileName;

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Run");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.setSystemInfo("Organisation", "Lets kode it");
        extent.attachReporter(htmlReporter);

        return extent;
    }
}


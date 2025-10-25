package BaseAndCommons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestClass extends BaseClass{
 public static ExtentReports extent;
    public static ExtentReports getReportObject(){
//start reporter to make all the configuration necessary
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/report.html");
      //  htmlReporter.config().setReportName("Web Automation Results");
     //   htmlReporter.config().setDocumentTitle("Test Results");
        //create ExtentReports and attach reporter(s)
         extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester","Prathibha");

       // ExtentTest extentTest = extent.createTest();
        return extent;


    }


}

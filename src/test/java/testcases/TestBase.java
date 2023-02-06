package testcases;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.Utility;

import java.io.IOException;
import java.sql.SQLException;

public class TestBase {

    // extend report
    protected static ExtentReports report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html");
    protected static ExtentTest test = report.startTest("Database Testing Framework");

    @BeforeTest
    public void setUp() throws SQLException {
        // Connect to the database
        Utility.createDBConnection("test", "3306","root","");
        Utility.createStatement();
        System.out.println("stating db connection successfully");
    }
    @AfterTest
    public void tearDown() throws SQLException, IOException {
        // Close the connection
        Utility.closedbConnection();
        // flush extent report
        report.endTest(test);
        report.flush();
        System.out.println("close connection to db successfully");
        //start html report after test end
        Utility.startHtmlReport(System.getProperty("user.dir"),"ExtentReportResults.html");
    }
}

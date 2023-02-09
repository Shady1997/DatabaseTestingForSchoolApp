package testcases;

import com.github.javafaker.Faker;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Utility;

import java.io.IOException;
import java.sql.SQLException;
public class TestBase {
    protected static ExtentReports report;
    protected static ExtentTest test;
    protected static Faker faker=new Faker();
    String FIRSTNAME = faker.name().firstName();
    String LASTNAME = faker.name().lastName();
    String BIRTHDATE = "2008-07-04";
    String ADDRESS = faker.address().fullAddress();
    String EMAIL = faker.internet().emailAddress();
    String CLASSNAME=faker.company().name();
    String CLASS_TEACHER=faker.name().fullName();
    String ENROLMENT_DATE="2009-07-04";
    static int CLASS_ID=0;
    static int STUDENT_ID=0;
    static int ENROLMENT_ID=0;
    @BeforeSuite
    public void defineSuiteElements() {
        // extend report
         report = new ExtentReports(System.getProperty("user.dir")+"/STMDatabaseTestReport.html");
         test = report.startTest("STM School Database Testing Module");
    }
    @BeforeTest
    public void setUp() throws SQLException {
        // Connect to the database
        Utility.createDBConnection("school", "3306","root","");
        Utility.createStatement();
        System.out.println("stating db connection successfully");
    }
    @AfterTest
    public void closedbConnection () throws SQLException {
        // Close the connection
        Utility.closedbConnection();

        System.out.println("close connection to db successfully");
    }
    @AfterSuite
    public void tearDown() throws IOException {
        // flush extent report
        report.endTest(test);
        report.flush();

        //start html report after test end
        Utility.startHtmlReport(System.getProperty("user.dir"),"STMDatabaseTestReport.html");
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, result.getName()+" failed with the following error: "+result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName());
        } else
            test.log(LogStatus.SKIP, result.getName());
    }
}

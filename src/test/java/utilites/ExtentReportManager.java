package utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;


public void onStart(ITestContext testContext){
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    repName = "Test-Report"+timeStamp+".html";
    sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
   sparkReporter.config().setDocumentTitle("Rest Assured");
   sparkReporter.config().setReportName("api");
   sparkReporter.config().setTheme(Theme.DARK);


    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);
    extent.setSystemInfo("Created By", "Sisay");
    extent.setSystemInfo("User Name", System.getProperty("user.name"));
    extent.setSystemInfo("Operating system", System.getProperty("os.name"));
    extent.setSystemInfo("Enviroment", "QA");
    extent.setSystemInfo("Autmation Type", "API");

}
public void onTestSuccess(ITestResult result){
    test=extent.createTest(result.getName());
    test.assignCategory(result.getMethod().getGroups());
    test.createNode(result.getName());
    test.log(Status.PASS,"Test Passed");
}
    public void onTestFailure(ITestResult result){
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        test.log(Status.FAIL,"Test Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
}
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());


    }
    public void onFinish(ITestContext testContext){
    extent.flush();
}
}
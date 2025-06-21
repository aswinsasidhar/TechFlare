package Tech.Tech_flare;


	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	public class ExtentTestngITestListener implements ITestListener {

	    private static ExtentReports extent;
	    public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	    @Override
	    public void onStart(ITestContext context) {
	        String className = context.getCurrentXmlTest().getClasses().get(0).getName();
	        String simpleName = className.substring(className.lastIndexOf('.') + 1);
	        extent = ExtentReporterManager.createInstance(simpleName);
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
	        testThread.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        testThread.get().log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        testThread.get().log(Status.FAIL, "Test Failed");
	        testThread.get().log(Status.FAIL, result.getThrowable());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        testThread.get().log(Status.SKIP, "Test Skipped");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
	}



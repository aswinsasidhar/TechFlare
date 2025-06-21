package Tech.Tech_flare;


	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.io.File;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReporterManager {

	    public static ExtentReports createInstance(String className) {
	        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	        String reportPath = System.getProperty("user.dir") + "/Tech_flare/target" + className + "_" + timestamp + ".html";

	        File reportDir = new File(System.getProperty("user.dir") + "/Tech_flare/target/reports");
	        if (!reportDir.exists()) {
	            reportDir.mkdirs();
	        }

	        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	        spark.config().setTheme(Theme.STANDARD);
	        spark.config().setDocumentTitle("Automation Test Report");
	        spark.config().setReportName(className + " Test Report");

	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(spark);
	        extent.setSystemInfo("OS", "Windows");
	        extent.setSystemInfo("Tester", "Aswin Sasidhar");

	        return extent;
	    }
	}

	



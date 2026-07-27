package com.runner;

import com.base.BaseClass;
import com.listener.ExtentReport_Test;
import com.listener.ITestListenerClass;
import com.pageobjectmanager.PageObjectManager;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(ITestListenerClass.class)
public class NaukriRunner extends BaseClass {

    @BeforeSuite
    public void reportStart(){
        extentReportStart(PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("start"));
    }

    @AfterSuite
    public void reportEnd() throws IOException {
        extentReportTearDown(PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("end"));
    }

    @BeforeTest
    public void propertySetting() {
        browserLaunch(PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("browserName"));
        launchURL(PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("appURL"));
    }

    @Test(priority = 1)
    public void validLoginPage() {
        ExtentReport_Test.extenttest = extentReports.createTest("Login Test" + " : " + Thread.currentThread().
                getStackTrace()[1].getMethodName().toString()).info("Naukri Web Application Testing");
        PageObjectManager.getPageObjectManager().getLoginPage().verifyLoginPage();
    }

    @Test(priority = 2, dependsOnMethods = "validLoginPage")
    public void validProfileUpdatePage() {
        ExtentReport_Test.extenttest = extentReports.createTest("Profile Update Page" + " : " + Thread.currentThread().
                getStackTrace()[2].getMethodName().toString()).info("Naukri Web Application Testing");
        PageObjectManager.getPageObjectManager().getProfileUpdatePage().verifyProfileUpdatePage();
    }

    @Test(priority = 3, dependsOnMethods = "validProfileUpdatePage")
    public void validProfileSummaryPage() {
        ExtentReport_Test.extenttest = extentReports
                .createTest("Profile Summary Page")
                .info("Naukri Web Application Testing");
        PageObjectManager.getPageObjectManager().getProfileSummaryPage().verifyProfileSummaryPage();
    }

    @AfterTest
    public void browserTerminate() {
        terminateBrowser();
    }



}

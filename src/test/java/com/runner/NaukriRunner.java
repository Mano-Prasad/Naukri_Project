package com.runner;

import com.base.BaseClass;
import com.pageobjectmanager.PageObjectManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NaukriRunner extends BaseClass {

    @BeforeTest
    public void propertySetting() {
        browserLaunch(PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("browserName"));
        launchURL(PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("appURL"));
    }

    @Test(priority = 1)
    public void validLoginPage() {
        PageObjectManager.getPageObjectManager().getLoginPage().verifyLoginPage();
    }

    @Test(priority = 2, dependsOnMethods = "validLoginPage")
    public void validProfileUpdatePage() {
        PageObjectManager.getPageObjectManager().getProfileUpdatePage().verifyProfileUpdatePage();
    }

    @Test(priority = 3, dependsOnMethods = "validProfileUpdatePage")
    public void validProfileSummaryPage() {
        PageObjectManager.getPageObjectManager().getProfileSummaryPage().verifyProfileSummaryPage();
    }

    @AfterTest
    public void browserTerminate() {
        terminateBrowser();
    }


}

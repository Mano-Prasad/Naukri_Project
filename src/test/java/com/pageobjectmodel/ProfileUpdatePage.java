package com.pageobjectmodel;

import com.base.BaseClass;
import com.interfaceelements.ProfileUpdateInterfaceElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.pageobjectmanager.PageObjectManager;
import org.testng.Assert;

import java.time.Duration;

public class ProfileUpdatePage extends BaseClass implements ProfileUpdateInterfaceElements {

    @FindBy(xpath = menuButton_xpath)
    WebElement menuButton;

    @FindBy(linkText = viewUpdateProfile_linkText)
    WebElement viewUpdateProfile;

    @FindBy(css = profileName_css)
    WebElement actualProfileName;

    public ProfileUpdatePage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyProfileUpdatePage() {
        implicitWait(Duration.ofSeconds(60));
        clickElement(menuButton);
        clickElement(viewUpdateProfile);
        String expectedProfileName = PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("profileName");
        Assert.assertEquals(getText(actualProfileName), expectedProfileName );
    }


}

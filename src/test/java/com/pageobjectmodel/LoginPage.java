package com.pageobjectmodel;

import com.base.BaseClass;
import com.interfaceelements.LoginPageInterfaceElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.pageobjectmanager.PageObjectManager;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BaseClass implements LoginPageInterfaceElements {

    @FindBy(xpath = login_xpath)
    WebElement login;

    @FindBy(xpath= username_xpath)
    WebElement username;

    @FindBy(xpath=password_xpath)
    WebElement password;

    @FindBy(xpath=loginButton_xpath)
    WebElement loginButton;

    @FindBy(xpath=profileName_xpath)
    WebElement profileName;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void verifyLoginPage(){
        implicitWait(Duration.ofSeconds(60));
        clickElement(login);
        passInput(username, PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("emailID"));
        passInput(password, PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("password"));
        clickElement(loginButton);
        String expectedName = PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("profileName");
        explicitWait(profileName,"visibilityOf", Duration.ofSeconds(30));
        Assert.assertEquals(getText(profileName), expectedName);
    }

}

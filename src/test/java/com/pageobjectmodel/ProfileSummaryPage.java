package com.pageobjectmodel;

import com.base.BaseClass;
import com.interfaceelements.ProfileSummaryInterfaceElements;
import com.pageobjectmanager.PageObjectManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProfileSummaryPage extends BaseClass implements ProfileSummaryInterfaceElements {

    @FindBy(xpath = profileSummary_xpath)
    WebElement profileSummary;

    @FindBy(xpath = editIcon_xpath)
    WebElement editIcon;

    @FindBy(css = summaryTextArea_css)
    WebElement summaryTextArea;

    @FindBy(xpath = saveButton_xpath)
    WebElement saveButton;

    public ProfileSummaryPage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyProfileSummaryPage() {
        implicitWait(Duration.ofSeconds(30));
        clickElement(profileSummary);
        clickElement(editIcon);
        clearText(summaryTextArea);
        passInput(summaryTextArea, PageObjectManager.getPageObjectManager().getFileReaderManager().getConfigProperty("profileSummary"));
        clickElement(saveButton);

    }
}
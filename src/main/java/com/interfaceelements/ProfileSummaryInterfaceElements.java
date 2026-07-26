package com.interfaceelements;

public interface ProfileSummaryInterfaceElements {
    String profileSummary_xpath = "//li[@class='collection-item typ-14Medium']//child::span[text()='Profile summary']";
    String editIcon_xpath = "//div[@class='profileSummary']//descendant::span[@class='edit icon']";
    String summaryTextArea_css = "textarea[name='profileSummary']";
    String saveButton_xpath = "//button[text()='Save']";

}

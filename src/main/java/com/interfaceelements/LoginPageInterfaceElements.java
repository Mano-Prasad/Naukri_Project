package com.interfaceelements;

public interface LoginPageInterfaceElements {

    String login_xpath = "//a[text()='Login']";
    String username_xpath= "//input[@aria-label='Email ID / Username']";
    String password_xpath= "//input[@aria-label='Password']";
    String loginButton_xpath = "//button[contains(@class,'loginButton')]";
    String profileName_xpath = "//div[@class='user-details-inner']//descendant::div[@class='info__heading']";


}

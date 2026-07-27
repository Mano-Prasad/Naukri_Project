package com.pageobjectmanager;

import com.pageobjectmodel.LoginPage;
import com.pageobjectmodel.ProfileSummaryPage;
import com.pageobjectmodel.ProfileUpdatePage;
import com.utility.ExcelUtility2;
import com.utility.FileReaderManager;

public class PageObjectManager {

    private static PageObjectManager pageObjectManager;
    private FileReaderManager fileReaderManager;
    private LoginPage loginPage;
    private ProfileUpdatePage profileUpdatePage;
    private ProfileSummaryPage profileSummaryPage;
    private ExcelUtility2 excelUtility2;

    public static PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager();
        }
        return pageObjectManager;
    }

    public FileReaderManager getFileReaderManager() {
        if(fileReaderManager == null){
            fileReaderManager = new FileReaderManager();
        }
        return fileReaderManager;
    }

    public LoginPage getLoginPage() {
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ProfileUpdatePage getProfileUpdatePage() {
        if(profileUpdatePage == null){
            profileUpdatePage = new ProfileUpdatePage();
        }
        return profileUpdatePage;
    }

    public ProfileSummaryPage getProfileSummaryPage(){
        if(profileSummaryPage == null){
            profileSummaryPage = new ProfileSummaryPage();
        }
        return profileSummaryPage;
    }

    public ExcelUtility2 getExcelUtility2(){
        if(excelUtility2 == null){
            excelUtility2 = new ExcelUtility2();
        }
        return excelUtility2;
    }

}

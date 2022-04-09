package com.qa.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class UIBaseTest extends UIBasePage {

    private static final String URL = "https://rahulshettyacademy.com/AutomationPractice/";

    @BeforeTest
    public void openBrowser(){
        initWebDriver();
        openPage(URL);
        maximizeWindow();
    }

    @AfterTest
    public void closeBrowser(){
        quitWebDriver();
    }
}

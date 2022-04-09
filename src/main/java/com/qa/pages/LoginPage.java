package com.qa.pages;

import com.qa.base.UIBasePage;
import org.openqa.selenium.By;

public class LoginPage extends UIBasePage {
    private static final By PAGE_TITLE = By.xpath("//h3[text()=\"Login\"]");

    public boolean isLoginPageLoaded(){
        return isVisible(PAGE_TITLE);
    }
}

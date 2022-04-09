package com.qa.pages;

import com.qa.base.UIBasePage;
import org.openqa.selenium.By;

public class HomePage extends UIBasePage {
    private static final By LOGIN_BUTTON = By.xpath("//a[text()=\"Login\"]");

    public HomePage clickLoginLink(){
        clickElement(LOGIN_BUTTON);
        return this;
    }
}

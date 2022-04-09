package com.qa.pages;

import com.qa.base.UIBasePage;
import org.openqa.selenium.By;

public class FeaturedCoursesPage extends UIBasePage {
    private static final By PAGE_TITLE = By.xpath("//h3[text()=\"An Academy to learn Everything about Testing\"]");
    private static final By NO_THANKS_LINK = By.xpath("//button[text()=\"NO THANKS\"]");

    public FeaturedCoursesPage clickNoThanksLink(){
        clickElement(NO_THANKS_LINK);
        return this;
    }

    public boolean isFeaturedCoursesPageLoaded(){
        return isVisible(PAGE_TITLE);
    }
}

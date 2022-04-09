package com.qa.tests;

import com.qa.base.UIBaseTest;
import com.qa.pages.FeaturedCoursesPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.PracticePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskOneTest extends UIBaseTest {
    private final static int RADIO_BUTTON_INDEX = 2;
    private final static int SUGGESTION_LIST_INDEX = 1;
    private final static int DROPDOWN_LIST_INDEX = 3;
    private final static int SUM_OF_WEB_TABLE = 235;
    private final static int SUM_OF_FIXED_HEADER_WEB_TABLE = 296;
    private final static int INDEX_OF_WEB_TABLE_COLUMN = 2;
    private final static int INDEX_OF_FIXED_HEADER_TABLE_COLUMN = 3;
    private final static String COUNTRY_TEXT = "sr";
    private final static String DROP_DOWN_TEXT = "Option3";
    private final static String NAME_FOR_ALERT = "Vimukthi";
    private final static String ALERT_TEXT = "Hello Vimukthi, share this practice page and share your knowledge";
    private final static String CONFIRM_TEXT = "Hello Vimukthi, Are you sure you want to confirm?";
    private final static String CURRENT_URL = "https://rahulshettyacademy.com/AutomationPractice/#top";
    PracticePage practicePage = new PracticePage();
    FeaturedCoursesPage featuredCoursesPage = new FeaturedCoursesPage();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Description("Verify the UI Flows")
    @Test
    public void uiFlowsTest(){
        verifyPageLoad();
        selectAndVerifyRadioButton();
        selectFromSuggestionClassExample();
        selectFromDropDown();
        selectAndVerifyCheckBox();
        clickSwitchWindowAndVerify();
        clickSwitchTabAndVerify();
        clickAlertAndVerify();
        clickConfirmAndVerify();
        clickHideShowButtonsAndVerify();
        sumPriceInWebTable();
        sumPriceInFixedHeaderWebTable();
        hoverAndSelectOption();
        clickLinkOnIFrame();
    }

    @Step("Verify the home page is loaded")
    private void verifyPageLoad(){
        Assert.assertTrue(practicePage
                .isPracticePageLoaded());
    }

    @Step("Select the radio button and verify")
    private void selectAndVerifyRadioButton(){
        Assert.assertTrue(practicePage
                .clickRadioButton(RADIO_BUTTON_INDEX)
                .isRadioButtonSelected(RADIO_BUTTON_INDEX));
    }

    @Step("Select from the suggestion class")
    private void selectFromSuggestionClassExample(){
        Assert.assertTrue(practicePage
                .fillSuggestionClassField(COUNTRY_TEXT)
                .clickSuggestionsByIndex(SUGGESTION_LIST_INDEX));
    }

    @Step("Select from the dropdown and verify")
    private void selectFromDropDown(){
        Assert.assertEquals(practicePage
                .clickDropDown(DROPDOWN_LIST_INDEX)
                .getSelectedText(), DROP_DOWN_TEXT);
    }

    @Step("Select and verify the checkbox")
    private void selectAndVerifyCheckBox(){
        Assert.assertTrue(practicePage
                .clickCheckBox()
                .isCheckBoxSelected());
    }

    @Step("Click button, switch to new window and verify the page load")
    private void clickSwitchWindowAndVerify(){
        practicePage
                .clickOpenWindowButton()
                .getParentWindowHandle()
                .switchToChildWindow();
        Assert.assertTrue(featuredCoursesPage
                .clickNoThanksLink()
                .isFeaturedCoursesPageLoaded());
        practicePage
                .switchToParentWindow();
    }

    @Step("Click button, switch to new tab and verify the page load")
    private void clickSwitchTabAndVerify(){
        practicePage
                .clickOpenTabButton()
                .getParentWindowHandle()
                .switchToChildWindow();
        homePage
                .clickLoginLink();
        Assert.assertTrue(loginPage
                .isLoginPageLoaded());
        practicePage
                .switchToParentWindow();
    }

    @Step("Click button and verify the alert")
    private void clickAlertAndVerify(){
        Assert.assertEquals(practicePage
                .enterNameToAlert(NAME_FOR_ALERT)
                .clickAlertButton()
                .getAlertText(), ALERT_TEXT);
        practicePage.acceptAlert();
    }

    @Step("Click button and verify the confirm popup")
    private void clickConfirmAndVerify(){
        Assert.assertEquals(practicePage
                .enterNameToAlert(NAME_FOR_ALERT)
                .clickConfirmButton()
                .getAlertText(), CONFIRM_TEXT);
        practicePage.acceptAlert();
    }

    @Step("Click hide/ show buttons and verify the visibility of input field")
    private void clickHideShowButtonsAndVerify(){
        Assert.assertTrue(practicePage
                .isHideShowInputVisible());
        Assert.assertFalse(practicePage
                .clickHideButton()
                .isHideShowInputVisible());
        Assert.assertTrue(practicePage
                .clickShowButton()
                .isHideShowInputVisible());
    }

    @Step("Calculate the sum of the price from web table")
    private void sumPriceInWebTable(){
        Assert.assertEquals(practicePage
                .getSumFromTable(INDEX_OF_WEB_TABLE_COLUMN), SUM_OF_WEB_TABLE);
    }

    @Step("Calculate the sum of the price from Fixed header web table")
    private void sumPriceInFixedHeaderWebTable(){
        Assert.assertEquals(practicePage
                .getSumFromFixedHeaderTable(INDEX_OF_FIXED_HEADER_TABLE_COLUMN), SUM_OF_FIXED_HEADER_WEB_TABLE);
    }

    @Step("Hover and select option from dropdown list")
    private void hoverAndSelectOption(){
        Assert.assertEquals(practicePage
                .hoverAndSelectItemFromList()
                .getUrl(), CURRENT_URL);
    }

    @Step("Verify page on IFrame")
    private void clickLinkOnIFrame(){
        practicePage
                .switchIFrame();
        homePage
                .clickLoginLink();
        Assert.assertTrue(loginPage
                .isLoginPageLoaded());
    }
}

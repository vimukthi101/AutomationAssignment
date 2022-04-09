package com.qa.pages;

import com.qa.base.UIBasePage;
import org.openqa.selenium.By;

public class PracticePage extends UIBasePage {
    private static final By PAGE_TITLE = By.xpath("//h1[text()=\"Practice Page\"]");
    private static final By RADIO_BUTTON_LOCATOR = By.name("radioButton");
    private static final By SUGGESTION_CLASS_INPUT_LOCATOR = By.id("autocomplete");
    private static final By SUGGESTION_CLASS_OPTIONS_LOCATOR = By.id("ui-id-1");
    private static final By DROPDOWN_LOCATOR = By.id("dropdown-class-example");
    private static final By CHECKBOX_LOCATOR = By.xpath("//input[@name=\"checkBoxOption2\"]");
    private static final By OPEN_WINDOW_BUTTON_LOCATOR = By.id("openwindow");
    private static final By OPEN_TAB_BUTTON_LOCATOR = By.id("opentab");
    private static final By NAME_INPUT_LOCATOR = By.id("name");
    private static final By ALERT_BUTTON_LOCATOR = By.id("alertbtn");
    private static final By CONFIRM_BUTTON_LOCATOR = By.id("confirmbtn");
    private static final By HIDE_BUTTON_LOCATOR = By.id("hide-textbox");
    private static final By SHOW_BUTTON_LOCATOR = By.id("show-textbox");
    private static final By HIDE_SHOW_INPUT_LOCATOR = By.id("displayed-text");
    private static final By WEB_TABLE_LOCATOR = By.xpath("//table[@id=\"product\" and @name=\"courses\"]/tbody");
    private static final By WEB_TABLE_FIXED_HEADER_LOCATOR = By.xpath("//div[@class=\"tableFixHead\"]//table[@id=\"product\"]/tbody");
    private static final By MOUSE_HOVER_BUTTON_LOCATOR = By.id("mousehover");
    private static final By MOUSE_HOVER_SUB_MENU_OPTION_LOCATOR = By.xpath("//div[@class=\"mouse-hover-content\"]/a[@href=\"#top\"]");
    private static final By IFRAME_LOCATOR = By.id("courses-iframe");

    public boolean isPracticePageLoaded(){
        return isVisible(PAGE_TITLE);
    }

    public PracticePage clickRadioButton(int index){
        clickRadioButtonByIndex(RADIO_BUTTON_LOCATOR, index);
        return this;
    }

    public boolean isRadioButtonSelected(int index){
        return isRadioButtonSelected(RADIO_BUTTON_LOCATOR, index);
    }

    public PracticePage fillSuggestionClassField(String country){
        typeText(SUGGESTION_CLASS_INPUT_LOCATOR, country);
        return this;
    }

    public boolean clickSuggestionsByIndex(int index){
        return selectFromSuggestions(SUGGESTION_CLASS_OPTIONS_LOCATOR, index);
    }

    public PracticePage clickDropDown(int index){
        selectFromListByIndex(DROPDOWN_LOCATOR, index);
        return this;
    }

    public String getSelectedText(){
        return getSelectedTextFromDropDown(DROPDOWN_LOCATOR);
    }

    public PracticePage clickCheckBox(){
        clickElement(CHECKBOX_LOCATOR);
        return this;
    }

    public boolean isCheckBoxSelected(){
        return isCheckBoxSelected(CHECKBOX_LOCATOR);
    }

    public PracticePage clickOpenWindowButton(){
        clickElement(OPEN_WINDOW_BUTTON_LOCATOR);
        return this;
    }

    public PracticePage clickOpenTabButton(){
        clickElement(OPEN_TAB_BUTTON_LOCATOR);
        return this;
    }

    public PracticePage enterNameToAlert(String name){
        typeText(NAME_INPUT_LOCATOR, name);
        return this;
    }

    public PracticePage clickAlertButton(){
        clickElement(ALERT_BUTTON_LOCATOR);
        return this;
    }

    public PracticePage clickConfirmButton(){
        clickElement(CONFIRM_BUTTON_LOCATOR);
        return this;
    }

    public PracticePage clickHideButton(){
        clickElement(HIDE_BUTTON_LOCATOR);
        return this;
    }

    public PracticePage clickShowButton(){
        clickElement(SHOW_BUTTON_LOCATOR);
        return this;
    }

    public boolean isHideShowInputVisible(){
        return isVisible(HIDE_SHOW_INPUT_LOCATOR);
    }

    public int getSumFromTable(int columnIndex){
        return getPriceSumInTable(WEB_TABLE_LOCATOR, columnIndex);
    }

    public int getSumFromFixedHeaderTable(int columnIndex){
        return getPriceSumInTable(WEB_TABLE_FIXED_HEADER_LOCATOR, columnIndex);
    }

    public PracticePage hoverAndSelectItemFromList(){
        hoverToElement(MOUSE_HOVER_BUTTON_LOCATOR, MOUSE_HOVER_SUB_MENU_OPTION_LOCATOR);
        return this;
    }

    public PracticePage switchIFrame(){
        scrollToElement(IFRAME_LOCATOR);
        switchToIFrame(IFRAME_LOCATOR);
        return this;
    }
}

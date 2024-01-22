package com.example.demo.accommodiq.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    WebDriver driver;

    @FindBy(css = "h4")
    WebElement searchResultsSubTitle;

    @FindBy(css = "img[ng-reflect-router-link='/my-accommodations']")
    WebElement myAccommodationsButton;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageIsReady() {
        return (new WebDriverWait(driver, 10)).until(driver -> searchResultsSubTitle.getText().equals("Book your next stay at one of our properties"));
    }

    public void clickOnMyAccommodationsButton() {
        myAccommodationsButton.click();
    }
}

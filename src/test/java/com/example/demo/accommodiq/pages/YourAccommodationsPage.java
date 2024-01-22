package com.example.demo.accommodiq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourAccommodationsPage {

    WebDriver driver;

    @FindBy(css = "h4")
    WebElement yourAccommodationsSubTitle;

    public YourAccommodationsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageIsReady() {
        return (new WebDriverWait(driver, 10)).until(driver -> yourAccommodationsSubTitle.getText().equals("View, update or delete your accommodations"));
    }

    public void clickOnAccommodationCardAvailabilityIcon(int index) {
        WebElement accommodationCard = driver.findElements(new By.ByXPath("//app-accommodation-card")).get(index);
        WebElement availabilityIcon = accommodationCard.findElement(new By.ByCssSelector("i[ng-reflect-router-link='/accommodation-availability-pr']"));
        availabilityIcon.click();
    }
}

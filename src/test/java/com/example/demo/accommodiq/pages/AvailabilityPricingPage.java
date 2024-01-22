package com.example.demo.accommodiq.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AvailabilityPricingPage {
    WebDriver driver;
    @FindBy(css = "h2")
    WebElement availabilityPricingSubTitle;

    @FindBy(css = "#cancellation-deadline")
    WebElement cancellationDeadlineInput;

    @FindBy(css = ".p-checkbox-box")
    WebElement pricePerGuestCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement updateButton;

    @FindBy(xpath = "//button[span[text()='Add']]")
    WebElement addAvailabilityButton;

    @FindBy(css = "input[placeholder='Select the Range']")
    WebElement availabilityRangeInput;

    @FindBy(css = "#price")
    WebElement priceInput;

    @FindBy(css = "tbody[role='rowgroup']")
    WebElement availabilityTable;

    @FindBy(css = "p-button[ng-reflect-label='X']")
    WebElement deleteAvailabilityButton;

    @FindBy(xpath = "//div[text()=' New range overlaps with existing ranges ']")
    WebElement newRangeOverlapsWithExistingRangesError;

    @FindBy(xpath = "//div[text()=' You need to choose dates and price. ']")
    WebElement youNeedToChooseDatesAndPriceError;

    @FindBy(xpath = "//div[contains(@class, 'p-toast-detail')]")
    WebElement toastMessage;

    public AvailabilityPricingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageIsReady() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(availabilityPricingSubTitle, "Update availability of your apartment"));
    }

    public void setCancellationDeadline(String cancellationDeadline) {
        cancellationDeadlineInput.clear();
        cancellationDeadlineInput.click();
        cancellationDeadlineInput.sendKeys(cancellationDeadline);
    }

    public void clickOnPricePerGuestCheckbox() {
        pricePerGuestCheckbox.click();
    }

    public void clickOnUpdateButton() {
        updateButton.click();
    }

    public void clickOnAddAvailabilityButton() {
        addAvailabilityButton.click();
    }

    public void setAvailabilityRange(int firstNumber, int secondNumber) {
        availabilityRangeInput.click();
        WebElement firstElement = getAvailabilityRangeElement(firstNumber);
        WebElement secondElement = getAvailabilityRangeElement(secondNumber);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(firstElement));
        firstElement.click();
        secondElement.click();
    }

    public void setPrice(String price) {
        priceInput.clear();
        priceInput.click();
        priceInput.sendKeys(price);
    }

    public boolean availabilityTableIsDisplayed() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(availabilityTable)).isDisplayed();
    }

    public boolean availabilityTableContains(String startDate, String endDate, String price) {
        WebElement availabilityRow = availabilityTable.findElement(By.xpath("//tr[td[text()='" + startDate + "'] and td[text()='" + endDate + "'] and td[text()='" + price + "']]"));
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(availabilityRow)).isDisplayed();
    }

    public void clickOnDeleteAvailabilityButton() {
        deleteAvailabilityButton.click();
    }

    public boolean newRangeOverlapsWithExistingRangesErrorIsDisplayed() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(newRangeOverlapsWithExistingRangesError)).isDisplayed();
    }

    public boolean youNeedToChooseDatesAndPriceErrorIsDisplayed() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(youNeedToChooseDatesAndPriceError)).isDisplayed();
    }

    public boolean toastMessageIsDisplayed(String message) {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(toastMessage, message));
    }

    public boolean toastMessageHasExpired() {
        return new WebDriverWait(driver, 11).until(ExpectedConditions.invisibilityOfElementLocated(By.className("p-toast-message")));
    }

    private WebElement getAvailabilityRangeElement(int number) {
        return driver.findElement(By.xpath("//table/tbody/tr/td/span[text()='" + number + "']"));
    }
}

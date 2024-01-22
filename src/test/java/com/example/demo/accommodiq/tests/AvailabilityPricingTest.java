package com.example.demo.accommodiq.tests;

import com.example.demo.accommodiq.pages.AvailabilityPricingPage;
import com.example.demo.accommodiq.pages.LoginPage;
import com.example.demo.accommodiq.pages.SearchPage;
import com.example.demo.accommodiq.pages.YourAccommodationsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AvailabilityPricingTest extends TestBase {

    public static String username = "john.doe@example.com";
    public static String password = "123";
    public static int accommodationCardIndex = 2;
    public static String cancellationDeadline = "2";
    public static int availabilityRangeStart = 25;
    public static int availabilityRangeEnd = 28;
    public static int availabilityRangeStartInvalid = 10;
    public static int availabilityRangeEndInvalid = 11;
    public static String price = "500";
    public static String priceInvalid = "-1";
    public static String availabilityStartDate = " 25/01/2024 ";
    public static String availabilityEndDate = " 28/01/2024 ";
    public static int overlappingAvailabilityRangeStart = 26;
    public static int overlappingAvailabilityRangeEnd = 27;
    public static String accommodationBookingUpdatedMessage = "Accommodation booking details updated successfully.";
    public static String newAvailabilityAddedSuccessfullyMessage = "Availability added successfully";
    public static String availabilityDeletedSuccessfullyMessage = "Availability range removed";
    public static String cancellationDeadlineInvalid = "-1";
    public static String cancellationDeadlineInvalidMessage = "Form is invalid. Please check the entered data.";

    @Test
    public void testAvailabilityPricing() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.pageIsReady());
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.pageIsReady());
        searchPage.clickOnMyAccommodationsButton();

        YourAccommodationsPage yourAccommodationsPage = new YourAccommodationsPage(driver);
        Assert.assertTrue(yourAccommodationsPage.pageIsReady());
        yourAccommodationsPage.clickOnAccommodationCardAvailabilityIcon(accommodationCardIndex);

        AvailabilityPricingPage availabilityPricingPage = new AvailabilityPricingPage(driver);
        Assert.assertTrue(availabilityPricingPage.pageIsReady());

        availabilityPricingPage.setCancellationDeadline(cancellationDeadlineInvalid);
        availabilityPricingPage.clickOnUpdateButton();
        Assert.assertTrue(availabilityPricingPage.toastMessageIsDisplayed(cancellationDeadlineInvalidMessage));
        Assert.assertTrue(availabilityPricingPage.toastMessageHasExpired());
        
        availabilityPricingPage.setCancellationDeadline(cancellationDeadline);
        availabilityPricingPage.clickOnUpdateButton();
        Assert.assertTrue(availabilityPricingPage.toastMessageIsDisplayed(accommodationBookingUpdatedMessage));
        Assert.assertTrue(availabilityPricingPage.toastMessageHasExpired());

        availabilityPricingPage.setAvailabilityRange(availabilityRangeStartInvalid,availabilityRangeEndInvalid);
        availabilityPricingPage.setPrice(priceInvalid);
        availabilityPricingPage.clickOnAddAvailabilityButton();
        Assert.assertTrue(availabilityPricingPage.youNeedToChooseDatesAndPriceErrorIsDisplayed());

        availabilityPricingPage.setAvailabilityRange(availabilityRangeStart,availabilityRangeEnd);
        availabilityPricingPage.setPrice(price);
        availabilityPricingPage.clickOnAddAvailabilityButton();
        Assert.assertTrue(availabilityPricingPage.toastMessageIsDisplayed(newAvailabilityAddedSuccessfullyMessage));
        Assert.assertTrue(availabilityPricingPage.toastMessageHasExpired());
        Assert.assertTrue(availabilityPricingPage.availabilityTableIsDisplayed());
        Assert.assertTrue(availabilityPricingPage.availabilityTableContains(availabilityStartDate,availabilityEndDate,price));

        availabilityPricingPage.setAvailabilityRange(overlappingAvailabilityRangeStart,overlappingAvailabilityRangeEnd);
        availabilityPricingPage.setPrice(price);
        availabilityPricingPage.clickOnAddAvailabilityButton();
        Assert.assertTrue(availabilityPricingPage.newRangeOverlapsWithExistingRangesErrorIsDisplayed());

        availabilityPricingPage.clickOnDeleteAvailabilityButton();
        Assert.assertTrue(availabilityPricingPage.toastMessageIsDisplayed(availabilityDeletedSuccessfullyMessage));
    }
}

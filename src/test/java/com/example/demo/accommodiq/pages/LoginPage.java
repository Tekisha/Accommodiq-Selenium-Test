package com.example.demo.accommodiq.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    public static String URL = "http://localhost:4200/login";

    @FindBy(css = "h1")
    WebElement loginPageTitle;

    @FindBy(css = "#username")
    WebElement usernameInput;

    @FindBy(css = "#password")
    WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL);

        PageFactory.initElements(driver, this);
    }

    public boolean pageIsReady() {
        return (new WebDriverWait(driver, 10)).until(driver -> loginPageTitle.getText().equals("Login"));
    }

    public void setUsername(String username) {
        usernameInput.clear();
        usernameInput.click();
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.click();
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }
}

package org.hotel.search.nh.pages;

import java.util.List;
import org.hotel.search.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final String URL = "https://www.nh-hotels.com/es";

    @FindBy(id = "location2-sb")
    private WebElement destinationInput;

    @FindBy(id = "calendar-checkin-sb")
    private WebElement checkinInput;
    @FindBy(id = "calendar-checkout-sb")
    private WebElement checkoutInput;
    @FindBy(id = "optionRooms")
    private WebElement ocupationInput;
    @FindBy(id = "btn-search")
    private WebElement searchButton;
    @FindBy(id = "consent-prompt-accept")
    private WebElement acceptCookiesButton;
    @FindBy(className = "autocomplete-results-list")
    private List<WebElement> destinationList;



    
    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(URL);
    }

    public void setCheckout(String data) {
        checkoutInput.sendKeys(data);
        String newValue = checkoutInput.getAttribute("value");
        if (!data.equals(newValue)) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='" + data + "';", checkoutInput);
        }
    }

    public void setCheckin(String data) {
        checkinInput.sendKeys(data);
        String newValue = checkinInput.getAttribute("value");
        if (!data.equals(newValue)) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='" + data + "';", checkinInput);
        }
    }

    public void setDestination(String data) {
        destinationInput.sendKeys(data);
        if (!destinationList.isEmpty()) {
            WebElement element = destinationList.get(0).findElements(By.tagName("li")).get(0);
            element.click();
        } else {
            System.out.println("No se encontraron destinos en la lista de autocompletar.");
        }
    }

    public void setOcupation(String ocupation) {
        ocupationInput.sendKeys(ocupation);


    }

    public void search() {
        searchButton.click();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }
}

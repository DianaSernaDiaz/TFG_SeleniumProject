package org.hotel.search.eurostars.pages;

import static org.hotel.search.common.Utils.getDay;
import static org.hotel.search.common.Utils.getMonthName;
import static org.hotel.search.common.Utils.getYear;

import java.util.List;
import org.hotel.search.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final String URL = "https://www.eurostarshotels.co.uk/";

    @FindBy(id = "searchBoxEngine")
    private WebElement destinationInput;
    @FindBy(id = "optionRooms")
    private WebElement ocupationInput;
    @FindBy(id = "td-search-00")
    private WebElement searchButton;
    @FindBy(className = "sta-cookies_confirm")
    private WebElement acceptCookiesButton;
    @FindBy(className = "autocomplete-results-list")
    private List<WebElement> destinationList;


    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(URL);
    }



    public void setCheckout(String data) {

        String month = getMonthName(data);
        int year = getYear(data);
        int day = getDay(data);

        WebElement monthContainer = getSpan(month + " " + year);

        monthContainer = monthContainer.findElement(By.xpath(".."));

        WebElement button = getButton(monthContainer, day);

        button.click();

    }

    public void setCheckin(String data) {
        String month = getMonthName(data);
        int year = getYear(data);
        int day = getDay(data);

        WebElement monthContainer = getSpan(month + " " + year);

        monthContainer = monthContainer.findElement(By.xpath(".."));

        WebElement button = getButton(monthContainer, day);

        button.click();
    }

    private WebElement getButton(WebElement monthContainer, int day) {

        WebElement buttonEncontrado = null;
        boolean encontrado = false;
        List<WebElement> buttons = monthContainer.findElements(By.tagName("button"));

        for (int i = 0; i < buttons.size() && !encontrado; i++) {
            WebElement button = buttons.get(i);

            List<WebElement> spans = button.findElements(By.tagName("span"));
            for (int j = 0; j < spans.size() && !encontrado; j++) {
                WebElement span = spans.get(j);
                if (span.getText().equals(String.valueOf(day))) {
                    encontrado = true;
                    buttonEncontrado = button;
                }
            }
        }

        return buttonEncontrado;

    }

    private WebElement getSpan(String value) {
        WebElement elementoEncontrado = null;
        List<WebElement> spans = driver.findElements(By.tagName("span"));
        boolean encontrado = false;

        for (int i = 0; i < spans.size() && !encontrado; i++) {
            WebElement element = spans.get(i);
            try {
                if (element.getText().equals(value)) {
                    elementoEncontrado = element;
                    encontrado = true;
                }
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return elementoEncontrado;
    }

    public void setDestination(String data) {
        destinationInput.click();
        destinationInput.sendKeys(data);

        WebElement spanElement = driver.findElement(By.xpath("//span[text()='Destination']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement divBelowSpan = (WebElement) js.executeScript(
            "return arguments[0].nextElementSibling.querySelector('div');",
            spanElement
        );

        divBelowSpan.click();
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

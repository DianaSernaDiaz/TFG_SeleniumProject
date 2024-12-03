package org.hotel.search.melia.pages;

import static org.hotel.search.common.Utils.getDay;
import static org.hotel.search.common.Utils.getMonthName;
import static org.hotel.search.common.Utils.getYear;
import static org.hotel.search.common.Utils.waits;

import java.util.ArrayList;
import java.util.List;
import org.hotel.search.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String URL = "https://www.melia.com/en";

    @FindBy(id = "suggest-input")
    private WebElement destinationInput;
    @FindBy(id = "calendar-checkin-sb")
    private WebElement checkinInput;
    @FindBy(id = "calendar-checkout-sb")
    private WebElement checkoutInput;
    @FindBy(id = "optionRooms")
    private WebElement ocupationInput;
    @FindBy(xpath = "//button[contains(@class, 'c-button') and @type='submit']")
    private WebElement searchButton;
    @FindBy(id = "didomi-notice-agree-button")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//div[contains(@class, 'c-suggestion-list')]")
    private WebElement destinationList;

    @FindBy(xpath = "//button//div[contains(text(), 'Hotel, city or destination')]")
    private WebElement destinationHidden;


    @FindBy(xpath = "//div[starts-with(@class, 'scrollable_')]//div[starts-with(@class, 'b-clickable-area')]")
    private WebElement offerButton;


    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(URL);
    }





    public void setDestination(String data) {
        destinationHidden.click();
        destinationInput.sendKeys(data);
        WebElement element = destinationList.findElements(By.tagName("button")).get(0);
        element.click();
    }

    public void setOcupation(String ocupation) {
        ocupationInput.sendKeys(ocupation);


    }

    public void setCheckin(String data) {

        /*
        WebElement checkin = null;
        List<WebElement> spans = driver.findElements(By.tagName("span"));

        boolean encontrado = false;
        for (int i = 0; i < spans.size() && !encontrado; i++) {
            WebElement element = spans.get(i);
            if (element.getText().equals("Check-in")) {
                checkin = element;
                encontrado = true;
            }
        }

        checkin.click();
*/
        String month = getMonthName(data);
        int year = getYear(data);
        int day = getDay(data);

        List<String> displayedMonths = getDisplayedMonths();

        int index = -1;

        if (displayedMonths.get(0).equalsIgnoreCase(month + " " + year)) {
            index = 0;
        } else if (displayedMonths.get(1).equalsIgnoreCase(month + " " + year)) {
            index = 1;
        }


        WebElement calendar = driver.findElements(By.className("react-calendar__month-view")).get(index);

        WebElement button = getButton(calendar, day);

        button.click();

    }

    public void setCheckout(String data) {

        String month = getMonthName(data);
        int year = getYear(data);
        int day = getDay(data);

        List<String> displayedMonths = getDisplayedMonths();

        int index = -1;

        if (displayedMonths.get(0).equalsIgnoreCase(month + " " + year)) {
            index = 0;
        } else if (displayedMonths.get(1).equalsIgnoreCase(month + " " + year)) {
            index = 1;
        }

        WebElement calendar = driver.findElements(By.className("react-calendar__month-view")).get(index);

        WebElement button = getButton(calendar, day);

        button.click();

    }

    private WebElement getButton(WebElement monthContainer, int day) {

        WebElement buttonEncontrado = null;
        boolean encontrado = false;
        List<WebElement> buttons = monthContainer.findElements(By.tagName("button"));

        for (int i = 0; i < buttons.size() && !encontrado; i++) {
            WebElement button = buttons.get(i);

            List<WebElement> spans = button.findElements(By.tagName("abbr"));
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
            if (element.getText().equals(value)) {
                elementoEncontrado = element;
                encontrado = true;
            }
        }
        return elementoEncontrado;
    }

    public void search() {
        searchButton.click();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
        waits(3000);

        if (offerButton.isDisplayed()) {
            offerButton.click();
            wait.until(ExpectedConditions.invisibilityOf(offerButton));
        }
    }

    private List<String> getDisplayedMonths() {
        List<WebElement> webElements = driver.findElements(
            By.className("react-calendar__navigation__label__labelText"));
        List<String> displeyedMonths = new ArrayList<>();
        for (WebElement webElement : webElements) {
            displeyedMonths.add(webElement.getText());
        }
        return displeyedMonths;
    }
}

package org.hotel.search.eurostars.pages;

import static org.hotel.search.common.Utils.scrollDown;

import java.util.List;
import org.hotel.search.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsResultPage extends BasePage {

    @FindBy(xpath = "//div[@id='root']//ul[1]")
    private WebElement hotelsResultList;

    private String titleClassName = "title";
    private String pricePerNightClassName = "night-amount";

    public HotelsResultPage(WebDriver driver) {
        super(driver);
    }

    public void printData() {

        List<WebElement> lis = hotelsResultList.findElements(By.tagName("li"));

        for (int i = 1; i < lis.size(); i++) {
            WebElement element = lis.get(i);
            System.out.printf("Hotel: %s - ",
                element.findElement(By.tagName("img")).getAttribute("alt"));
            System.out.printf("Precio por noche: %s \n", getPriceByContent(element));
            scrollDown(driver);
        }
    }

    private String getPriceByContent(WebElement contenedor) {
        List<WebElement> spans = contenedor.findElements(By.tagName("span"));
        String salida = "";
        boolean encontrado = false;

        for (int i = 0; i < spans.size() && !encontrado; i++) {
            WebElement element = spans.get(i);
            if (element.getText().contains(" EUR")) {
                encontrado = true;
                salida = element.getText();
            }
        }
        return salida;
    }

}

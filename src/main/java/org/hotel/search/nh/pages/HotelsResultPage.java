package org.hotel.search.nh.pages;

import java.util.List;
import org.hotel.search.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelsResultPage extends BasePage {

    @FindBy(className = "m-hotel-box-main")
    private List<WebElement> hotelsResultList;

    private String titleClassName = "title";
    private String pricePerNightClassName = "night-amount";

    public HotelsResultPage(WebDriver driver){
        super(driver);
    }

    public void printData(){
        for(WebElement element: hotelsResultList){

            try {
                WebElement wprecio = wait.until(ExpectedConditions.visibilityOf(element.findElement(By.className(pricePerNightClassName))));
                String precio = wprecio.getText();

                System.out.printf("Hotel: %s - ",element.findElement(By.className(titleClassName)).getText());
                System.out.printf("Precio por noche: %s \n",precio);
            } catch (TimeoutException ignored) {
            }
        }
    }

}

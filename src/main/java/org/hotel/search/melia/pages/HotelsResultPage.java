package org.hotel.search.melia.pages;

import java.util.List;
import org.hotel.search.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsResultPage extends BasePage {

    @FindBy(xpath = "//main//div[@class='cell']")
    private List<WebElement> hotelsResultList;

    public HotelsResultPage(WebDriver driver) {
        super(driver);
    }

    public void printData() {
        for (WebElement element : hotelsResultList) {
            String title = element.findElement(By.xpath(".//span"))
                .getText();
            String price = element.findElement(By.xpath(".//span[starts-with(@class,'text-wrapper')]/div"))
                .getText();

            System.out.printf("Hotel: %s - ", title);
            System.out.printf("Precio por noche: %s \n", price);
        }
        System.out.println();
    }

}

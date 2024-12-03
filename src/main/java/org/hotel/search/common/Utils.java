package org.hotel.search.common;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {

    private static final String filePath =
        "test-output/RestAssuredReport-" + getNow() + ".png";

    public static String getMonthName(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        String month = localDate.getMonth().name().toLowerCase(); // Convierte a minúsculas
        return month.substring(0, 1).toUpperCase() + month.substring(
            1); //Pone en mayusculas la primera letra
    }

    public static int getYear(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.getYear();
    }

    public static int getDay(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.getDayOfMonth();
    }

    public static void waits(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException ignored) {
        }
    }

    public static void takeScreenshot(WebDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
    }

    public static void scrollDown(WebDriver driver) {
        int pixels = 200;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ");");
    }
}

package org.hotel.search.nh;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.hotel.search.common.BaseTest;
import org.hotel.search.common.MyScreenRecorder;
import org.hotel.search.common.Utils;
import org.hotel.search.nh.pages.HomePage;
import org.hotel.search.nh.pages.HotelsResultPage;
import org.junit.jupiter.api.Test;

import static org.hotel.search.common.Utils.waits;

public class NHTest extends BaseTest {


    String startDate = "01/03/2025";
    String endDate = "05/03/2025";
    String destination = "Valencia";

    @Test
    void searchNHHotels() {

        String methodName= "searchNHHotels";
        ExtentTest extent = extentReports.createTest(methodName);
        MyScreenRecorder.startRecording("navigationTest_NH");

        try {

            HomePage homePage = new HomePage(driver);
            waits(2000);

            extent.log(Status.INFO, "Vamos a aceptar cookies");
            homePage.acceptCookies();
            extent.log(Status.INFO, "Se han aceptado las cookies correctamente");
            waits(300);

            extent.log(Status.INFO, "Elegimos la ciudad de destino");
            homePage.setDestination(destination);
            waits(150);

            extent.log(Status.INFO, "Elegimos la fecha de entrada");
            homePage.setCheckin(startDate);
            waits(160);

            extent.log(Status.INFO, "Elegimos la fecha de salida");
            homePage.setCheckout(endDate);
            waits(200);

            HotelsResultPage hotelsResultPage = homePage.search();
            waits(10000);

            hotelsResultPage.printData();
            extent.log(Status.PASS, "Success.");

        } catch (Exception e) {
            e.printStackTrace();
            Utils.takeScreenshot(driver);
            extent.log(Status.FAIL, "Error while execution.");
        } finally {
            MyScreenRecorder.stopRecording();
        }

    }
}

package org.hotel.search.melia;

import static org.hotel.search.common.Utils.waits;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.hotel.search.common.BaseTest;
import org.hotel.search.common.MyScreenRecorder;
import org.hotel.search.common.Utils;
import org.hotel.search.melia.pages.HomePage;
import org.hotel.search.melia.pages.HotelsResultPage;
import org.junit.jupiter.api.Test;

public class MeliaTest extends BaseTest {

    final static String startDate = "01/03/2025";
    final static String endDate = "05/03/2025";

    @Test
    void searchMeliaHotels() {

        String methodName= "searchMeliaHotels";
        ExtentTest extent = extentReports.createTest(methodName);
        MyScreenRecorder.startRecording("navigationTest_Melia");

        try {

            HomePage homePage = new HomePage(driver);
            waits(2000);

            extent.log(Status.INFO,"vamos a aceptar cookies");
            homePage.acceptCookies();
            extent.log(Status.INFO,"se han aceptado las cookies correctamente");

            waits(300);

            extent.log(Status.INFO, "Elegimos la ciudad de destino");
            homePage.setDestination("Valencia");
            waits(150);

            extent.log(Status.INFO, "Elegimos la fecha de entrada");
            homePage.setCheckin(startDate);
            waits(160);

            extent.log(Status.INFO, "Elegimos la fecha de salida");
            homePage.setCheckout(endDate);
            waits(200);

            HotelsResultPage hotelsResultPage = homePage.search();
            waits(5000);

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

package org.hotel.search.melia;

import static org.hotel.search.common.Utils.waits;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.hotel.search.common.BaseTest;
import org.hotel.search.common.MyScreenRecorder;
import org.hotel.search.common.Utils;
import org.hotel.search.melia.pages.HomePage;
import org.hotel.search.melia.pages.HotelsResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MeliaTest extends BaseTest {

    @Test
    void searchMeliaHotels() {
        String startDate = "01/03/2025";
        String endDate = "05/03/2025";
        String destination = "Valencia";

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
            homePage.setDestination(destination);
            waits(150);

            extent.log(Status.INFO, "Elegimos la fecha de entrada");
            homePage.setCheckin(startDate);
            waits(160);

            extent.log(Status.INFO, "Elegimos la fecha de salida");
            homePage.setCheckout(endDate);
            waits(200);

            extent.log(Status.INFO, "Vamos a pulsar el boton buscar");
            HotelsResultPage hotelsResultPage = homePage.search();
            waits(5000);
            extent.log(Status.INFO, "Se ha pulsado en el boton buscar");
            extent.log(Status.INFO, "Se va mostrar los resultados de la busqueda");
            hotelsResultPage.printData();

            extent.log(Status.PASS, "Success.");

        } catch (Exception e) {
            e.printStackTrace();
            Utils.takeScreenshot(driver);
            extent.log(Status.FAIL, e.getMessage());
            Assertions.fail(); // indica que el test ha fallado
        } finally {
            MyScreenRecorder.stopRecording();
        }

    }
}

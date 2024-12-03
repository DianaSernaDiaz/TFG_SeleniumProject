package org.hotel.search.eurostars;

import static org.hotel.search.common.Utils.takeScreenshot;
import static org.hotel.search.common.Utils.waits;

import org.hotel.search.common.BaseTest;
import org.hotel.search.common.MyScreenRecorder;
import org.hotel.search.eurostars.pages.HomePage;
import org.hotel.search.eurostars.pages.HotelsResultPage;
import org.junit.jupiter.api.Test;

public class EurostarsTest extends BaseTest {

    final static String startDate = "01/12/2024";
    final static String endDate = "05/12/2024";

    @Test
    void searchEuroStarsHotels() {

        MyScreenRecorder.startRecording("navigationTest");
        try {
            HomePage homePage = new HomePage(driver);
            waits(2000);

            homePage.acceptCookies();
            waits(300);

            homePage.setDestination("Valencia");
            waits(150);

            homePage.setCheckin(startDate);
            waits(160);

            homePage.setCheckout(endDate);
            waits(200);

            //homePage.setOcupation("1 habitación, 2 adultos ");
            waits(100);

            homePage.search();

            waits(5000);

            HotelsResultPage hotelsResultPage = new HotelsResultPage(driver);
            hotelsResultPage.printData();

            System.out.println();
        } catch (Exception e) {
            takeScreenshot(driver);
        }finally {
            MyScreenRecorder.stopRecording();
        }

    }

}

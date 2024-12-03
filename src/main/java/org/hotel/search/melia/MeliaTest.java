package org.hotel.search.melia;

import static org.hotel.search.common.Utils.waits;

import org.hotel.search.common.BaseTest;
import org.hotel.search.melia.pages.HomePage;
import org.hotel.search.melia.pages.HotelsResultPage;
import org.junit.jupiter.api.Test;

public class MeliaTest extends BaseTest {

    final static String startDate = "01/12/2024";
    final static String endDate = "05/12/2024";

    @Test
    void searchMeliaHotels() {

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

        HotelsResultPage hotelsResultPage= new HotelsResultPage(driver);
        hotelsResultPage.printData();

    }

    // Repeat the search method for Eurostars and Melia
}

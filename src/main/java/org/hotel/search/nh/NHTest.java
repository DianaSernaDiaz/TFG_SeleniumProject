package org.hotel.search.nh;

import org.hotel.search.common.BaseTest;
import org.hotel.search.common.Utils;
import org.hotel.search.nh.pages.HomePage;
import org.hotel.search.nh.pages.HotelsResultPage;
import org.junit.jupiter.api.Test;
import static org.hotel.search.common.Utils.waits;

public class NHTest extends BaseTest {


    final static String startDate = "01/12/2024";
    final static String endDate = "05/12/2024";

    @Test
    void searchNHHotels() {

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

        homePage.setOcupation("1 habitación, 2 adultos ");
        waits(100);

        homePage.search();

        waits(10000);

        HotelsResultPage hotelsResultPage = new HotelsResultPage(driver);
        hotelsResultPage.printData();

    }

    // Repeat the search method for Eurostars and Melia
}

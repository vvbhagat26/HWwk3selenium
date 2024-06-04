package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']/child::li"));
        for (WebElement e : topMenu) {
            if (e.getText().equalsIgnoreCase(menu)) {
                e.click();
                break;
            }
        }
    }
    @Test
    public void verifyPageNavigation() {
        List<String> topmenu = new ArrayList<>();
        topmenu.add("Computers");
        topmenu.add("Electronics");
        topmenu.add("Apparel");
        topmenu.add("Digital downloads");
        topmenu.add("Books");
        topmenu.add("Jewelry");
        topmenu.add("Gift Cards");

        for (int i = 0; i < topmenu.size(); i++) {
            selectMenu(topmenu.get(i));
            String actualmessge = getTextFromElement(By.xpath("//h1[contains(text(),'" + topmenu.get(i) + "')]"));
            Assert.assertEquals("", topmenu.get(i), actualmessge);
        }
    }
    @After
    public void tearDown() {
        //closeBrowser();
    }
}

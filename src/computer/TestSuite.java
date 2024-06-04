package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //  //Verify products displayed inverse alphabetically
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        clickOnElement(By.linkText("Computers"));
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));
        List<WebElement> beforeFilterProductNames = driver.findElements(By.cssSelector(".product-title"));
        List<String> beforeFilterProductNamesList = new ArrayList<>();
        for (WebElement p : beforeFilterProductNames) {
            beforeFilterProductNamesList.add(p.getText());
        }
        Collections.sort(beforeFilterProductNamesList);
        Collections.reverse(beforeFilterProductNamesList);
        selectByValueFromDropDown(By.id("products-orderby"), "6");
        Thread.sleep(2000);
        List<WebElement> afterFilterProductNames = getMultipleElements(By.className("product-title"));
        List<String> afterFilterProductNamesList = new ArrayList<>();
        for (WebElement s : afterFilterProductNames) {
            afterFilterProductNamesList.add(s.getText());
        }
        Assert.assertEquals("Products are not sorted in descending order", afterFilterProductNamesList, beforeFilterProductNamesList);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Thread.sleep(1000);
        //mouse hover on computer then mouse hover on desktops the click
        WebElement computer = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        WebElement desktops = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(computer).moveToElement(desktops).click().build().perform();

        //a to z
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        //Create the object of select class
        Select select = new Select(dropDown);
        //Select by text
        select.selectByVisibleText("Name: A to Z");

        Thread.sleep(2000);
        //Click on add to cart
        driver.findElement(By.xpath(" //div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]")).click();

        //verify 'build your own com.' text
        String expectedText = "Build your own computer";
        WebElement welcomeText = driver.findElement(By.linkText("Build your own computer"));
        String actualText = welcomeText.getText();
        Assert.assertEquals("Not expected text", expectedText, actualText);

        Thread.sleep(2000);
        //Select '2.2 Ghz' using select class
        selectByValueFromDropDown(By.id("product_attribute_1"), "1");

        Thread.sleep(2000);
        //Select 8GB using select class
        selectByValueFromDropDown(By.id("product_attribute_2"), "5");

        Thread.sleep(2000);
        //Select Hdd radio button
        clickOnElement(By.id("product_attribute_3_7"));

        Thread.sleep(2000);
        //Select OS radio button
        clickOnElement(By.id("product_attribute_4_9"));

        Thread.sleep(2000);
        //Check two checkboxes,click on one
        clickOnElement(By.id("product_attribute_5_12"));

        Thread.sleep(2000);
        //Verify the value
        String expectedText1 = "$1,475.00";
        WebElement welcomeText1 = driver.findElement(By.id("price-value-1"));
        String actualText1 = welcomeText1.getText();
        Assert.assertEquals("Not expected text", expectedText1, actualText1);

        Thread.sleep(2000);
        //Click on'Add to cart '
        clickOnElement(By.id("add-to-cart-button-1"));

        Thread.sleep(2000);
        //Verify the msg
        String expectedText2 = "shopping cart";
        WebElement welcomeText2 = driver.findElement(By.xpath(" //a[normalize-space()='shopping cart']"));
        String actualText2 = welcomeText2.getText();
        Assert.assertEquals("Not expected text", expectedText2, actualText2);

        //close the inner browser
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));

        Thread.sleep(1000);
        //Mouse Hover to shopping cart and Click on go to cart
        WebElement shopCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        WebElement goCart = driver.findElement(By.xpath("//button[text()='Go to cart']"));
        Actions sc = new Actions(driver);
        sc.moveToElement(shopCart).moveToElement(goCart).click().build().perform();

        Thread.sleep(1000);
        //Verify text
        String expectedText3 = "Shopping cart";
        WebElement welcomeText3 = driver.findElement(By.xpath("//h1[text()='Shopping cart']"));
        String actualText3 = welcomeText3.getText();
        Assert.assertEquals("Not expected text", expectedText3, actualText3);

        //select qty.2
        clickOnElement(By.xpath("//div[@class='quantity up']"));
        //Verify the total=$2950
        Assert.assertEquals("$2,950.00",getTextFromElement(By.xpath("//span[@class='value-summary']//strong[text()='$2,950.00']")));

        //click on terms checkbox
        clickOnElement(By.id("termsofservice"));

        //click on checkout
        clickOnElement(By.id("checkout"));

        Thread.sleep(1000);
        //verify text'welcome,sign in'
        String expectedText4 = "Welcome, Please Sign In!";
        WebElement welcomeText4 = driver.findElement(By.xpath(" //h1[normalize-space()='Welcome, Please Sign In!']"));
        String actualText4 = welcomeText4.getText();
        Assert.assertEquals("Not expected text", expectedText4, actualText4);

        //click on checkout guest
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        Thread.sleep(2000);
        //input fName
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Lal");
        //input lMane
        sendTextToElement(By.id("BillingNewAddress_LastName"), "bahadur");
        //input email
        sendTextToElement(By.id("BillingNewAddress_Email"), "java1234@gmail.com");

        //Select country
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "233");

        Thread.sleep(2000);
        //Enter city
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        //Enter Address
        sendTextToElement(By.id("BillingNewAddress_Address1"), "115,Park lane");
        //Enter postcode
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "Ha9 2sf");
        //Enter phone number
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "01234567");

        Thread.sleep(1000);
        //click on continue
        clickOnElement(By.name("save"));

        //Click on next day radio button
        clickOnElement(By.id("shippingoption_1"));

        //Click on continue
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //Click on credit card radiobutton
        clickOnElement(By.id("paymentmethod_1"));

        //click continue
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //select mastercard from dropdown
        selectByValueFromDropDown(By.xpath(" //select[@id='CreditCardType']"), "MasterCard");


        //enter card details
        sendTextToElement(By.id("CardholderName"), "Lal");
        sendTextToElement(By.id("CardNumber"), "5555555555554444");
        selectByValueFromDropDown(By.id("ExpireMonth"), "12");
        selectByValueFromDropDown(By.id("ExpireYear"), "2024");
        sendTextToElement(By.id("CardCode"), "456");

        Thread.sleep(1000);
        //Click on continue
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        // Verify “Payment Method” is “Credit Card”
        String actPaymentText = getTextFromElement(By.xpath("//span[normalize-space()='Payment Method:']")) + getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        Assert.assertEquals("Payment method not valid", "Payment Method:Credit Card", actPaymentText);
        String actualShippingText = getTextFromElement(By.xpath("//span[normalize-space()='Shipping Method:']")) + getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        // Verify “Shipping Method” is “Next Day Air”
        Assert.assertEquals("Shipping method not valid", "Shipping Method:Next Day Air", actualShippingText);
        clickOnElement(By.xpath("//button[text()='Confirm']"));
        //Verify Total is “$2,950.00”


        //Click on confirm
        clickOnElement(By.xpath(" //button[normalize-space()='Confirm']"));

        //Verify text 'thank you'
        Assert.assertEquals("Thank you", getTextFromElement(By.xpath("//h1[text()='Thank you']")));

        //Verify 'your order is processed'
        Assert.assertEquals("Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']")));

        //Click on continue
        clickOnElement(By.xpath(" //button[normalize-space()='Continue']"));

        //Verify the msg.
        String expectedText7 = "Welcome to our store";
        WebElement welcomeText7= driver.findElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        String actualText7= welcomeText7.getText();
        Assert.assertEquals("Not expected text", expectedText7, actualText7);
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}





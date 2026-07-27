package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BaseClass {

    public static WebDriver driver;
    public static  ExtentReports extentReports;
    private static File file;


    // BROWSER LAUNCH
    protected static WebDriver browserLaunch(String browserName) {

        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Unsupported Browser : " + browserName);
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING BROWSER LAUNCH" + e.getMessage());
        }
        return driver;
    }

    // GET
    protected static void launchURL(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING URL LAUNCHING" + e.getMessage());
        }
    }

    protected static void windowMinimize() {
        try {
            driver.manage().window().minimize();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING MINIMIZE THE WINDOW" + e.getMessage());
        }
    }

    // CLOSE BROWSER
    protected static void closeBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING BROWSER CLOSING" + e.getMessage());
        }
    }

    // QUIT BROWSER
    protected static void terminateBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            Assert.fail("Error : OCCURRED DURING TERMINATE BROWSER" + e.getMessage());
        }
    }

    // SENDKEYS
    protected static void passInput(WebElement element, String values) {
        try {
            element.sendKeys(values);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING PASSING THE VALUE" + e.getMessage());
        }
    }

    // DROP DOWN
    protected static void selectOptions(WebElement element, String type, String value) {
        try {
            Select select = new Select(element);

            if (type.equalsIgnoreCase("value")) {
                select.selectByValue(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.selectByIndex(Integer.parseInt(value));
            } else if (type.equalsIgnoreCase("text")) {
                select.selectByVisibleText(value);
            }
        } catch (Exception e) {

            Assert.fail("ERROR : OCCURRED DURING SELECTING THE VALUE" + e.getMessage());
        }
    }

    protected static void deSelectOptions(WebElement element, String type, String value) {
        try {
            Select select = new Select(element);
            if (type.equalsIgnoreCase("value")) {
                select.deselectByValue(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.deselectByIndex(Integer.parseInt(value));
            } else if (type.equalsIgnoreCase("text")) {
                select.deselectByVisibleText(value);
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING DE-SELECTING THE VALUE" + e.getMessage());
        }
    }

    // NAVIGATION
    protected static void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING NAVIGATE TO" + e.getMessage());
        }
    }

    protected static void navigationMethods(String navigate) {
        try {
            if (navigate.equalsIgnoreCase("back")) {
                driver.navigate().back();
            } else if (navigate.equalsIgnoreCase("forward")) {
                driver.navigate().forward();
            } else if (navigate.equalsIgnoreCase("refresh")) {
                driver.navigate().refresh();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING NAVIGATING USING METHODS" + e.getMessage());
        }
    }

    protected static String alert(String type, String text) {
        try {
            Alert alert = driver.switchTo().alert();
            if (type.equalsIgnoreCase("accept")) {
                alert.accept();
            } else if (type.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
            } else if (type.equalsIgnoreCase("sendKeys")) {
                alert.sendKeys(text);
            } else if (type.equalsIgnoreCase("getText")) {
                return alert.getText();
            }

        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING ALERT POPUP" + e.getMessage());
        }
        return null;
    }

    protected static void mouseActions(WebElement element, String type) {
        try {
            Actions actions = new Actions(driver);
            if (type.equalsIgnoreCase("click")) {
                actions.click(element).build().perform();
            } else if (type.equalsIgnoreCase("doubleClick")) {
                actions.doubleClick(element).build().perform();
            } else if (type.equalsIgnoreCase("rightClick")) {
                actions.contextClick(element).build().perform();
            } else if (type.equalsIgnoreCase("moveToElement")) {
                actions.moveToElement(element).build().perform();
            } else if (type.equalsIgnoreCase("clickAndHold")) {
                actions.clickAndHold(element).build().perform();
            } else if (type.equalsIgnoreCase("release")) {
                actions.release(element).build().perform();
            }

        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING TO PERFORM MOUSE ACTIONS" + e.getMessage());
        }

    }

    protected static void mouseActionsDragAndDrop(WebElement source, WebElement target) {
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).build().perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING TO PERFORM MOUSE ACTIONS DRAG AND DROP" + e.getMessage());
        }
    }

    protected static void switchToFrame(WebElement element, String type, String index, String idOrName) {
        try {
            if (type.equalsIgnoreCase("index")) {
                driver.switchTo().frame(Integer.parseInt(index));
            } else if (type.equalsIgnoreCase("id") || type.equalsIgnoreCase("name")) {
                driver.switchTo().frame(idOrName);
            } else if (type.equalsIgnoreCase("webElement")) {
                driver.switchTo().frame(element);
            } else if (type.equalsIgnoreCase("parentFrame")) {
                driver.switchTo().parentFrame();
            } else if (type.equalsIgnoreCase("defaultContent")) {
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING SWITCHING THE FRAME" + e.getMessage());
        }
    }

    protected static void Action(int keyValue) {
        try {
            Robot robot = new Robot();
            robot.keyPress(keyValue);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING KEYBOARD PERFORM" + e.getMessage());
        }
    }

    protected static void switchToWindow(int num) {
        try {
            List<String> allWindow = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allWindow.get(num));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING WINDOW HANDLE" + e.getMessage());
        }
    }

    protected static boolean elementState(WebElement element, String type) {

        try {
            if (type.equalsIgnoreCase("isDisplayed")) {
                return element.isDisplayed();
            } else if (type.equalsIgnoreCase("isEnabled")) {
                return element.isEnabled();
            } else if (type.equalsIgnoreCase("isSelected")) {
                return element.isSelected();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING " + type + " : " + e.getMessage());
        }
        return false;
    }

    protected static List<String> dropDownGetOptions(WebElement element) {
        try {
            Select select = new Select(element);
            List<WebElement> allOptions = new ArrayList<>(select.getOptions());
            List<String> options = allOptions.stream().map(n -> n.getText()).toList();
            return options;
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GETTING ALL OPTIONS" + e.getMessage());
            return List.of();
        }
    }

    protected static String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GETTING TITLE" + e.getMessage());
        }
        return null;
    }

    protected static String getPageURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GETTING CURRENT PAGE URL" + e.getMessage());
        }
        return null;
    }

    protected static String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GETTING CURRENT PAGE URL" + e.getMessage());

        }
        return null;
    }

    protected static String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GET THE TEXT" + e.getMessage());

        }
        return null;
    }

    protected static String getAttribute(WebElement element, String attributeName) {
        try {
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GET DOM ATTRIBUTE" + e.getMessage());
        }
        return null;
    }

    protected static void implicitWait(Duration time) {
        try {
            driver.manage().timeouts().implicitlyWait(time);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING IMPLICIT WAIT" + e.getMessage());
        }
    }

    protected static void explicitWait(WebElement element, String type, Duration time) {

        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            if (type.equalsIgnoreCase("visibilityOf")) {
                wait.until(ExpectedConditions.visibilityOf(element));
            } else if (type.equalsIgnoreCase("elementToBeClickable")) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } else if (type.equalsIgnoreCase("inVisibilityOf")) {
                wait.until(ExpectedConditions.invisibilityOf(element));
            }
            else if(type.equalsIgnoreCase("alertIsPresent")){
                wait.until(ExpectedConditions.alertIsPresent());
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING EXPLICIT WAIT" + e.getMessage());
        }
    }

    protected static void fluentWait(WebElement element, String type, Duration time,
                                     Duration pollingEveryTime) {

        Wait<WebDriver> wait = new FluentWait<>(driver).pollingEvery(pollingEveryTime)
                .withTimeout(time).ignoring(Exception.class);

        try {
            if (type.equalsIgnoreCase("visibilityOf")) {
                wait.until(ExpectedConditions.visibilityOf(element));
            } else if (type.equalsIgnoreCase("elementToBeClickable")) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } else if (type.equalsIgnoreCase("inVisibilityOf")) {
                wait.until(ExpectedConditions.invisibilityOf(element));
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING EXPLICIT WAIT" + e.getMessage());
        }
    }

    protected static void takeFullPageScreenShot() {
        try {
            Date date = new Date();
            String currentDate = date.toString().replace(" ", "_").replace(":", "_");

            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            ImageIO.write(screenCapture, "png", new File(".\\Screenshot\\" +
                    currentDate + ".png"));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING TAKING SCREENSHOT" + e.getMessage());
        }
    }

    protected static void takeScreenShot() {
        try {
            Date date = new Date();
            String currentDate = date.toString().replace(" ", "_").replace(":", "_");

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(source, new File(".\\Screenshot\\" + currentDate + ".png"));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING TAKING SCREENSHOT" + e.getMessage());
        }
    }

    protected static void scrolling(String type, int x, int y) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            if (type.equalsIgnoreCase("Scroll")) {
                js.executeScript("window.scrollBy(" + x + "," + y + ")");
            } else if (type.equalsIgnoreCase("bottomOfPage")) {
                js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING SCROLLING" + e.getMessage());
        }
    }

    protected static String getFirstSelectedOption(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getFirstSelectedOption().getText();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GET FIRST SELECTED OPTION" + e.getMessage());
        }
        return null;
    }

    protected static List<String> getAllSelectedOptions(WebElement element) {
        try {
            Select select = new Select(element);
            List<String> allSelectedOptions = select.getAllSelectedOptions().stream().
                    map(WebElement::getText).toList();
            return allSelectedOptions;
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING GET ALL SELECTED OPTION" + e.getMessage());
        }
        return List.of();
    }


    protected static boolean isMultipleOption(WebElement element) {

        try {
            Select select = new Select(element);
            return select.isMultiple();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING TO CHECK IS-MULTIPLE" + e.getMessage());
        }
        return false;
    }


    protected static void javaScriptExecutor(WebElement element, String type, String value) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (type.equalsIgnoreCase("scroll")) {
                js.executeScript("arguments[0].scrollIntoView();", element);
            } else if (type.equalsIgnoreCase("click")) {
                js.executeScript("arguments[0].click();", element);
            } else if (type.equalsIgnoreCase("sendkeys")) {
                js.executeScript("arguments[0].value = arguments[1];", element, value);
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED USING JAVASCRIPT EXECUTOR" + e.getMessage());
        }
    }

    protected static void clearText(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED USING CLEAR METHOD" + e.getMessage());
        }
    }

    protected static void submitButton(WebElement element) {
        try {
            element.submit();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED USING SUBMIT METHOD" + e.getMessage());
        }
    }

    protected static void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED USING CLICK METHOD" + e.getMessage());
        }
    }


    protected static void selectRadioButton(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED DURING RADIO BUTTON" + e.getMessage());
        }
    }

    public void extentReportStart(String location) {
        extentReports = new ExtentReports();
        file = new File(location);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public void extentReportTearDown(String location) throws IOException {
        extentReports.flush();
        file = new File(location);
        Desktop.getDesktop().browse(file.toURI());
    }

    protected String captureScreenshot() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File scrfile = screenshot.getScreenshotAs(OutputType.FILE);
        File destfile = new File("Screenshots\\.png" + "_" + timeStamp + ".png");

        FileHandler.copy(scrfile, destfile);
        return destfile.getAbsolutePath();
    }

    protected static void validation(WebElement element, String expected){
        try{
            String text=element.getText();
            Assert.assertEquals(expected, text);
        } catch (Exception e) {
            Assert.fail("ERROR  : OCCURED DURING VALIDATION" + e.getMessage()); }
    }

}






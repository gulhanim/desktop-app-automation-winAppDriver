import io.appium.java_client.windows.WindowsDriver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NotePadTest {
    private static WindowsDriver notepadSession = null;

    public static String getDate() {
        Date date = new Date();
        return date.toString();
    }

    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            notepadSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void cleanApp() {
        notepadSession.quit();
        setUp();
    }

    @AfterSuite
    public void tearDown() {
        notepadSession.quit();
    }

    @Test()
    public void checkAboutWindow() {
        notepadSession.findElementByName("Help").click();
        notepadSession.findElementByAccessibilityId("65").click();
        notepadSession.findElementByName("OK").click();
    }

    @Test
    public void sendTestText() {
        notepadSession.findElementByClassName("Edit").sendKeys(getDate());
        notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        notepadSession.findElementByClassName("Edit").clear();
    }

    @Test
    public void pressTimeAndDateButton() {
        notepadSession.findElementByName("Edit").click();
        notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        notepadSession.findElementByAccessibilityId("26").click();
        Assert.assertNotNull(notepadSession.findElementByClassName("Edit"));
        notepadSession.findElementByClassName("Edit").clear();
        notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }
}
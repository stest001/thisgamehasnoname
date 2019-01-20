package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import utils.PropertyLoader;

import static utils.DriverManager.getDriver;

public abstract class BaseSteps {

    private static final String TEST_URL = PropertyLoader.loadProperty("test_url");
    private static final String USER = PropertyLoader.loadProperty("email");
    private static final String PASSWORD = PropertyLoader.loadProperty("amazon_password");
    private static final int TIME_OUT_IN_SECONDS = 20;
    private static final int MILLIS = 1000;

    public static void openBrowser() {
        getDriver().get(TEST_URL);
        maximizeBrowserWindow();
    }

    public static void maximizeBrowserWindow() {
        getDriver().manage().window().maximize();
        waitingDocumentReadyState();
    }

    public static void waitingDocumentReadyState() {
        try {
            Thread.sleep(MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        (new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS)).until((getDriver) -> {
            try {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            } catch (Exception e) {
                return false;
            }
        });
    }

    public static void logOut(){
        new HomePage()
                .moveMouseOnYourAccountTool()
                .clickOnSignOut();
        getDriver().close();
    }

    public static void logIn() {
        openBrowser();
        goToLoginPage();
        new LoginPage()
                .setEmailValue(USER)
                .setPasswordValue(PASSWORD)
                .clickOnSignIn();
    }

    public static void goToLoginPage() {
        new HomePage()
                .moveMouseOnYourAccountTool()
                .clickOnSignIn();
    }
}

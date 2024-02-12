package com.callzen.testBase;

import com.callzen.commonFactory.CommonConstants;
import com.callzen.commonFactory.WebBrowser;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.callzen.commonFactory.CommonConstants.*;

public class BaseTest extends WebBrowser {
    public Logger logger = LogManager.getLogger(this.getClass());
    public Page launchWebBrowser() {
        CommonConstants.loadConfigProperties();
        try {
            page = new WebBrowser().getWebBrowser(BROWSER);
            if (CommonConstants.RUN_MODE) {
                log("Launching '" + BROWSER + "' Browser in Headless Mode");
            } else {
                log("Successfully Launched '" + BROWSER + "' Browser");
            }
            page.navigate(URL);
            log("Navigated to: " + URL);
        } catch (Exception e) {
            log("Failed to launch Browser: " + e.getMessage());
        }
        return page;
    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        try {
            if (page != null) {
                page.close();
                log("Successfully '"+BROWSER+"' Browser closed");
            }
        } catch (Exception e) {
            log("Failed to close'" + BROWSER + "'browser");
        }
    }
    public void log(String message) {
        logger.info(message);
    }

}

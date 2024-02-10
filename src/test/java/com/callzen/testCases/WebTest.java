package com.callzen.testCases;

import com.callzen.commonFactory.CommonConstants;
import com.callzen.pages.CallzenWeb;
import com.callzen.testBase.BaseTest;
import com.callzen.utility.MyListeners;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyListeners.class)
public class WebTest extends BaseTest {
    public CallzenWeb user;

    @BeforeMethod(alwaysRun = true)
    public void login() {
        user = new CallzenWeb(launchWebBrowser());
        user.login(CommonConstants.USERNAME, CommonConstants.PASSWORD);
    }

    @Test
    public void TC01_Agent_Column_Verifications() throws InterruptedException {
        user.selectHeaderOption("Agents");
        String defaultColumns = user.getColumnNames();
        System.out.print("Total Columns : " + defaultColumns);
    }
}

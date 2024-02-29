package com.callzen.testCases;
import com.callzen.commonFactory.CommonConstants;
import com.callzen.pages.CallzenWeb;
import com.callzen.testBase.BaseTest;
import com.callzen.utility.MyListeners;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.List;

@Listeners(MyListeners.class)
public class WebTest extends BaseTest {
    public CallzenWeb user;
    @BeforeMethod(alwaysRun = true)
    public void login() {
        user = new CallzenWeb(launchWebBrowser());
        user.login(CommonConstants.USERNAME, CommonConstants.PASSWORD);
    }
    @Test(priority=1)
    public void TC01_Agent_TableColumn_Verifications() throws InterruptedException {
        user.selectHeaderOption("Agents");
        log("Clicked on 'Agent' Module");

        String defaultColumns = user.getAgentTableColumnsNames().toLowerCase();
        List<String> tableColsList = Arrays.asList(defaultColumns.split(","));
        tableColsList.replaceAll(String::trim);
        System.out.print("Total Columns : " + defaultColumns);

        String checkedColumns = user.getCheckedColumns().toLowerCase();
        List<String> checkedColsList = Arrays.asList(checkedColumns.split(","));
        checkedColsList.replaceAll(String::trim);
        System.out.println("Checked Columns : " + checkedColumns);
        SoftAssert softAssert = new SoftAssert();
        List<String> excludedCols = Arrays.asList("checklist coverage", "inbound calling", "actions", "agent name", "checklists"); // Lowercase
        for (String col : checkedColsList) {
            String colLowerCase = col.trim().toLowerCase(); // Ensure lowercase for comparison
            if (!excludedCols.contains(colLowerCase)) {
                softAssert.assertTrue(tableColsList.contains(colLowerCase), "Column " + col + " is not present");
            }
        }
    }

   @Test(priority=2)
    public void TC02_Verify_Search_select_AddColumn_Functionality() throws InterruptedException {
        user.selectHeaderOption("Agents");
        log("Clicked on 'Agent' Module");
        user.searchColumnFilter("customers", "connected Calls", "meaningful Calls");

        String defaultColumns = user.getAgentTableColumnsNames().toLowerCase();
        List<String> tableColsList = Arrays.asList(defaultColumns.split(","));
        tableColsList.replaceAll(String::trim);
        log("Agent Table 'Total Columns :"+defaultColumns+"'");

        String checkedColumns = user.getCheckedColumns().toLowerCase();
        List<String> checkedColsList = Arrays.asList(checkedColumns.split(","));
        checkedColsList.replaceAll(String::trim);
        log("Checked 'Total Columns :"+checkedColumns+"'");
    }

    @Test(priority=2)
    public void TC02_Verify_Search_select_RemoveColumn_Functionality() throws InterruptedException {
        user.selectHeaderOption("Agents");
        log("Clicked on 'Agent' Module");
        user.removeColumnFilter("average Handling Time", "calls Made", "checklists","long Silence","negative Emotion","total Talktime");

        String defaultColumns = user.getAgentTableColumnsNames().toLowerCase();
        List<String> tableColsList = Arrays.asList(defaultColumns.split(","));
        tableColsList.replaceAll(String::trim);
        log("Agent Table 'Total Columns :"+defaultColumns+"'");

        String checkedColumns = user.getCheckedColumns().toLowerCase();
        List<String> checkedColsList = Arrays.asList(checkedColumns.split(","));
        checkedColsList.replaceAll(String::trim);
        log("Checked 'Total Columns :"+checkedColumns+"'");
    }




}

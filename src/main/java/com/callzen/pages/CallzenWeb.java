package com.callzen.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import com.microsoft.playwright.ElementHandle;

public class CallzenWeb {
    public Page page;
    public Logger logger = LogManager.getLogger(this.getClass());
    public CallzenWeb(Page page) {
        this.page = page;
    }
    public void log(String message) {
        logger.info(message);
    }
    // Locators
    private String emailField = "#email";
    private String passwordField = "#password";
    private String loginBtn = "//button[@type='submit' and text()='Sign In']";
    private String headrLocator = "//*[@class='nb__3eMzz']//following-sibling::span[text()='menu']";
    private String columnFilterBtn = "(//*[@class='nb__1hXpJ']//button)[1]";
    private String searchBar = "//input[@placeholder='Search']";
    private String agentTableColumns = "//tr/th[1]//p";
    private String checkListCoverageColumn = "//div[@class='nb__1w2q6']";
    private String actionsColumn = "//div[@class='nb__1HF0I']";
    private String checkedColumns = "//input[@type='checkbox' and @checked]/preceding-sibling::span";
    private String cheked= "(//input[@type='checkbox' and @checked]/preceding-sibling::span)[1]";

    public String getCheckedCols() throws InterruptedException {
        StringBuilder checkedCols = new StringBuilder();
        page.click(columnFilterBtn);
        log("Clicking on Column Filter Button");
        for (int i =1; i <= 7; i++) {
            String columnName = page.locator("(//input[@type='checkbox' and @checked]/preceding-sibling::span)["+i+"]").innerText();
            checkedCols.append(columnName).append(",");
        }
        System.out.println("checkedCols: " + checkedCols);
        return checkedCols.toString();
    }







    public String getColumnNames() {
        StringBuilder sb = new StringBuilder();
        String checklist = page.locator(checkListCoverageColumn).innerText();
        String actions = page.locator(actionsColumn).innerText();
        sb.append(checklist).append(",");
        sb.append(actions).append(",");
        for (int i =1; i <= 7; i++) {
            String columnName = page.locator("//tr/th[" + i + "]//p").innerText();
            sb.append(columnName).append(",");
        }
        return sb.toString();
    }




    public void login(String email, String password) {
        log("Entering Email: " + email);
        page.fill(emailField, email);

        log("Entering Password: " + password);
        page.fill(passwordField, password);

        log("Clicking on 'Sign In' Button");
        page.click(loginBtn);
    }



    public String selectHeaderOption(String header) {
        String headerLocator = headrLocator.replace("menu", header);
        try {
            page.click(headerLocator);
            return "Header option '" + header + "' selected successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to select header option '" + header + "'.";
        }
    }

    public void columnFilter(String headers) throws InterruptedException {
        selectHeaderOption(headers);
        log("Clicking on Header Option: " + headers);

        page.click(columnFilterBtn);
        log("Clicking on Column Filter Button");

        getCheckedCols();

        page.fill(searchBar, "calls Made");
        log("Entering ColumnName 'calls Made' in Searchbar");
    }




}



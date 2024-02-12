package com.callzen.pages;

import com.callzen.commonFactory.PlaywrightUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CallzenWeb extends PlaywrightUtils {
    public Page page;
    public Logger logger = LogManager.getLogger(this.getClass());
    public CallzenWeb(Page page) {
        super(page);
        this.page = page;
    }
    public void log(String message) {
        logger.info(message);
    }
    // Locators
    private String emailField = "#email";
    private String passwordField = "#password";
     String loginBtn = "//button[@type='submit' and text()='Sign In']";
    private String headrLocator = "//*[@class='nb__3eMzz']//following-sibling::span[text()='menu']";
    private String columnFilterBtn = "(//*[@class='nb__1hXpJ']//button)[1]";
    private String searchBar = "//input[@placeholder='Search']";
    private String agentTableColumns = "//tr/th[1]//p";
    private String checkListCoverageColumn = "//div[@class='nb__1w2q6']";
    private String actionsColumn = "//div[@class='nb__1HF0I']";
    private String checkedColumns = "//input[@type='checkbox' and @checked]/ancestor::label";
    private String clearTextIcon = "//button[@class='nb__2ti88 nb__2nIpP']//*[name()='svg']";

    public void login(String email, String password) {
        log("Entering Email: " + email);
        fill(emailField, email);
        log("Entering Password: " + password);
        fill(passwordField, password);
        log("Clicking on 'Sign In' Button");
        click(loginBtn);
     }

    public String getCheckedColumns() throws InterruptedException {
        click(columnFilterBtn);
        Locator loc = page.locator(checkedColumns);
        StringBuilder sb = new StringBuilder();
        try {
            page.waitForSelector(checkedColumns);
            List<String> all = loc.allInnerTexts();
            System.out.println("Number of elements: " + all.size());
            for (String s : all) {
                sb.append(s).append(",");
            }
        } catch (PlaywrightException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getAgentTableColumnsNames() {
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

    public String selectHeaderOption(String header) {
        String headerLocator = headrLocator.replace("menu", header);
        try {
            click(headerLocator);
            return "Header option '" + header + "' selected successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to select header option '" + header + "'.";
        }
    }

    public void searchColumnFilter(String...columns) throws InterruptedException {
        click(columnFilterBtn);
        log("Clicking on Column Filter Button");
        for (String col : columns) {
            fill(searchBar, col);
            log("Entering ColumnName '" + col + "' in Searchbar");
            click("//input[@type='checkbox']");
            log("CLicking on Checkbox");
            click(clearTextIcon);
            log("Clearing the text from Searchbar");
        }
        click(columnFilterBtn);
        log("Clicking on Column Filter Button");
    }


    public void removeColumnFilter(String...columns) throws InterruptedException {
        click(columnFilterBtn);
        log("Clicking on Column Filter Button");
        for (String col : columns) {
            fill(searchBar, col);
            log("Entering ColumnName '" + col + "' in Searchbar");
            click("//input[@type='checkbox']");
            log("CLicking on Checkbox");
            click(clearTextIcon);
            log("Clearing the text from Searchbar");
        }
        click(columnFilterBtn);
        log("Clicking on Column Filter Button");
    }



}



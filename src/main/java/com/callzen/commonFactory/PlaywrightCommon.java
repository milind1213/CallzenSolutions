package com.callzen.commonFactory;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PlaywrightCommon {
  public Page page;
    public PlaywrightCommon(Page page) {
        this.page = page;
    }
    public void type(String loc, String text) {
        Locator locator = page.locator(loc);
        locator.evaluate("element => element.style.border = '2px solid red'");
        locator.type(text);
    }
    public void click(String loc) {
        Locator locator = page.locator(loc);
        locator.evaluate("element => element.style.border = '2px solid red'");
        locator.click();
    }
    public void fill(String loc, String text) {
        Locator locator = page.locator(loc);
        locator.evaluate("element => element.style.border = '2px solid red'");
        locator.fill(text);
    }
    public boolean elementExists(String locatorString) {
        return page.locator(locatorString).first() != null;
    }

    public String getText(String locatorString) {
        return page.locator(locatorString).innerText();
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(1000* seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

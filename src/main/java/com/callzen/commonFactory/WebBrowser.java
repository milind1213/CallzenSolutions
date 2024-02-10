package com.callzen.commonFactory;

import com.microsoft.playwright.*;
import static com.callzen.commonFactory.CommonConstants.RUN_MODE;

public class WebBrowser {
    public Page page;
    private static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
    public static Browser getBrowser() {
        return threadLocalBrowser.get();
    }
    private static ThreadLocal<BrowserContext> threadLocalBrowserContext = new ThreadLocal<>();
    public static BrowserContext getBrowserContext() {
        return threadLocalBrowserContext.get();
    }
    private static ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    public static Page getPage() {
        return threadLocalPage.get();
    }
    private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();
    public static Playwright getPlaywright() {
        return threadLocalPlaywright.get();
    }

    public Page getWebBrowser(String webBrowser) {
        threadLocalPlaywright.set(Playwright.create());
        switch (webBrowser.toLowerCase()) {
            case "chrome":
                threadLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                        .setChannel("chrome").setHeadless(RUN_MODE)));
                break;
            case "chromium":
                threadLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless((RUN_MODE))));
                break;
            case "firefox":
                threadLocalBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless((RUN_MODE))));
                break;
            case "edge":
                threadLocalBrowser.set(
                        getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                                .setChannel("msedge").setHeadless((RUN_MODE))));
                break;
            default:
                System.out.println("please pass the right browser name......");
                break;
        }
        threadLocalBrowserContext.set(getBrowser().newContext());
        threadLocalPage.set(getBrowserContext().newPage());
        return getPage();
    }


}

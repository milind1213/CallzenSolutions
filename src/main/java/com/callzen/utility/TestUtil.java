package com.callzen.utility;
import com.callzen.commonFactory.WebBrowser;
import com.microsoft.playwright.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class TestUtil extends WebBrowser {
    public static String screenshotName;
    public static void captureScreenshot(String methodName) throws IOException {
        screenshotName = methodName + ".png";
        byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("reports", screenshotName)));
        writeBytesToFile(screenshotName, screenshot);
    }
    private static void writeBytesToFile(String fileName, byte[] bytes) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }
    }
}
package com.example.cross_browser_selenium.tests;


import com.example.cross_browser_selenium.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrossBrowserTest extends BaseTest {

    @Test
    public void verifyTitle() {
        driver.get("https://example.com/");
        String title = driver.getTitle();
        System.out.println("Title: " + title);
        Assert.assertTrue(title.contains("Example Domain"));
    }
}

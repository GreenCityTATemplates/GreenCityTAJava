package com.ita.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum UtilsLocators {
    SPINNER(By.xpath("//span[contains(@class,'spinner')"));
    private final By path;

    UtilsLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}

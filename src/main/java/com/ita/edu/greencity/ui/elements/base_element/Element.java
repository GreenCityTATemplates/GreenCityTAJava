package com.ita.edu.greencity.ui.elements.base_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;


@ImplementedBy(BaseElement.class)
public interface Element extends WebElement, WrapsElement, Locatable {

    boolean elementWired();
}
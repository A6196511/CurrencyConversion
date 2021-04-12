package com.steps;

import com.DriverProvider;
import io.cucumber.java.After;

public class ParentSteps {

    @After
    public void stopDriver() {
        DriverProvider.stopDriver();
    }
}

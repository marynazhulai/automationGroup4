package com.customertimes.cucumber.hooks;
import com.customertimes.test.framework.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;
import static com.customertimes.test.framework.driver.WebdriverRunner.closeWebDriver;


public class WebDriverHooks {
    @Before
    public void setUp() {
        getWebDriver().get(TestConfig.CONFIG.baseUrl());
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}

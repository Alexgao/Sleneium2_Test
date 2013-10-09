package com.gordon.s2_test.browsers;

import com.gordon.s2_test.ConfigProfile;
import com.gordon.s2_test.webdriver.Driver;
import org.openqa.selenium.WebDriver;

import static com.gordon.s2_test.webdriver.WebDriverFactory.getWebDriver;
import static com.gordon.s2_test.webdriver.WebDriverFactory.initWebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-30
 * Time: 下午1:12
 * To change this template use File | Settings | File Templates.
 */
public class Chrome extends BaseDriver {
    static{
        System.setProperty("webdriver.chrome.driver", ConfigProfile.chromeBinary);
        ConfigProfile.browser= Driver.CHROME;
    }
    public Chrome() {
        super(initWebDriver());
    }

}

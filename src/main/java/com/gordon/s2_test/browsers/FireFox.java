package com.gordon.s2_test.browsers;

import com.gordon.s2_test.ConfigProfile;
import com.gordon.s2_test.webdriver.Driver;

import static com.gordon.s2_test.webdriver.WebDriverFactory.initWebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-30
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class FireFox extends BaseDriver {
    static{
        System.setProperty("webdriver.firefox.bin",ConfigProfile.firefoxBinary);
        ConfigProfile.browser=Driver.FIREFOX;
    }
    public FireFox() {
        super(initWebDriver());
    }
}

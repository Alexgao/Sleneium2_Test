package com.gordon.s2_test.browsers;

import com.gordon.s2_test.ConfigProfile;
import com.gordon.s2_test.webdriver.Driver;

import static com.gordon.s2_test.webdriver.WebDriverFactory.initWebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-10-8
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class InternetExplorer extends BaseDriver {
    static {
        System.setProperty("webdriver.ie.driver", ConfigProfile.IEBinary);
        ConfigProfile.browser= Driver.IE;
    }

    public InternetExplorer(){
        super(initWebDriver());
    }
}

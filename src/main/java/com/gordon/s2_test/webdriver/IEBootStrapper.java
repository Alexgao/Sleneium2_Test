package com.gordon.s2_test.webdriver;

import org.slf4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-29
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public class IEBootStrapper {

    Logger logger = LoggerFactory.getLogger(IEBootStrapper.class.getName());
    private DesiredCapabilities capabilities;

    public IEBootStrapper(DesiredCapabilities capabilities){
        this.capabilities = capabilities;
    }

    public WebDriver create(){
        return this.create(true);
    }

    /**
     *
     *
     * @param igonreProtectedMode -是否忽略安全模式
     * @return
     */
    public WebDriver create(boolean igonreProtectedMode){
        if (igonreProtectedMode){
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        }
        return new InternetExplorerDriver(capabilities);
    }
}

package com.gordon.s2_test.webdriver;

import com.gordon.s2_test.ConfigProfile;
import org.slf4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-27
 * Time: 下午6:05
 * To change this template use File | Settings | File Templates.
 */
public class WebDriverFactory{
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();


    public static WebDriver initWebDriver(){
        if (getCurrentDriver()!=null)
            throw new RuntimeException("初始化失败...线程中存在driver进程，请仔细查看框架...");

        DesiredCapabilities mergeCapabilities = getDefaultCapabilities();
        WebDriver driver = getDriverForCapabilities(mergeCapabilities);
        webDriver.set(driver);

        logger.info("初始化浏览器。");
        return driver;
    }

    public static WebDriver getWebDriver() {
        WebDriver driver = getCurrentDriver();
        ConfigProfile configProfile = new ConfigProfile();
        if (driver == null) {
            driver = getDriverForCapabilities(getDefaultCapabilities());
            webDriver.set(driver);
        }

        logger.info("获取当前浏览器。");
        return driver;
    }

    private static WebDriver getCurrentDriver(){
        return webDriver.get();
    }

    private static WebDriver getDriverForCapabilities(final DesiredCapabilities capabilities) {
//        try{
            WebDriver driver;
            URL REMOTE_DRIVER_URL =ConfigProfile.remoteDriverURL;

            final Boolean IS_REMOTE = !(REMOTE_DRIVER_URL == null || REMOTE_DRIVER_URL.equals("")) ;
//            if (!REMOTE_DRIVER_URL.contains(".")){
//                logger.warn("请注意，您所给出的参数 ### remoteDriverURL ### 的值，格式不正确。请检查...");
//            }
//        System.out.print(IS_REMOTE);

            if(IS_REMOTE){
                if ((ConfigProfile.browser).equals(Driver.IE)){
                    driver = new EventFiringWebDriver(new ScreenshotEnableRemoteDriver(REMOTE_DRIVER_URL,DesiredCapabilities.internetExplorer()));
                }else if (ConfigProfile.browser.equals(Driver.CHROME)){
                    driver = new EventFiringWebDriver(new ScreenshotEnableRemoteDriver(REMOTE_DRIVER_URL,DesiredCapabilities.chrome()));
                }else if ((ConfigProfile.browser).equals(Driver.FIREFOX)){
                    driver = new EventFiringWebDriver(new ScreenshotEnableRemoteDriver(REMOTE_DRIVER_URL,capabilities));
                }else if (ConfigProfile.browser.equals(Driver.HTMLUNIT)){
                    driver = new EventFiringWebDriver(new ScreenshotEnableRemoteDriver(REMOTE_DRIVER_URL,DesiredCapabilities.htmlUnit()));
                }else {
                    System.err.print("系统指定浏览器错误.");
                    driver=null;
                }
            }else {
                if (ConfigProfile.browser.equals(Driver.IE)){
                    driver = new IEBootStrapper(capabilities).create(true);
                }else if (ConfigProfile.browser.equals(Driver.CHROME)){
                    driver = new ChromeBootStrapper(capabilities).create();
                }else if (ConfigProfile.browser.equals(Driver.FIREFOX)){
                    driver = new FirefoxDriver(capabilities);
//                    driver = new FirefoxDriver();
                }else if (ConfigProfile.browser.equals(Driver.HTMLUNIT)){
                    driver = new HtmlUnitDriver(capabilities);
                }else {
                    System.err.print("系统指定浏览器错误.");
                    driver=null;
                }
            }
            return driver;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }


    }

    private static DesiredCapabilities getDefaultCapabilities() {
        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, getFirefoxProfile());
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
        return capabilities;
    }

    private static FirefoxProfile getFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile() ;

        profile.setEnableNativeEvents(false);
        profile.setPreference("intl.accept_languages", System.getProperty("acceptLanguages", "zh-CN,en-us,en-gb,en"));
//        profile.setPreference("intl.accept_languages", "zh-CN");
//        profile.setPreference("intl.accept_languages", "en-us");
        profile.setPreference("extensions.checkCompatibility.5.0", false);
        profile.setPreference("extensions.checkCompatibility.6.0", false);
        profile.setPreference("browser.safebrowsing.enabled", false);

        profile.setPreference("browser.cache.disk.enable", false);
        profile.setPreference("browser.cache.memory.enable", false);
        profile.setPreference("browser.cache.offline.enable", false);
        profile.setPreference("network.http.use-cache", false);

        profile.setPreference("network.http.max-connections", 144);
        profile.setPreference("network.http.max-connections-per-server", 48);
        profile.setPreference("network.http.max-persistent-connections-per-proxy", 24);
        profile.setPreference("network.http.max-persistent-connections-per-server", 12);
        profile.setPreference("network.http.pipelining", true);
        profile.setPreference("network.http.pipelining.maxrequests", 24);
        profile.setPreference("network.http.proxy.pipelining", true);

        inItFirebug(profile,ConfigProfile.firebugPath);

        return profile;

    }

    private static FirefoxProfile inItFirebug(FirefoxProfile profile,String firebugPath) {
        if (ConfigProfile.useFireBug) {

            try {
                //firefox 7
                profile.addExtension(new File(firebugPath));

                // Set version very high to prevent welcome screen
                profile.setPreference("extensions.firebug.currentVersion", "100.123");
                profile.setPreference("extensions.firebug.script.enableSites", true);
                profile.setPreference("extensions.firebug.net.enableSites", true);
                profile.setPreference("extensions.firebug.console.enableSites", true);
                profile.setPreference("extensions.firebug.allPagesActivation", "on");
                profile.setPreference("extensions.firebug.showStackTrace", true);
                profile.setPreference("extensions.firebug.defaultPanelName", "console");
                profile.setPreference("extensions.firebug.showXMLHttpRequests", false);

            } catch (final IOException e) {

                throw new RuntimeException("IO Exception caught while adding the Firebug extension to the Firefox profile.", e);
            }
        }

        return profile;


    }

    public static void closeDriver(){
        try {
            if(webDriver.get()!=null){
                webDriver.get().quit();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            webDriver.remove();
        }

    }


    private static class ScreenshotEnableRemoteDriver extends RemoteWebDriver implements TakesScreenshot{
        public ScreenshotEnableRemoteDriver(URL hubLocationURL,DesiredCapabilities capabilities){
            super(hubLocationURL,capabilities);

        }

        @Override
        public <T> T getScreenshotAs(OutputType<T> target) throws WebDriverException {
            String base64EncodePng = execute(DriverCommand.SCREENSHOT).getValue().toString();
            return target.convertFromBase64Png(base64EncodePng);
        }
    }

}

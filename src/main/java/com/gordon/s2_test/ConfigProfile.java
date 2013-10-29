package com.gordon.s2_test;

import com.gordon.s2_test.webdriver.Driver;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-29
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public class ConfigProfile {
    public static Boolean useFireBug = false;
    public static Enum browser = Driver.FIREFOX ;
    public static String remoteDriverURL=null;
    public static int waitTimeOut = 5;
    public static String firebugPath = "src/...";
    public static String firefoxBinary="C:/Program Files/Mozilla Firefox/firefox.exe";
    public static String chromeBinary="src/main/java/resource/chrome/chromedriver.exe";
    public static String IEBinary="C:/Program Files/Internet Explorer/iexplore.exe";

}

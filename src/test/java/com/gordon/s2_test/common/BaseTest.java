package com.gordon.s2_test.common;

import com.gordon.s2_test.ConfigBinary;
import com.gordon.s2_test.browsers.Browser;
import com.gordon.s2_test.browsers.Chrome;
import com.gordon.s2_test.browsers.FireFox;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-10-9
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseTest {
   static{ ConfigBinary binary = new ConfigBinary();
       binary.setFireFoxBinay("D:/Browser/Mozilla_Firefox/firefox.exe");
       binary.setChromeBinay("D:/Browser/chromedriver.exe");
    }
    public static final Browser browser = new Chrome();


    @BeforeClass
    public static void beforeClass(){

        browser.go("www.baidu.com");
        browser.maxWindow();
    }

    @AfterClass
    public static void afterClass(){
//        browser.waitTimeFor(5000);
        browser.close();
    }

}

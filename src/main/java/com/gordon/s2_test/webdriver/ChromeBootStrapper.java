package com.gordon.s2_test.webdriver;

import org.slf4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-29
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public class ChromeBootStrapper {
    private DesiredCapabilities capabilities;
    private ChromeDriverService service;
    Logger logger = LoggerFactory.getLogger(ChromeBootStrapper.class.getName());

    public ChromeBootStrapper(DesiredCapabilities capabilities){
        this.capabilities=capabilities;
    }

    public WebDriver create(){
        try {
            if (System.getProperty("webdriver.chrome.driver")==null || System.getProperty("webdriver.chrome.driver").equals("")){
                logger.warn("错误： 抱歉，你没有设置系统参数 ### webdriver.chrome.driver ### ，service创建失败。");
                return new ChromeDriver();
            }else {
                service = createChromeDriverService();
                service.start();
                Runtime.getRuntime().addShutdownHook(new StopServiceHook(service));

                return new ChromeDriver(service,capabilities);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChromeDriverService createChromeDriverService(){
        try{
            File chromeExeFile =new File(System.getProperty("webdriver.chrome.driver"));
            return new ChromeDriverService.Builder()
                    .usingDriverExecutable(chromeExeFile)
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public class StopServiceHook extends Thread{
        private ChromeDriverService service;

        public StopServiceHook(ChromeDriverService service){
            this.service = service;
        }

        @Override
        public void run(){
            stopService();
        }

        private void stopService() {
            try {
                if (service != null){
                    service.stop();
                    logger.info("关闭Chrome Service.");
                }
            }catch (Exception e){
                System.err.print("屏蔽以下错误："+e.getMessage());
                e.printStackTrace();
            }
        }


    }
}

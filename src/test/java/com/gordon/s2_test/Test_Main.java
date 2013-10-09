package com.gordon.s2_test; /**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 12-11-28
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */

/*
                --------------- 说 ~ 明 ---------------
        这页代码主要是，我们搭建好了测试项目的框架过后，写的一页测试代码。

    由于有些同事可能在基础上有些困难，所以我在写代码的过程中，会存在许多的

    注解，看着有些时候会看的不是很清楚，所以这也里并不是我们项目代码的开始。

    我在正式写项目之前会先写一些小的案例让大家理解selenium的主要工作，和

    Junit的作用！
 */

/*
    以下 import 蓝色字开头的这些代码是 调用包的代码，因为我们用的maven这个工具，
    所以我们只需要在pom.xml添加代码，它就会自动给我下载包。
 */

import com.gordon.s2_test.ConfigBinary;
import com.gordon.s2_test.browsers.Browser;
import com.gordon.s2_test.browsers.Chrome;
import com.gordon.s2_test.browsers.FireFox;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Set;

import static java.lang.System.getProperty;

//我们创建的一个类的名字，（固定写法--创建这个类的时候自动生成）
public class Test_Main {
    private static final Long ONE = Long.parseLong(getProperty("pageLoadTimeout", "60"));

    //写一个主要方法，（大意就是找一个代码的执行输出口）
    public static void main(String args[]){


        // 创建一个WebDriver对象，我们这个创建的是一个FF的 WD（WebDriver以后都这么叫）那么它到时候会打开一个FF Browser
//        // 我们在这里命名为driver
//        WebDriver driver=new FirefoxDriver();
//
//        //通过driver打开google的网站
//        driver.get("http://stg.heremaps.cn/51.5072091,-0.1275501,22,0,89.9");
//
//        /*
//            因为打开浏览器到页面响应,那么我们在这里写一句代码，
//            意思就是只要在10秒钟之内，只要达到了下面代码的要求就继续执行下去
//
//            --这句代码具体现在还没必要知道，如果想知道可以来问我，太多了，写不清楚--
//        */
//        Actions actions=new Actions(driver);
//        driver.manage().window().maximize();
//        (new WebDriverWait(driver, 10))
//                .until(new ExpectedCondition<WebElement>() {
//                    @Override
//                    public WebElement apply(WebDriver d) {
//                        return d.findElement(By.xpath("//*"));
//                    }
//
//                });
//
//        System.out.print("页面加载成功...");
//        WebElement e= driver.findElement(By.xpath("//*[@id='map-controls']/div[1]/div[3]/div[3]"));
//        System.out.print(e.getTagName());
//        Robot robot=new Robot();
////        actions.doubleClick(e).perform();
////        actions.contextClick(e).build().perform();
//        Thread.sleep(5000);
////
////        actions.sendKeys(Keys.ESCAPE).perform();
//
//        System.out.print("点击一个按钮...");
//        robot.mouseMove(500,500);
//        robot.mousePress(KeyEvent.BUTTON1_MASK);
//        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
//        robot.mousePress(KeyEvent.BUTTON1_MASK);
//        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
//        (new WebDriverWait(driver, 10))
//                .until(new ExpectedCondition<WebElement>() {
//                    @Override
//                    public WebElement apply(WebDriver d) {
//                        return d.findElement(By.xpath("//*[contains(text(),'查看街景')]"));
//                    }
//
//                });
//
//         driver.findElement(By.xpath("//*[contains(text(),'查看街景')]")).click();
////        System.out.print("发送第1个UP...");
////        Thread.sleep(5000);
////        robot.keyPress(KeyEvent.VK_ADD);
////        robot.keyRelease(KeyEvent.VK_ADD);
////        System.out.print("发送第2个UP...");
//        Thread.sleep(10000);
////        actions.sendKeys(Keys.PAGE_UP).perform();
////        System.out.print("发送第3个UP...");
////        setScroll(driver,10);
//        //获取我们打开网站上页面元素name属性为"q"的元素（FireBug截出来就是search box）
//        //命名为element
////        WebElement element = driver.findElement(By.xpath("//*[@id='main']/div[6]/div[2]/ul[1]/li[1]/a"));
////        element.click();
//        // 给这个元素输入"Hi,Pactera!"
////        element.sendKeys("成都市");
////
////        // 提交表单，相当于我们敲回车
////        element.submit();
//
//        // 通过driver获取当前页面的标题，然后通过"System.out.print()"这句代码输出！
////        System.out.println("Page title is: " + element.getTagName());
//
//        //关闭浏览器
//        driver.quit();
//
//    }
////    public static void setScroll(WebDriver driver,int height){
////        try {
////            String setscroll = "document.documentElement.scrollTop=" + height;
////             System.out.println("执行JS...");
////            JavascriptExecutor jse=(JavascriptExecutor) driver;
////            jse.executeScript(setscroll);
////            System.out.println("执行完毕，没有错误...");
////        } catch (Exception e) {
////            System.out.println("执行错误...");
////        }

//        System.out.println("1:" + getProperty("pageLoadTimeout"));
//        System.out.println("2:" + ONE);
////        System.out.println("3:" + getProperty("pageLoadTimeout"));


//        AtomicInteger ai=new AtomicInteger(0);
//        int i1=ai.get();
//        System.out.println("i1: " + i1);
//        int i2=ai.getAndSet(5);
//        System.out.println("i2: " + i2);
//        int i3=ai.get();
//        System.out.println("i3: " + i3);
//        int i4=ai.getAndIncrement();
//        System.out.println("i4: " + i4);
//        System.out.println("i5: " + ai.get());

//        String remoteDriverURL = "1" ;
//        final Boolean IS_REMOTE = remoteDriverURL == null || remoteDriverURL.equals("") ;
//        final Boolean IS_REMOTE1 = remoteDriverURL.contains(".");
//         System.out.print(IS_REMOTE1);
//          System.out.print(Driver.FIREFOX);
//        ConfigProfile.browser=Driver.CHROME;
//        System.out.print(Driver.FIREFOX.equals(Driver.FIREFOX));
//        System.out.print(ConfigProfile.browser);
//        System.setProperty("webdriver.chrome.driver","D:/Browser/chromedriver.exe");
//        getWebDriver().get("http://baidu.com/");
//
//
//        Thread.sleep(10000);
//        closeDriver();
        ConfigBinary configBinary =new ConfigBinary();
        configBinary.setChromeBinay("D:/Browser/chromedriver.exe");

        Browser chrome = new Chrome();
        chrome.go("baidu.com");
        chrome.maxWindow();
//        System.out.print(chrome.getAllFrames().size());

        chrome.setWindowSize(800,800);
        chrome.waitTimeFor(5000);
        chrome.close();

//        ConfigBinary binary = new ConfigBinary();
//        binary.setFireFoxBinay("D:/Browser/Mozilla_Firefox/firefox.exe");
//        Browser ie = new FireFox();
//        ie.go("www.baidu.com");
//        ie.maxWindow();
//        ie.waitTimeFor(5000);
//        ie.close();




    }



}

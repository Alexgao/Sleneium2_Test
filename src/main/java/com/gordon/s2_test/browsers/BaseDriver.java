package com.gordon.s2_test.browsers;

import com.gordon.s2_test.ConfigProfile;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.gordon.s2_test.webdriver.WebDriverFactory.closeDriver;
import static com.gordon.s2_test.webdriver.WebDriverFactory.getWebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-27
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public class BaseDriver implements Browser {
    private final Logger logger = LoggerFactory.getLogger(BaseDriver.class);
    private WebDriver webDriver;

    public BaseDriver(WebDriver driver){
        this.webDriver=driver;
    }

//    前往指定网址
    @Override
    public void go(String url) {
        if (!url.startsWith("http://")){
            url="http://"+url;
        }
        this.webDriver.get(url);
        logger.info("前往网页：\"" + url + "\"");
    }


//    窗口最大化
    @Override
    public void maxWindow() {
        this.webDriver.manage().window().maximize();
        logger.info("窗口最大化.");
    }


//    设置当前窗口大小
    @Override
    public void setWindowSize(final int width,final int height) {

        int maxWidth = Integer.parseInt(runJS().executeScript("return screen.availWidth;").toString());
        int maxHeight = Integer.parseInt(runJS().executeScript("return screen.availHeight;").toString());

        if(width > maxWidth || height > maxHeight){
            maxWindow();
            logger.warn("抱歉，当前窗口指定值过大，将自动跳转到默认最大化。");
        }else if (width < 10 || height < 10){
            maxWindow();
            logger.warn("抱歉，指定值不能小于10.");
        }else {
            this.webDriver.manage().window().setSize(new Dimension(width,height));
            logger.info("设置成功，当前窗口宽为："+width+"\t高为："+height);
        }

    }




    //    页面后退一步
    @Override
    public void back() {
        this.webDriver.navigate().back();
        logger.info("当前页面["+this.webDriver.getTitle()+"]后退一步.");
    }


//    页面前进一步
    @Override
    public void forward() {
        this.webDriver.navigate().forward();
        logger.info("当前页面["+this.webDriver.getTitle()+"]前进一步.");
    }


//    页面刷新
    @Override
    public void refresh() {
        this.webDriver.navigate().refresh();
        logger.info("当前页面["+this.webDriver.getTitle()+"]刷新.");
    }

    /**
     *
     * 点击页面某个元素
     *
     * @param element
     */
    @Override
    public void click(WebElement element){
        if (element != null || !element.toString().equals("")){
            element.click();
        }else {
            logger.warn("抱歉，给出的指定元素为空。");
        }
    }

    /**
     *
     * 点击页面某个元素
     *
     * @param by
     */
    @Override
    public void click(By by){
        WebElement element = this.webDriver.findElement(by);
        if (!element.toString().equals("")||element != null){
            element.click();
        }else {
            logger.error("没有找到指定元素，{}为：{}",by.getClass().getSimpleName(),by.toString());
        }

    }

    @Override
    public void click(String nameOrId) {
        findElement(nameOrId).click();
    }

    /**
     * 获取页面某个元素
     * @param by 元素的By值
     * @return 一个web元素
     */
    @Override
    public WebElement findElement(By by){
        WebElement element = this.webDriver.findElement(by);
        if (element.toString().equals("")||element == null){
            logger.error("没有找到指定的元素，{}为：{}",by.getClass().getSimpleName(),by.toString());
            return null;
        }
        return element;
    }

    /**
     * 通过Name或者Id查找单个元素
     * @param nameOrId Name或者Id的值
     * @return  一个web元素
     */
    @Override
    public WebElement findElement(String nameOrId) {
        WebElement element=this.webDriver.findElement(By.id(nameOrId));
        if (element!=null||element.equals("")){
            return element;
        }else {
            List<WebElement> elements = this.webDriver.findElements(By.name(nameOrId));
            if (elements.size()>1){
                logger.warn("请注意，你给定的\"name\"为\"{}\"的元素不止一个，我们将默认返回第一个给你。",nameOrId);
                return elements.get(0);
            }else if (elements.size() < 1 || elements.get(0) == null){
                logger.error("抱歉，你所给出的Id或者Name为{}的元素不存在。",nameOrId);
                return null;
            }else {
                return elements.get(0);
            }

        }

    }

    /**
     * 通过By获取Web元素
     * @param by
     * @return
     */
    @Override
    public List<WebElement> findElements(By by){
        List<WebElement> elements = this.webDriver.findElements(by);
        if (elements.size() < 1 || elements.get(0) == null){
            logger.error("抱歉，你所给出的{}为{}的元素并不存在。",by.getClass().getSimpleName(),by.toString());
            return null;
        }else {
            return elements;
        }

    }

    /**
     * 通过Name或者ClassName获取元素
     * @param nameOrClassName Name的标签值或者ClassName的值
     * @return 返回获取的所有WebElement
     */
    @Override
    public List<WebElement> findElements(String nameOrClassName) {
        List<WebElement> elements = this.webDriver.findElements(By.name(nameOrClassName));
        if (elements.size()<1||elements.get(0)==null){
            List<WebElement> elements1 = this.webDriver.findElements(By.className(nameOrClassName));
            if (elements1.size() < 1 || elements1.get(0) == null){
                logger.error("错误，你所给出的Name或者ClassName为：{}的元素并不存在。",nameOrClassName);
                return null;
            }else {
                return elements1;
            }
        }else {
            return elements;
        }


    }

    /**
     * 获取当前正在使用的Driver
     * @return 返回当前Driver
     */
    @Override
    public WebDriver getDriver() {
        return getWebDriver();
    }

    @Override
    public Boolean elementIsVisible(By by) {
        List<WebElement> webElements = findElements(by);
        try{
            return webElements.size() > 0 && webElements.get(0).isDisplayed();
        }catch (Exception e){
            logger.error("错误，元素不存在，{}为：{}",by.getClass().getSimpleName(),by.toString());
            return false;
        }
    }

    /**
     * 判断元素是否显示在页面上
     * @param elementDescription 对于元素的描述
     * @param by 元素的By值
     * @param waitTimeOut 等待超时时间
     * @return 如果显示返回被判断的元素，否则为空
     */
    @Override
    public WebElement waitForElementsVisible(final String elementDescription, final By by ,final int waitTimeOut) {
        return waitForCondition(elementDescription + "是显示的。",waitTimeOut,
            new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(final WebDriver driver) {
                    List<WebElement> elements = driver.findElements(by);
                    return elements.size() > 0 && elements.get(0).isDisplayed() ? elements.get(0) : null;
                }
            }
        );
    }

    @Override
    public WebElement waitForElementsVisible(final String elementDescription, final By by) {
        return waitForElementsVisible(elementDescription,by,0);
    }

    @Override
    public WebElement waitForElementsVisible(final By by, final int waitTimeOut) {
        return waitForElementsVisible(by.getClass().getSimpleName()+"为："+by.toString()+"的元素,",by,waitTimeOut);
    }

    @Override
    public WebElement waitForElementsVisible(By by) {
        return waitForElementsVisible(by.getClass().getSimpleName()+"为："+by.toString()+"的元素,",by,0);
    }

    @Override
    public WebElement waitForElementsPresent(final String elementDescription,final By by,final int waitTimeOut) {
        return waitForCondition(elementDescription + "是存在的。",waitTimeOut,
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        List<WebElement> elements = driver.findElements(by);
                        return elements.size() > 0 ? elements.get(0) : null;
                    }
                }
        );
    }

    @Override
    public WebElement waitForElementsPresent(String elementDescription, By by) {
        return waitForElementsPresent(elementDescription,by,0);
    }

    @Override
    public WebElement waitForElementsPresent(By by, int waitTimeOut) {
        return waitForElementsPresent(by.getClass().getSimpleName()+"为："+by.toString()+"的元素,",by,waitTimeOut);
    }

    @Override
    public WebElement waitForElementsPresent(By by) {
        return waitForElementsPresent(by.getClass().getSimpleName()+"为："+by.toString()+"的元素,",by,0);
    }


    /**
     * 判断元素是否存在
     * @param by 元素的By值
     * @return  返回一个布尔值
     */
    @Override
    public Boolean elementIsPresent(By by) {
        return this.webDriver.findElements(by).size()>0;

    }

    @Override
    public Object executeScript(String script) {
        return runJS().executeScript(script);
    }

    @Override
    public String executeScriptGetValue(String script) {
        return String.valueOf(executeScript(script));
    }


    //    截图并保存  - 该方法现在还没实现
    @Override
    public void takeScreetShot(StringBuffer name,StringBuffer file) {
        TakesScreenshot tss = (TakesScreenshot)this.webDriver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = sdf.format(new Date());
        File screenshot = tss.getScreenshotAs(OutputType.FILE);

    }


    /**
     *  获取所有窗口句柄
      * @return 获取的所有窗口句柄
     */
    @Override
    public Set<String> getWindows() {
        logger.info("获取所有窗口句柄");
        return this.webDriver.getWindowHandles();  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *   选择窗口
     *
     * @param windowHandle 窗口句柄
     */
    @Override
    public void selectWindow(String windowHandle) {
        this.webDriver.switchTo().window(windowHandle);
        logger.info("切换至窗口,窗口句柄为：{}",windowHandle);

    }

    /**
     *
     * 获取所有的Frame
     *
     * @return 返回获取的所有关于frame的元素
     */
    @Override
    public List<WebElement> getAllFrames() {
        List<WebElement> iframes = this.webDriver.findElements(By.tagName("iframe"));
        List<WebElement> frames = this.webDriver.findElements(By.tagName("frame"));
        for (WebElement frame : iframes){
            frames.add(frame);
        }
        if (frames.size()<1||frames.get(0)==null){
            logger.warn("#*# WARN: #*#  抱歉，页面中没有找到Frame. ");
        }
        return frames;
    }

    /**
     *   切换到Frame
     *
     *
     **/
    @Override
    public void selectFrame(By by) {
        this.webDriver.switchTo().frame(findElement(by));
        logger.info("切换到Frame，{}为：{}。",by.getClass().getSimpleName(),by.toString());
    }

    @Override
    public void selectFrame(WebElement element) {
        this.webDriver.switchTo().frame(element);
        logger.info("切换到Frame，WebElement为：{}。", element.toString());
    }

    @Override
    public void selectFrame(String nameOrId) {
        this.webDriver.switchTo().frame(nameOrId);
        logger.info("切换到Frame，Name或者Id为：{}。", nameOrId);
    }

    @Override
    public void selectFrame(int index) {
        this.webDriver.switchTo().frame(index);
        logger.info("切换到Frame，在网页中的索引为：{}。", index);
    }

    /**
     *
     *    切换到默认的窗口。
     *
     * **/
    @Override
    public void selectDefaultWindow() {
        this.webDriver.switchTo().defaultContent();
        logger.info("切换到默认窗口.");
    }



    /**
     *
     *  JS运行器
     *
     * **/
    @Override
    public JavascriptExecutor runJS() {
        try {
            JavascriptExecutor js = (JavascriptExecutor)this.webDriver;
            return js;
        }catch (Exception e){
            logger.warn("JS转换错误");
            return null;
        }

    }


    /***
     *
     *   清楚所有的Cookies
     *
     * */
    @Override
    public void clearAllCookies() {
        this.webDriver.manage().deleteAllCookies();
        logger.info("清除所有的Cookies。");
    }

    @Override
    public void clearCookies(Cookie cookie) {
        this.webDriver.manage().deleteCookie(cookie);
        logger.info("清除Cookie为：{}",cookie.toString());
    }


    //    指定等待时间
    @Override
    public void waitTimeFor(Integer time) {
        try {
            Thread.sleep(time);
            logger.info("页面等待：{}毫秒。",time);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


//    关闭浏览器
    @Override
    public void close() {
        closeDriver();
        logger.info("关闭浏览器。");
    }

    @Override
    public void closeCurrent() {
        this.webDriver.close();
        logger.info("关闭当前浏览器。");
    }


    public <T> T waitForCondition(String description , int waitTimeOut , ExpectedCondition<T> expectedCondition){
        final int timeOut = waitTimeOut < 500 ? ConfigProfile.waitTimeOut : waitTimeOut;

        FluentWait<WebDriver> wait = new WebDriverWait(getDriver() , timeOut);
        wait.withMessage(description);
        return wait.until(expectedCondition);
    }


}

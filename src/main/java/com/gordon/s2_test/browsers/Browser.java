package com.gordon.s2_test.browsers;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-27
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public interface Browser {

    //打开网址
    void go(String url);

    //窗口最大化
    void maxWindow();

    //指定窗口大小
    void setWindowSize(int width, int height);

    //后退
    void back();

    //前进
    void forward();

    //刷新当前窗口
    void refresh();

    //截取当前屏幕
    void takeScreetShot(StringBuffer name,StringBuffer file);

    //获取当前所有窗口
    Set<String> getWindows();

    //切换Window
    void selectWindow(String windowHandle);

    //获取所有的Frame
    List<WebElement> getAllFrames();

    //切换到Frame
    void selectFrame(By by);

    void selectFrame(WebElement element);

    void selectFrame(String id);

    void selectFrame(int index);

    //选择默认窗口并返回，（由于还没写到window接口）
    void selectDefaultWindow();

    //运行JavaScript
    JavascriptExecutor js();

    Object executeScript(String script);

    String executeScriptGetValue(String script);


    //清理所有Cookies
    void clearAllCookies();

    void clearCookies(Cookie cookie);

    //获取Cookies
    Set<Cookie> getCookies();

    Cookie getCookieNamed(String cookieName);


    //设置固定等待时间
    void waitTimeFor(Integer time);


    //关闭所有窗口
    void close();


    //关闭当前窗口
    void closeCurrent();


    //点击某个元素
    void click(WebElement element);

    void click(By by);

    void click(String id);

    void jQueryClick(String cssSelecter);


    //输入
    void sendKeys(By by, CharSequence keys);

    void sendKeys(String messages, By by, CharSequence keys);

    void sendKeys(String messages, WebElement element, CharSequence keys);

    void sendKeys(WebElement element, CharSequence keys);


    //鼠标移到元素上
    void hoverOver(By by);

    void hoverOver(WebElement element);

    void hoverOver(String id);




    //通过By查找单个Element
    WebElement findElement(By by);

    WebElement findElement(String nameOrId);




    //通过By查找多个Element
    List<WebElement> findElements(By by);

    List<WebElement> findElements(String nameOrClassName);


    //获取当前的Driver
    WebDriver getDriver();


    //判断元素是否显示
    Boolean elementVisible(By by);

    Boolean elementVisible(WebElement element);

    Boolean elementIdVisible(String id);


    /**
     * 判断元素是否存在
     * @param by 元素的By值
     * @return  返回一个布尔值
     */
    Boolean elementPresent(By by);

    Boolean elementPresent(String id);



    /**
     * 判断元素是否显示在页面上
     * @param elementDescription 对于元素的描述
     * @param by 元素的By值
     * @param waitTimeOut 等待超时时间
     * @return 如果显示返回被判断的元素，否则为空
     */
    WebElement waitForElementsVisible(String elementDescription, By by ,int waitTimeOut);

    WebElement waitForElementsVisible(String elementDescription, By by );

    WebElement waitForElementsVisible(By by ,int waitTimeOut);

    WebElement waitForElementsVisible(By by );

//    WebElement waitForElementsVisible(String elementDescription,WebElement element,int waitTimeOut);
//
//    WebElement waitForElementsVisible(String elementDescription,WebElement element);
//
//    WebElement waitForElementsVisible(WebElement element,int waitTimeOut);
//
//    WebElement waitForElementsVisible(WebElement element);



    /**
     * 判断元素是否存在在页面上
     * @param elementDescription 对于元素的描述
     * @param by 元素的By值
     * @param waitTimeOut 等待超时时间
     * @return 如果存在返回被判断的元素，否则为空
     */
    WebElement waitForElementsPresent(String elementDescription,By by,int waitTimeOut);

    WebElement waitForElementsPresent(String elementDescription,By by);

    WebElement waitForElementsPresent(By by,int waitTimeOut);

    WebElement waitForElementsPresent(By by);









}

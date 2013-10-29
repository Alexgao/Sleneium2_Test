package com.gordon.s2_test.tests;

import com.gordon.s2_test.categories.ShouldTest;
import com.gordon.s2_test.categories.SmokeTest;
import com.gordon.s2_test.common.BaseTest;
import com.gordon.s2_test.page.login.Login;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-10-9
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */

public class LoginTests extends BaseTest {
    Login login = new Login(browser);

    @Category({SmokeTest.class,ShouldTest.class})
    @Test
    public void testLogin(){
        login.openLoginRegisterWindow();
        login.switchLoginFrame();
        waitForTime(2000);
        login.inputUserName();
//        login.inputPassWordAndEnter();
        login.inputPassWord();
        login.clickLoginButton();

    }

    @Category({SmokeTest.class,ShouldTest.class})
    @Test
    public void testLogout(){
        login.openUserStateList();
        login.clickLogoutLink();
    }
}

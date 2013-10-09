package com.gordon.s2_test.tests;

import com.gordon.s2_test.common.BaseTest;
import com.gordon.s2_test.page.Login;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-10-9
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class LoginTests extends BaseTest {
    Login login = new Login();

    @Test
    public void testLogin(){
        login.openLoginWindow();

    }
}

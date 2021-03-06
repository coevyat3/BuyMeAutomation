package testcases;

import buymeObjects.*;
import helper.resource.ResourceHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import utils.ReadingSheets;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;
    GiftCardPage giftCardPage;
    WhoToSendPage whoToSendPage;
    HowToSendPage howToSendPage;
    HomePage homePage;
    PaymentPage paymentPage;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        loadConfig(browser);
        navBarPage= new NavBarPage();
        searchResultPage= new SearchResultPage();
        giftCardPage= new GiftCardPage();
        whoToSendPage= new WhoToSendPage();
        howToSendPage= new HowToSendPage();
        loginPage= new LoginPage();
       homePage= new HomePage();
        paymentPage=new PaymentPage();

    }
    @Test
    public void testLogin()  {
        loginPage= new LoginPage();
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        whoToSendPage= giftCardPage.insertAmount(Constants.amount);
        howToSendPage= whoToSendPage.sendAll(Constants.FriendName,Constants.Bless,Constants.OwnBless, ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));
        loginPage= howToSendPage.sendAll("יולי","21","13:30","s1@walla.com","me");
        loginPage.login(Constants.email,Constants.password);

    }
    @Test(groups = {"dataDrivenTest"},dataProvider ="loginData" )
    public void doLogin(String email,String password){
      homePage.gotoLogin();
      loginPage.doLogin(email,password);
    }
    @DataProvider(name = "loginData")
    public Iterator<Object[]> sendLoginData(){
        ArrayList<Object[]> data = ReadingSheets.getLoginData();
        return data.iterator();
    }



}

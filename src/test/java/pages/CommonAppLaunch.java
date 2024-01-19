package pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import testBase.BaseTest;
import testUtils.Utils;

import java.io.IOException;
import java.time.Duration;


public class CommonAppLaunch extends BaseTest {

    Logger log = Logger.getLogger(CommonAppLaunch.class);


    @FindBy(id = "title")
    WebElement lblHomeTitle;

    @FindBy(xpath = "//a[text()='Log in' and @class='Buttonsstyles__Button-sc-1jwidxo-0 kTwZBr']")
    WebElement lnkLogin;

    @FindBy(xpath = "//*[@id='user']")
    WebElement txtUserLogin;

    @FindBy(xpath = "//*[@id='login']")
    WebElement btnContinue;

    @FindBy(xpath = "//*[@name='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//*[@id='login-submit']/span")
    WebElement btnLogin;

    @FindBy(xpath = "//h2[text()='Most popular templates']")
    WebElement lblDashBoardHeading;

    @FindBy(xpath = "//*[@class='X7iA6JJNiXCA2r' or text()='Create']")
    WebElement btnCreateFirst;

    @FindBy(xpath = "//span[text()='Create board']")
    WebElement btnSelectCreateBoard;

    @FindBy(xpath = "//*[text()='Board title']/following::input[1]")
    WebElement txtBoardTitle;

    @FindBy(xpath = "//button[text()='Create']")
    WebElement btnCreateFinal;

    @FindBy(xpath = "//h1[@class='HKTtBLwDyErB_o']")
    WebElement lblTopBoardName;

    @FindBy(xpath = "//*[@class='DD3DlImSMT6fgc XQSLFE3ZZrvms3' and text()='Boards']")
    WebElement lnkLeftPaneBoard;

    @FindBy(xpath = "//a[@class='board-tile']")
    WebElement lnkFirstBoard;

    @FindBy(xpath = "//textarea[text()='To Do']")
    WebElement lblToDO;

    @FindBy(xpath = "//textarea[text()='Doing']")
    WebElement lblDoing;

    @FindBy(xpath = "//textarea[text()='Done']")
    WebElement lblDone;

    @FindBy(xpath = "//input[@class='aT0m6qQxTMOyb8']")
    WebElement txtTopBoardName;

    @FindBy(xpath = "//*[@class='nch-icon A3PtEe1rGIm_yL J2CpPoHYfZ2U6i fAvkXZrzkeHLoc']")
    WebElement txtTopRightSideThreeDotBoard;

    @FindBy(xpath = "//a[@class='board-menu-navigation-item-link board-menu-navigation-item-link-v2 js-close-board']")
    WebElement lnkCloseBoard;

    @FindBy(xpath = "//input[@class='js-confirm full nch-button nch-button--danger']")
    WebElement btnCloseButton;

    @FindBy(xpath = "//button[text()='Permanently delete board']")
    WebElement btnPermCloseBoard;

    @FindBy(xpath = "//button[text()='Delete']")
    WebElement btnDeleteBoardFinal;



    // Initializing the Page Object
    public CommonAppLaunch(){
        PageFactory.initElements(driver,this);
    }

//**************************************** ACTIONS *******************************************************************

    public void fnLaunchApp() throws IOException {
      // App launch from Before class
        driver.get(Utils.getGlobalValues("appUrl"));
    }
    public void fnValidateLoginPageTitle(String expectedTitle){
         String actualTitle = driver.getTitle();
         Assert.assertEquals(actualTitle,expectedTitle,"User is on Trello Home Page");
         log.info("User is on Trello Home Page");
    }

    public void fnGoToLoginPage(){
        lnkLogin.click();
    }
    public void fnUserEnterLoginID() throws IOException {
        txtUserLogin.sendKeys(Utils.getGlobalValues("userId"));
        btnContinue.click();
    }
    public void fnUserEnterPassword() throws IOException, InterruptedException {
        explicitlyWait(txtPassword);
        txtPassword.sendKeys(Utils.getGlobalValues("password"));
        btnLogin.click();
    }
    public void fnTrelloDashBoardLanding() throws IOException {
        explicitlyWait(lblDashBoardHeading);
        String dashboardTitle = driver.getTitle();
        Assert.assertEquals(dashboardTitle,"Boards | Trello","User is logged in and landed on Trello dashboard page");
    }

    public void fnClickCreateBoard(){
        btnCreateFirst.click();
        btnSelectCreateBoard.click();
    }

    public void fnEnterBoardName(String name){
        txtBoardTitle.sendKeys(name);
    }

    public void fnSubmitCreateBoard(){
        btnCreateFinal.click();
    }

    public void fnVerifyBoardIsCreated(String boardName){
        explicitlyWait(lblTopBoardName);
        String actualBoardName = lblTopBoardName.getText();
        Assert.assertEquals(actualBoardName,boardName,"Verify that the board is created in workspace with same same");
    }

    public void fnClickOnBoardLinkLeftPane(){
        lnkLeftPaneBoard.click();
    }

    public void fnClickOnFirstBoard(){
        explicitlyWait(lnkFirstBoard);
        lnkFirstBoard.click();
    }

    public void fnValidateBoardsComponentDisplayed(){
        explicitlyWait(lblToDO);
        Assert.assertTrue(lblToDO.isDisplayed()," Tile ToDo is displayed ");
        Assert.assertTrue(lblDoing.isDisplayed()," Tile Doing is displayed ");
        Assert.assertTrue(lblDone.isDisplayed()," Tile Done is displayed ");
    }

    public void fnChangeBoardName(){
        explicitlyWait(lblTopBoardName);
        lblTopBoardName.click();
        txtTopBoardName.sendKeys("My Name Changed");
    }

    public void fnDeleteTheBoard(){
        explicitlyWait(txtTopRightSideThreeDotBoard);
        txtTopRightSideThreeDotBoard.click();
        lnkCloseBoard.click();
        btnCloseButton.click();
        explicitlyWait(btnPermCloseBoard);
        btnPermCloseBoard.click();
        btnDeleteBoardFinal.click();
    }
}

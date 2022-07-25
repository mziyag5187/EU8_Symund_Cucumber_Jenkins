package net.seamlessly.pages;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.seamlessly.step_definitions.DeckModule_StepDefs;
import net.seamlessly.utilities.BrowserUtils;
import net.seamlessly.utilities.Driver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DeckModulePage extends BasePage {


    public final String list_2_Name ="List2";


    public DeckModulePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@aria-controls='app-navigation-vue']")
    public WebElement toggleButton;

    @FindBy(xpath = "//div[@id='app-navigation-vue']//a[@href='#']")
    public WebElement addBoard;

    @FindBy(xpath = "//input[@placeholder='Board name']")
    public WebElement boardNameInputBox;

    @FindBy(xpath = "//input[@class='icon-confirm']")
    public WebElement confirmIcon;


    @FindBy(xpath = "//button[@icon='icon-add']")
    public WebElement addListIcon;

    @FindBy(xpath = "//div[@id='stack-add']//input[@id='new-stack-input-main']")
    public WebElement enterListNameInputBox;

    @FindBy(xpath = "//div[@class='stack']//button[@icon='icon-add']")
    public List<WebElement> addCardButton;
    @FindBy(xpath = "//input[@id='new-stack-input-main']")
    public WebElement cardNameInput;

    @FindBy(xpath = "(//div[@class='open']//li//button)[1]")
    public WebElement assignToMe;

    @FindBy(xpath = "(//div[@class='open']//li//button)[1]//span[2]")
    public WebElement assignToMeText;

    @FindBy(xpath = "//div[@class='avatar-list']//img")
    public WebElement assignedAvatar;


    @FindBy(xpath = "//div[@class='smooth-dnd-container vertical']//div//button[@aria-label='Actions']")
    public List<WebElement> threeDotsOnCards;

    @FindBy(xpath = "(//div[@class='open']//li)[2]//button")
    public WebElement moveCardOnDropdown;

    @FindBy(xpath = "(//ul[@class='multiselect__content']//li)[2]")
    public WebElement board2ToSelect;


    @FindBy(xpath = "//span[@class='action-button__icon icon-delete']/..")
    public WebElement deleteBoard;

    @FindBy(xpath = "//button[@class='error primary']")
    public WebElement deleteBoardConfirm;



    public void checkcreatedBoard() {
        WebElement board = Driver.getDriver().findElement(By.xpath("//span[@title='" + getBoardName() + "']") );
        BrowserUtils.sleep(1);
        Assert.assertTrue(board.isDisplayed());
    }



    public void clickAnyCreatedBoard() {
        WebElement createdBoard = Driver.getDriver().findElement(By.xpath("//span[@title='" + getAnyNameFromCreatedBoards() + "']/.."));
        createdBoard.click();
    }










    public void deleteBoards(){

        List<WebElement> boardsThreeDots = Driver.getDriver().findElements(By.xpath("//ul[@class='app-navigation-entry__children']//button[@aria-label='Actions']"));
        if (boardsThreeDots.size() != 0){
            for (WebElement eachBoardsThreeDot : boardsThreeDots) {
                eachBoardsThreeDot.click();
                deleteBoard.click();
            }
        }

    }


    Faker faker = new Faker();
    private String boardName;

    //take the name with title attribute
    @FindBy(xpath = "//ul[@class='app-navigation-entry__children']//span[@class='app-navigation-entry__title']")
    public List<WebElement> createdBoardsNamesFromSpan;

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName() {

        this.boardName = "Board" + faker.number().randomNumber();
    }

    public String createNewBoardName(){
        setBoardName();
        for (WebElement eachCreatedBoard : createdBoardsNamesFromSpan) {
            if (eachCreatedBoard.getAttribute("title").equals(getBoardName())){
                setBoardName();
            }
        }

        return getBoardName();
    }

    public String getAnyNameFromCreatedBoards(){
        int randomBoardIndex = faker.number().numberBetween(0,createdBoardsNamesFromSpan.size()-1);
        return createdBoardsNamesFromSpan.get(randomBoardIndex).getAttribute("title");
    }



    public void clickAnyBoardNameFromCreatedBoards(){

        int indexOfBoard = faker.number().numberBetween(1,createdBoardsNamesFromSpan.size());

        Driver.getDriver().findElement(By.xpath("(//ul[@class='app-navigation-entry__children']//a)[" + indexOfBoard + "]")).click();


    }

    private String listName;

    public String getListName() {
        return listName;
    }

    public void setListName() {
        this.listName = "List" + faker.number().randomNumber();
    }

    public void checkCreatedListName() {

        WebElement createdList = Driver.getDriver().findElement(By.xpath("//h3[contains(text(),'" + getListName() + "')]"));
        Assert.assertTrue(createdList.isDisplayed());
    }

    public void clickAnyAddCardButtonOnTheCurrentBoard() {
        addCardButton.get(faker.number().numberBetween(1,addCardButton.size())).click();
    }

    private String cardName;

    public String getCardName() {
        return cardName;
    }

    public void setCardName() {
        this.cardName = "Card" + faker.number().randomNumber();
    }

    public void  checkCreatedCard() {
        WebElement createdCard = Driver.getDriver().findElement(By.xpath("//span[.='" + getCardName() + "']"));
        Assert.assertTrue(createdCard.isDisplayed());
    }

    public void clickAnyThreeDotsOnCards() {

        threeDotsOnCards.get(faker.number().numberBetween(1,threeDotsOnCards.size())).click();
    }

    public void clickAssignToMe(){
        if (assignToMeText.getText().equals("Assign to me")){
            assignToMe.click();
        }else {
            
        }
    }

}

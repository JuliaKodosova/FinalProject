package my_project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailPage {
	@FindBy(xpath = "//input[@id='mailbox:login']")
	WebElement loginField;
	@FindBy(xpath = "//*[@id='mailbox:password']")
	WebElement passwordField;
	@FindBy(xpath = "//input[@class='o-control']")
	WebElement enterButton;
	@FindBy(xpath = "(//a[@data-name='compose'])[1]")
	WebElement composeEmailButton;
	@FindBy(xpath = "//textarea[@data-original-name='To']")
	WebElement toField;
	@FindBy(xpath = "//body[@id='tinymce']")
	WebElement messageArea;
	@FindBy(xpath = "//iframe[contains(@id,'composeEditor_ifr')]")
	WebElement emailBodyFrame;
	@FindBy(xpath = "(//div[@data-mnemo=\"toolbar-compose\"]//*[@data-name=\"send\"])[1]")
	WebElement sendButton;
	@FindBy(xpath = "//a[text()='Перейти во Входящие']")
	WebElement openMailsLink;
	@FindBy(xpath = "(//span[@class='b-nav__item__text'])[5]")
	WebElement spamFolder;
	@FindBy(css = ".js-item-checkbox")
	WebElement selectEmailCheckbox;
	@FindBy(xpath = "(//div[@data-name='spam'])[1]")
	WebElement spamField;
	@FindBy(xpath = "(//div[@data-name='noSpam'])[1]")
	WebElement noSpamFolder;
	@FindBy(xpath = "//*[@id=\"b-letters\"]/div[1]/div[5]/div/div[2]/div[1]/div/a/div[1]")
	WebElement checkEmail2;
	@FindBy(xpath = "//button[@class='btn b-spam-confirm__btn']")
	WebElement selectYes;
	@FindBy(xpath = "//span[@class='b-nav__item__text b-nav__item__text_unread']")
	WebElement inboxFolder;
	@FindBy(xpath = "//div[@class='js-item-checkbox b-datalist__item__cbx']")
	List<WebElement> checkboxes;
	@FindBy(xpath = "(//div[@data-group='letters-more'])[1]")
	WebElement moreDropdown;
	@FindBy(xpath = "//a[@data-name='flag_yes']")
	WebElement markFlag;
	@FindBy(xpath = "//a[@data-name='flag_no']")
	WebElement unMarkFlag;
	@FindBy(xpath = "//div[@class='notify']")
	WebElement confirmationMessage;
	@FindBy(xpath = "//div[@class='js-hover b-flag b-flag_yes b-flag_onhover']")
	WebElement flag;

	private WebDriver driver;
	private WebDriverWait wait;

	public MailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}

	public void enterLoginAndPassword(String login, String password) {
		loginField.clear();
		loginField.sendKeys(login);
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void ckickEnterButton() {
		enterButton.click();
		wait.until(ExpectedConditions.titleContains("Входящие"));
	}

	public boolean inboxLinkPresents() {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Входящие"));
	}
	public void composeEmail() {
		composeEmailButton.click();
	}

	public void sendTo(String email1, String email2) {
		toField.sendKeys(email1);
		toField.sendKeys(Keys.SPACE);
		toField.sendKeys(email2);
		toField.sendKeys(Keys.SPACE);
	}

	public void enterText(String text) {
		driver.switchTo().frame(emailBodyFrame);
		messageArea.sendKeys("text");
		driver.switchTo().defaultContent();
	}

	public void sendEmail() {
		sendButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Перейти во Входящие']")));
	}

	public void openMail() {

		openMailsLink.click();
		wait.until(ExpectedConditions.urlContains("inbox"));
		wait.until(ExpectedConditions.elementToBeClickable(selectEmailCheckbox));

	}

	public void selectEmail() {
		selectEmailCheckbox.click();
		selectEmailCheckbox.isDisplayed();

	}

	public void moveToSpam() {

		spamField.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(selectYes));
			selectYes.click();
		} catch (Exception e) {
			System.out.print("Do nothing");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='notify']")));
	}

	public void openSpamFolder() {
		spamFolder.click();
		wait.until(ExpectedConditions.urlContains("spam"));
		wait.until(ExpectedConditions.elementToBeClickable(checkEmail2));
	}

	public void selectEmail2() {
		checkEmail2.click();
	}

	public void moveToNotSpam() {
		noSpamFolder.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='notify']")));

	}

	public void openInbox() {
		inboxFolder.click();
		wait.until(ExpectedConditions.urlContains("inbox"));

	}

	public void clickCheckbox() {
		Actions action = new Actions(driver);
		action.moveToElement(checkboxes.get(0)).build().perform();
		checkboxes.get(0).click();
		action.moveToElement(checkboxes.get(1)).build().perform();
		checkboxes.get(1).click();
		action.moveToElement(checkboxes.get(2)).build().perform();
		checkboxes.get(2).click();
	}

	public void openMoreDropdown() {
		moreDropdown.click();
	}

	public void markWithFlag() {
		markFlag.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='js-hover b-flag b-flag_yes b-flag_onhover']")));

	}

	public void unMarkWithFlag() {
		unMarkFlag.click();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='js-hover b-flag b-flag_yes b-flag_onhover']")));
	}

	public boolean IsConfirmationMessagePresent() {
		return confirmationMessage.isDisplayed();
	}

	public boolean IsFlagPresent() {
		try {
			return flag.isDisplayed();
		}

		catch (Exception e) {
			return false;
		}
	}
	
	

}
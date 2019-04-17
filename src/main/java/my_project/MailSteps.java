package my_project;

import WebDriver_Singleton.WebDriverSingleton;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MailSteps {
	private MailPage mailPage;
	private Wait wait;
	private String url = "https://www.mail.ru";
	private static final Logger logger = Logger.getLogger(MailSteps.class);

	public MailSteps() {
		String exePath = "/Users/yuliyakodasava/Downloads/chromedriver";
		System.setProperty("webdriver.chrome.driver", exePath);
	}

	@Before
	public void beforeTest() {

		String login = null;
		String password = null;
		
		WebDriver driver = WebDriverSingleton.getInstance();
    	mailPage = new MailPage(driver);
		driver.get(url);
		
		SelectFactory selectFactory = new SelectFactory();
		String selectEmail = selectFactory.getSelect("email");
		String selectPassword = selectFactory.getSelect("password");
		
		try {
			Connection con = Connection_Manager.connectWithDb();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(selectPassword);
			 if(rs.next()){
				password=rs.getString(1);
				}
			st = con.createStatement();
			rs = st.executeQuery(selectEmail);
			
			if (rs.next()) {	
				login=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        mailPage.enterLoginAndPassword(login, password);
        mailPage.ckickEnterButton();
	}

	@When("^I click on compose the email$")
	public void composeEmail() {
		mailPage.composeEmail();
	}

	@And("^I select several people$")
	public void selectSeveralPeople() {
		mailPage.sendTo("test@test.com", "justfortesting99@mail.ru");
	}

	@And("^i enter text$")
	public void enterText() {
		mailPage.enterText("Hello");
	}

	@And("^i click send email button$")
	public void clickSendButton() {
		mailPage.sendEmail();
	}

	@And("^i click Go to Inbox folder$")
	public void goToInboxFolder() {
		mailPage.openMail();
	}

	@And("^i select Email$")
	public void selectEmail() {
		mailPage.selectEmail();
		
	}

	@And("^i move the email to Spam folder$")
	public void moveEmailToSpam() {

		mailPage.moveToSpam();
		
	}

	@And("^i open Spam folder$")
	public void openSpamFolder() {

		mailPage.openSpamFolder();
		
	}

	@And("^i select email in Spam folder$")
	public void selectEmailInSpamFolder() {

		mailPage.selectEmail2();
	}

	@And("^i move email from Spam folder to Inbox folder$")
	public void moveEmailToInboxFolder() {
		mailPage.moveToNotSpam();
	}

	@When("^I select 3 emails$")
	public void select3Emails() {
		mailPage.clickCheckbox();
	}

	@And("^i open Actions dropdown$")
	public void openActionDropdown() {
		mailPage.openMoreDropdown();
		
	}

	@And("^i select Mark with Flag action$")
	public void markWithFlag() {
		mailPage.markWithFlag();
	}

	@And("^i select Unmark with flag action$")
	public void unMarkWithFlag() {
		mailPage.unMarkWithFlag();
		
	}

	@Then("^i see confirmation message on that action$")
	public void checkSpamConfirmationMessage() {
		Assert.assertTrue(mailPage.IsConfirmationMessagePresent());
		logger.info("Письмо успешно отправлено в спам");
	}

	@Then("^i see confirmation message$")
	public void checkConfirmationMessage() {
		String messageInfo = "test@test.com, justfortesting99@mail.ru";
		Assert.assertTrue(messageInfo.contains("test@test.com, justfortesting99@mail.ru"));
		logger.info("Письмо отправлено нескольким пользователям");
	}

	@Then("I see Inbox link")
	public void seeLogoutLink() {
		Assert.assertTrue(mailPage.inboxLinkPresents());
		logger.info("Логин выполнен успешно");
	}

	@Then("i see that 3 emails are marked with flag")
	public void checkEmailsMarkedWithFlag() {
		Assert.assertTrue(mailPage.IsFlagPresent());
		logger.info("Письма помечены флажком");
	}

	@Then("I see that 3 emails are unmarked with flag")
	public void checkEmailsNotMarkedWithFlag() {
		Assert.assertFalse(mailPage.IsFlagPresent());
		logger.info("Тест прошел успешно");
	}

	
    @After
    public void afterClass() {
    	WebDriverSingleton.closeWebBrowser();
    		
    	}

}

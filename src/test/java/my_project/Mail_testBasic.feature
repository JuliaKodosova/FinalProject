Feature: Test basic scenarios on Mail.ru

Scenario: Test positive login
Then I see Inbox link



Scenario: Test sending email to a several users
When I click on compose the email
And I select several people 
And i enter text
And i click send email button
Then i see confirmation message

Scenario: Test moving the email to Spam folder
When I click on compose the email
And I select several people 
And i enter text
And i click send email button
And i click Go to Inbox folder
And i select Email
And i move the email to Spam folder
Then i see confirmation message on that action

Scenario: Test moving the email from Spam folder to Inbox folder
When I click on compose the email
And I select several people 
And i enter text
And i click send email button
And i click Go to Inbox folder
And i select Email
And i move the email to Spam folder
And i open Spam folder
And i select email in Spam folder
And i move email from Spam folder to Inbox folder
Then i see confirmation message on that action



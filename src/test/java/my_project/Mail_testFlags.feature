Feature:Test mark, unmark emails with flag
Scenario: Mark 3 emails with flag
When I select 3 emails
And i open Actions dropdown
And i select Mark with Flag action
Then i see that 3 emails are marked with flag

Scenario: Unmark 3 emails with flag
When I select 3 emails
And i open Actions dropdown
And i select Mark with Flag action
And i open Actions dropdown
And i select Unmark with flag action
Then I see that 3 emails are unmarked with flag
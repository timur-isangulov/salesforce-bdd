Feature: Verify search scenario
  Search for the Writing Tests page
  Verify Testing Apex page is loaded after clicking the link on the Writing Tests page

  @chrome
  Scenario: Verify Testing Apex link
    Given I am logged in
    When I search for the "Writing Tests"
    Then I should get "Writing Tests" link in result
    And retrieved links work
    Then I click on the "Writing Tests" link
    Then "Writing Tests" page opens
    Then I click on the "Testing Apex" link on the page
    Then "Testing Apex" page opens
    Then I logout


Feature: Check Currency Exchange Calculator page

  Scenario: Filling in "Buy" field should clear "Sell" amount field and vice versa
    Given Currency exchange calculator page is displayed
    Then user sees amount displayed as "100" in Sell input field
    And user sees empty Buy input field
    When user enters amount as "222" in Buy input field
    Then user sees amount displayed as "222" in Buy input field
    And user sees empty Sell input field
    When user enters amount as "333" in Sell input field
    Then user sees amount displayed as "333" in Sell input field
    And user sees empty Buy input field


  Scenario: Changing country should update rates and selected "Sell" currency
    Given Currency exchange calculator page is displayed
    Then user sees currency selected as "EUR" in Sell currency selector
    And user gets all initial rates values from table
    When user clicks on caret with country flag in footer
    And user selects country as "United Kingdom"
    And user waits until page is updated
    And user gets all final rates values from table
    Then user validate that final rates are differ from initial rates
    And user sees currency selected as "GBP" in Sell currency selector


  Scenario: If Bank exchange amount lower than Company exchange amount - text box representing the loss should appear
    Given Currency exchange calculator page is displayed
    Then user sees table column with Company exchange amounts
    And user sees 4 table columns with Banks exchange amounts
    And user compares exchange amounts between Company and 1 Bank and sees textbox with loss in each row where bank exchange amount lower than Company exchange amount
    And user compares exchange amounts between Company and 2 Bank and sees textbox with loss in each row where bank exchange amount lower than Company exchange amount
    And user compares exchange amounts between Company and 3 Bank and sees textbox with loss in each row where bank exchange amount lower than Company exchange amount
    And user compares exchange amounts between Company and 4 Bank and sees textbox with loss in each row where bank exchange amount lower than Company exchange amount


  Scenario: Check "Filter" and "Clear filter" buttons
    Given Currency exchange calculator page is displayed
    Then user sees currency selected as "EUR" in Sell currency selector
    And user sees currency selected as "All" in Buy currency selector
    And user sees 31 table row entries
    When user selects currency as "GBP" in Buy currency selector
    Then user sees currency selected as "GBP" in Buy currency selector
    When user clicks Filter button
    And user waits until page is updated
    Then user sees 1 filtered row entry
    And user sees currency as "GBP (British Pound)" displayed in filtered row entry
    When user clicks Clear filter button
    And user waits until page is updated
    Then user sees 31 table row entries


  Scenario: Filtering by invalid amount should trigger error message
    Given Currency exchange calculator page is displayed
    Then user sees currency selected as "EUR" in Sell currency selector
    When user enters amount as "QWE" in Sell input field
    And user clicks Filter button
    Then user sees error message "Invalid parameters" above Exchange fields
    When user clicks on Close error message cross button
    Then user does not see error message above Exchange fields
    When user clicks Clear filter button
    And user waits until page is updated
    Then user sees currency selected as "EUR" in Sell currency selector
    When user enters amount as "ASD" in Buy input field
    And user clicks Filter button
    Then user sees error message "Invalid parameters" above Exchange fields
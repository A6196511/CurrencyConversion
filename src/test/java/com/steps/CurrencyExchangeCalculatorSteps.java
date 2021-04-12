package com.steps;

import com.Page;
import com.pages.CurrencyExchangeCalculatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CurrencyExchangeCalculatorSteps {

    @Given("Currency exchange calculator page is displayed")
    public void CurrencyExchangeCalculatorPageIsDisplayed() {
        Page.on(CurrencyExchangeCalculatorPage.class).openPage();
    }

    @When("user enters amount as {string} in Sell input field")
    public void userEntersAmountInSellInputField(String amount) {
        Page.on(CurrencyExchangeCalculatorPage.class).setSellAmount(amount);
    }

    @Then("user sees amount displayed as {string} in Sell input field")
    public void userSeesAmountDisplayedInSellInputField(String amount) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isSellAmountDisplayed(amount));
    }

    @Then("user sees empty Sell input field")
    public void userSeesEmptySellInputField() {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isSellAmountFieldEmpty());
    }

    @When("user enters amount as {string} in Buy input field")
    public void userEntersAmountInBuyInputField(String amount) {
        Page.on(CurrencyExchangeCalculatorPage.class).setBuyAmount(amount);
    }

    @Then("user sees amount displayed as {string} in Buy input field")
    public void userSeeAmountDisplayedInBuyInputField(String amount) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isBuyAmountDisplayed(amount));
    }

    @Then("user sees empty Buy input field")
    public void userSeesEmptyBuyInputField() {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isBuyAmountFieldEmpty());
    }

    @When("user clicks on caret with country flag in footer")
    public void userClicksOnCaretWithCountryFlagInFooter() {
        Page.on(CurrencyExchangeCalculatorPage.class).clickCountryAndLanguageFooterCaret();
    }

    @And("user selects country as {string}")
    public void userSelectsCountryAs(String country) {
        Page.on(CurrencyExchangeCalculatorPage.class).selectCountry(country);
    }

    @And("user waits until page is updated")
    public void userWaitsUntilPageIsUpdated() {
        Page.on(CurrencyExchangeCalculatorPage.class).waitUntilPageIsUpdated();
    }

    @Then("user sees currency selected as {string} in Sell currency selector")
    public void userSeesCurrencySelectedInSellCurrencySelector(String currency) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isSellCurrencySelected(currency));
    }

    @Then("user sees currency selected as {string} in Buy currency selector")
    public void userSeesCurrencySelectedInBuyCurrencySelector(String currency) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isBuyCurrencySelected(currency));
    }

    @When("user selects currency as {string} in Sell currency selector")
    public void userSelectsCurrencyInSellCurrencySelector(String currency) {
        Page.on(CurrencyExchangeCalculatorPage.class).selectSellCurrency(currency);
    }

    @When("user selects currency as {string} in Buy currency selector")
    public void userSelectsCurrencyInBuyCurrencySelector(String currency) {
        Page.on(CurrencyExchangeCalculatorPage.class).selectBuyCurrency(currency);
    }

    @And("user gets all initial rates values from table")
    public void userGetsAllInitialRatesValuesFromTable() {
        Page.on(CurrencyExchangeCalculatorPage.class).getInitialRates();
    }

    @And("user gets all final rates values from table")
    public void userGetsAllFinakRatesValuesFromTable() {
        Page.on(CurrencyExchangeCalculatorPage.class).getFinalRates();
    }

    @Then("user validate that final rates are differ from initial rates")
    public void userValidateThatFinalRatesAreDifferFromInitialRates() {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).areRatesUpdated());
    }

    @Then("user sees table column with Company exchange amounts")
    public void userSeesTableColumnWithCompanyExchangeAmounts() {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isCompanyColumnHeadersDisplayed());
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).areCompanyAmountsDisplayed());
    }

    @And("user sees {int} table columns with Banks exchange amounts")
    public void userSeesTableColumnsWithBanksExchangeAmounts(int number) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).areBankColumnHeadersDisplayed(number));
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).areBankAmountsDisplayed(number));
    }

    @And("user compares exchange amounts between Company and {int} Bank and sees textbox with loss in each row where bank exchange amount lower than Company exchange amount")
    public void userComparesExchangeAmountsBetweenCompanyAndBankAndSeesTextboxWithLossInEachRowWhereBankExchangeAmountLowerThanCompanyExchangeAmount(int number) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(number));
    }

    @And("user sees {int} table row entries")
    public void userSeesTableRowEntries(int number) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isTableEntriesNumberCorrect(number));
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).areTableEntriesDisplayed());
    }

    @When("user clicks Filter button")
    public void userClicksFilterButton() {
        Page.on(CurrencyExchangeCalculatorPage.class).clickFilterButton();
    }

    @When("user clicks Clear filter button")
    public void userClicksClearFilterButton() {
        Page.on(CurrencyExchangeCalculatorPage.class).clickClearFilterButton();
    }

    @Then("user sees {int} filtered row entry")
    public void userSeesFilteredRowEntry(int number) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isTableEntriesNumberCorrect(number));
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).areTableEntriesDisplayed());
    }

    @And("user sees currency as {string} displayed in filtered row entry")
    public void userSeesCurrencyAsDisplayedInFilteredRowEntry(String currency) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isCurrencyDisplayedInFilteredEntry(currency));
    }

    @Then("user sees error message {string} above Exchange fields")
    public void userSeesErrorMessageAboveExchangeFields(String text) {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isErrorMessageDisplayed(text));
    }

    @When("user clicks on Close error message cross button")
    public void userClicksOnCloseErrorMessageCrossButton() {
        Page.on(CurrencyExchangeCalculatorPage.class).clickCloseErrorBlockButton();
    }

    @Then("user does not see error message above Exchange fields")
    public void userDoesNotSeeErrorMessageAboveExchangeFields() throws InterruptedException {
        Assert.assertTrue(Page.on(CurrencyExchangeCalculatorPage.class).isErrorMessageNotDisplayed());
    }
}


package com.pages;

import com.AbstractPage;
import com.DriverProvider;
import com.PropertyProvider;
import com.util.CheckTwoListsAreNotEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CurrencyExchangeCalculatorPage extends AbstractPage {

    @FindBy(how = How.XPATH, using = "//input[@data-ng-model='currencyExchangeVM.filter.from_amount']")
    WebElement sellInputField;

    @FindBy(how = How.XPATH, using = "//div[@data-ng-model='currencyExchangeVM.filter.from']")
    WebElement sellCurrencySelector;

    @FindBy(how = How.XPATH, using = "//div[@data-ng-model='currencyExchangeVM.filter.from']//input[@type='search']")
    WebElement sellCurrencySelectorInput;

    @FindBy(how = How.XPATH, using = "//input[@data-ng-model='currencyExchangeVM.filter.to_amount']")
    WebElement buyInputField;

    @FindBy(how = How.XPATH, using = "//div[@data-ng-model='currencyExchangeVM.filter.to']")
    WebElement buyCurrencySelector;

    @FindBy(how = How.XPATH, using = "//div[@data-ng-model='currencyExchangeVM.filter.to']//input[@type='search']")
    WebElement buyCurrencySelectorInput;

    @FindBy(how = How.XPATH, using = "//div[@data-ng-show='currencyExchangeVM.loading']")
    WebElement loadingCircle;

    @FindBy(how = How.XPATH, using = "//span[@class='caret']")
    WebElement countryAndLanguageFooterCaret;

    @FindBy(how = How.ID, using = "countries-dropdown")
    WebElement countryFooterSelector;

    @FindBy(how = How.ID, using = "languages-dropdown")
    WebElement languageFooterSelector;

    @FindBy(how = How.XPATH, using = "//span[contains(@data-ng-if, 'currencyExchangeVM.rates')]|//td[@data-ng-if='provider === currencyExchangeVM.PROVIDERS.COMMERCIAL']")
    List<WebElement> ratesTexts;

    @FindBy(how = How.XPATH, using = "//th[@data-ng-if='provider === currencyExchangeVM.PROVIDERS.COMMERCIAL']")
    WebElement companyColumnHeader;

    @FindBy(how = How.XPATH, using = "//span[@class='commercial-rate']")
    List<WebElement> companyExchangeAmountTexts;

    @FindBy(how = How.XPATH, using = "//th[@data-ng-repeat-start='provider in currencyExchangeVM.supportedProviders' and contains (text(), 'amount')]")
    List<WebElement> bankColumnHeaders;

    @FindBy(how = How.XPATH, using = "//td[5][contains(@data-title, 'amount')]//span[contains(@data-ng-if, 'currencyExchangeVM.rates')]")
    List<WebElement> firstBankExchangeAmountTexts;

    @FindBy(how = How.XPATH, using = "//td[6][contains(@data-title, 'amount')]//span[contains(@data-ng-if, 'currencyExchangeVM.rates')]")
    List<WebElement> secondBankExchangeAmountTexts;

    @FindBy(how = How.XPATH, using = "//td[7][contains(@data-title, 'amount')]//span[contains(@data-ng-if, 'currencyExchangeVM.rates')]")
    List<WebElement> thirdBankExchangeAmountTexts;

    @FindBy(how = How.XPATH, using = "//td[8][contains(@data-title, 'amount')]//span[contains(@data-ng-if, 'currencyExchangeVM.rates')]")
    List<WebElement> fourthBankExchangeAmountTexts;

    @FindBy(how = How.XPATH, using = "//tr[@data-ng-if='currencyExchangeVM.showRate(currency_to)']")
    List<WebElement> tableEntries;

    @FindBy(how = How.XPATH, using = "//td[@data-ng-if='currencyExchangeVM.rates[currencyExchangeVM.PROVIDERS.OFFICIAL]']")
    List<WebElement> currencyNameTexts;

    @FindBy(how = How.XPATH, using = "//button[@data-ng-click='currencyExchangeVM.filterExchangeRates()']")
    WebElement filterButton;

    @FindBy(how = How.XPATH, using = "//button[@data-ng-click='currencyExchangeVM.clearFilter()']")
    WebElement clearFilterButton;

    @FindBy(how = How.XPATH, using = "//td[@data-ng-if='currencyExchangeVM.rates[currencyExchangeVM.PROVIDERS.OFFICIAL]']")
    WebElement tableEntryCurrencyText;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement errorMessageBlock;

    @FindBy(how = How.XPATH, using = "//button[@ng-if='message.dismissButton']")
    WebElement closeErrorBlockButton;

    public static ArrayList<String> initialRates = new ArrayList<>();
    public static ArrayList<String> finalRates = new ArrayList<>();

    public CurrencyExchangeCalculatorPage openPage() {
        DriverProvider.webDriver().get(PropertyProvider.getProperty("TEST_URL"));
        waitForElementIsVisible(loadingCircle);
        waitForElementIsInvisible(By.xpath("//div[@data-ng-show='currencyExchangeVM.loading']"));
        return this;
    }

    public CurrencyExchangeCalculatorPage setSellAmount(String amount) {
        sendKeys(sellInputField, amount);
        return this;
    }

    public boolean isSellAmountDisplayed(String amount) {
        return getValue(sellInputField).equals(amount);
    }

    public boolean isSellAmountFieldEmpty() {
        return getValue(sellInputField).isEmpty();
    }

    public CurrencyExchangeCalculatorPage setBuyAmount(String amount) {
        sendKeys(buyInputField, amount);
        return this;
    }

    public boolean isBuyAmountDisplayed(String amount) {
        return getValue(buyInputField).equals(amount);
    }

    public boolean isBuyAmountFieldEmpty() {
        return getValue(buyInputField).isEmpty();
    }


    public CurrencyExchangeCalculatorPage clickCountryAndLanguageFooterCaret() {
        click(countryAndLanguageFooterCaret);
        return this;
    }


    public CurrencyExchangeCalculatorPage selectCountry(String country) {
        click(countryFooterSelector);
        click(findElementBy(By.xpath("//a[text()[contains(.,'" + country + "')]]")));
        return this;
    }

    public void waitUntilPageIsUpdated() {
        try {
            waitForElementIsVisible(loadingCircle, 5);
            waitForElementIsInvisible(By.xpath("//div[@data-ng-show='currencyExchangeVM.loading']"));
        } catch (Exception ignored) {
        }
    }

    public boolean isSellCurrencySelected(String currency) {
        return getText(sellCurrencySelector).equals(currency);
    }

    public CurrencyExchangeCalculatorPage selectSellCurrency(String currency) {
        click(sellCurrencySelector);
        sendKeys(sellCurrencySelectorInput, currency);
        click(findElementBy(By.xpath("//span[text()= '" + currency + "']")));
        return this;
    }

    public boolean isBuyCurrencySelected(String currency) {
        return getText(buyCurrencySelector).equals(currency);
    }

    public CurrencyExchangeCalculatorPage selectBuyCurrency(String currency) {
        click(buyCurrencySelector);
        sendKeys(buyCurrencySelectorInput, currency);
        click(findElementBy(By.xpath("//span[text()= '" + currency + "']")));
        return this;
    }

    public CurrencyExchangeCalculatorPage getInitialRates() {
        for (WebElement rateText : ratesTexts) {
            initialRates.add(getText(rateText));
        }
        return this;
    }

    public CurrencyExchangeCalculatorPage getFinalRates() {
        for (WebElement rateText : ratesTexts) {
            finalRates.add(getText(rateText));
        }
        return this;
    }

    public boolean areRatesUpdated() {
        if (initialRates.size() > 0 && finalRates.size() > 0) {
            return CheckTwoListsAreNotEqual.checkTwoListsAreNotEqual(initialRates, finalRates);
        } else {
            throw new IllegalArgumentException("Error: empty rates list.");
        }
    }

    public boolean isCompanyColumnHeadersDisplayed() {
        return companyColumnHeader.isDisplayed();
    }

    public boolean areCompanyAmountsDisplayed() {
        boolean result = false;
        for (int i = 0; i < tableEntries.size(); i++) {
            if (!getText(companyExchangeAmountTexts.get(i)).equals("-") || !getText(companyExchangeAmountTexts.get(i)).isEmpty()) {
                result = true;
            } else {
                break;
            }
        }
        return result;
    }

    public boolean areBankColumnHeadersDisplayed(int number) {
        return bankColumnHeaders.size() == number;
    }

    public boolean areBankAmountsDisplayed(int number) {
        boolean result = false;
        switch (number) {
            case (4):
                for (int i = 0; i < tableEntries.size(); i++) {
                    if (!getText(firstBankExchangeAmountTexts.get(i)).isEmpty() || !getText(secondBankExchangeAmountTexts.get(i)).isEmpty() || !getText(thirdBankExchangeAmountTexts.get(i)).isEmpty() || !getText(fourthBankExchangeAmountTexts.get(i)).isEmpty()) {
                        result = true;
                    } else {
                        break;
                    }
                }
                break;
        }
        return result;
    }

    public boolean isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(List<WebElement> amountTexts, int number) {
        boolean result = false;
        for (int i = 0; i < tableEntries.size(); i++) {
//            if there are 2 text rows in table table sell - amount and loss value
            if (!getText(amountTexts.get(i)).equals("-") && getText(amountTexts.get(i)).contains("\n")) {
                int iend = getText(amountTexts.get(i)).replace(",", "").indexOf("\n");
                float companyAmount = Float.parseFloat(getText(companyExchangeAmountTexts.get(i)).replace(",", ""));
                float bankAmount = Float.parseFloat(getText(amountTexts.get(i)).replace(",", "").substring(0, iend));
                if (companyAmount > bankAmount) {
                    String lossAmount = (String.format(Locale.ENGLISH, "%.2f", bankAmount - companyAmount));
//            check loss value
                    if (getText(amountTexts.get(i)).substring(iend + 1).trim().equals("(" + lossAmount + ")")) {
                        result = true;
                    } else {
                        System.out.println("Missing loss value for " + getText(bankColumnHeaders.get(number - 1)) + " for " + getText(currencyNameTexts.get(i)) + " currency");
                        result = false;
                        break;
                    }
                }
//            if there is 1 text row in table sell - only amount without loss value
            } else if (!getText(amountTexts.get(i)).equals("-") && !getText(amountTexts.get(i)).contains("\n")) {
                float companyAmount = Float.parseFloat(getText(companyExchangeAmountTexts.get(i)).replace(",", ""));
                float bankAmount = Float.parseFloat(getText(amountTexts.get(i)).replace(",", ""));
//            check if loss value should present
                if (companyAmount > bankAmount) {
                    System.out.println("Missing loss value for " + getText(bankColumnHeaders.get(number - 1)) + " for " + getText(currencyNameTexts.get(i)) + " currency");
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(int number) {
        boolean result;
        switch (number) {
            case (1):
                result = isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(firstBankExchangeAmountTexts, number);
                break;
            case (2):
                result = isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(secondBankExchangeAmountTexts, number);
                break;
            case (3):
                result = isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(thirdBankExchangeAmountTexts, number);
                break;
            case (4):
                result = isTextboxWithLossDisplayedIfBankAmountLowerThanCompanyAmount(fourthBankExchangeAmountTexts, number);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
        return result;
    }

    public boolean isTableEntriesNumberCorrect(int number) {
        return tableEntries.size() == number;
    }

    public boolean areTableEntriesDisplayed() {
        boolean result = false;
        for (WebElement tableEntry : tableEntries) {
            result = tableEntry.isDisplayed();
        }
        return result;
    }


    public CurrencyExchangeCalculatorPage clickFilterButton() {
        click(filterButton);
        return this;
    }

    public CurrencyExchangeCalculatorPage clickClearFilterButton() {
        click(clearFilterButton);
        return this;
    }

    public boolean isCurrencyDisplayedInFilteredEntry(String currency) {
        return getText(tableEntryCurrencyText).trim().equals(currency);
    }

    public boolean isErrorMessageDisplayed(String text) {
        return getText(errorMessageBlock).equals("×\n"+text);
    }

    public CurrencyExchangeCalculatorPage clickCloseErrorBlockButton() {
        click(closeErrorBlockButton);
        return this;
    }

    public boolean isErrorMessageNotDisplayed() throws InterruptedException {
        return isElementNotPresent(findElementByTillNotNull(By.xpath("//div[@class='alert alert-danger alert-dismissible']"),1));
    }
}
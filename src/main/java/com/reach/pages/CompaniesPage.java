package com.reach.pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompaniesPage {

	WebDriver ldriver;
    WebDriverWait wait;
 


	public CompaniesPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
        wait = new WebDriverWait(ldriver, 15, 50);

	}

	@FindBy(xpath = "//a[contains(@href,'/emc/browse-companies/')]")
	List<WebElement> link_alphabets;

	@FindBy(xpath = "//a[contains(@href,'/emc/company/')]")
	List<WebElement> list_totalCompanies;

	@FindBy(xpath = "//main[@class='main-home']//div[1]//ul//li")
	List<WebElement> list_leftCompanies;

	@FindBy(xpath = "//main[@class='main-home']//div[2]//ul//li")
	List<WebElement> list_rightcompanies;

	@FindBy(xpath = "//h1")
	WebElement txt_companyName;

	@FindBy(xpath = "//div[@class='col-md-5']")
	WebElement txt_companyDetailsLeft;

	@FindBy(xpath = "//div[@class='col-md-4']")
	WebElement txt_companyDetailsRight;

	@FindBy(xpath = "//img[@alt='Company image']")
	WebElement img_logo;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-menu-up']")
	WebElement btn_backToTop;
	
	
	@FindBy(id="onetrust-accept-btn-handler")
	WebElement btn_acceptCookies;

	public void clickOnAlphabetsByInput(int num) {
		String generatedXpath = "//div[@class='col-md-12 browse-head']//ul/li[" + num + "]//a";
		WebElement link_alphabet = ldriver.findElement(By.xpath(generatedXpath));
		Actions action=new Actions(ldriver);
		action.sendKeys(Keys.PAGE_UP).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(link_alphabet));
		link_alphabet.click();
	}

	public void clickOnCompanyNameByPosition(int num) {
		String generatedXpath;
		if (num % 2 == 0) {
			num = num / 2;
			generatedXpath = "//div[@class='col-md-6 ingredients ieright']//ul//li[" + num + "]//a";
		} else {
			num = (num / 2) + 1;
			generatedXpath = "//div[@class='col-md-6 ingredients ieleft']//ul//li[" + num + "]//a";
		}
		WebElement link_company = ldriver.findElement(By.xpath(generatedXpath));
		link_company.click();
	}

	public String getCompanyName() {
		return txt_companyName.getText();
	}

	public String getCompanyContactDetailsRight() {
		return txt_companyDetailsRight.getText();
	}

	public String getCompanyContactDetailsLeft() {
		return txt_companyDetailsLeft.getText();
	}

	public String saveCompanyLogo(String companyName) throws IOException {
		String logoSRC = img_logo.getAttribute("src");
		URL imageURL = new URL(logoSRC);
		BufferedImage saveImage = ImageIO.read(imageURL);
		String imgName = companyName + ".png";
		ImageIO.write(saveImage, "png", new File(System.getProperty("user.dir") + "/Logos/" + imgName));
		return imgName;
	}

	public int getNumberOfCompanies() {
		return list_totalCompanies.size();
	}

	public void navigateToPreviousPage() {
		ldriver.navigate().back();
	}

	public void clickOnAcceptCookiesButton() {
		if (btn_acceptCookies.isDisplayed()) {
			btn_acceptCookies.click();
		}
	}
}

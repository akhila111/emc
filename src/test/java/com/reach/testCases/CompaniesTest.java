package com.reach.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.testng.annotations.Test;

import com.reach.pages.CompaniesPage;
import com.reach.utilities.XMLUtils;

public class CompaniesTest extends BaseClass {
	String companyName, imageName, contactInfo1, contactInfo2;
	ArrayList<String> companies = new ArrayList<String>();
	ArrayList<String> logos = new ArrayList<String>();
	ArrayList<String> companyInfo1 = new ArrayList<String>();
	ArrayList<String> companyInfo2 = new ArrayList<String>();

	@Test
	public void getCompaniesDetails() throws IOException, ParserConfigurationException, TransformerException {
		CompaniesPage cp = new CompaniesPage(driver);
		cp.clickOnAcceptCookiesButton();

		// Looping through all the alphabets and clicking
		for (int i = 2; i <= 27; i++) {
			cp.clickOnAlphabetsByInput(i);
			
			// Getting the total number of companies listed
			int TotalCompanies = cp.getNumberOfCompanies();

			// Creating an array of user inputs to view details of particular companies
			ArrayList<Integer> positions = new ArrayList<Integer>(Arrays.asList(1,3,TotalCompanies));
			for (int j = 0; j < positions.size(); j++) {
				
				// Clicking on company names based on the position
				cp.clickOnCompanyNameByPosition(positions.get(j));

				// Getting the company name
				companyName = cp.getCompanyName();
				companies.add(companyName);

				// Saving the company logo in Logos folder and getting the name of image
				imageName = cp.saveCompanyLogo(companyName);
				logos.add(imageName);

				// Getting the contact details of the company
				contactInfo1 = cp.getCompanyContactDetailsLeft();
				companyInfo1.add(contactInfo1);
				contactInfo2 = cp.getCompanyContactDetailsRight();
				companyInfo2.add(contactInfo2);

				// Navigating to the previous page
				cp.navigateToPreviousPage();

			}

		}

		// Writing the company name, logo image name and contact information into a XML
		// file				
		XMLUtils.writeIntoXMLFile(companies, logos, companyInfo1, companyInfo2);

	}
}

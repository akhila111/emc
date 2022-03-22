package com.reach.utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;

public class XMLUtils {

	public static void writeIntoXMLFile(ArrayList<String> companyName, ArrayList<String> companylogo,
			ArrayList<String> contactInfo1, ArrayList<String> contactInfo2)
			throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// Create Root Element Companies
		Element rootElement = doc.createElement("companies");
		doc.appendChild(rootElement);

		for (String a : companyName) {
			for (String b : companylogo) {
				for (String c : contactInfo1) {

					for (String d : contactInfo2) {
						// Creating Company element
						Element company = doc.createElement("company");
						rootElement.appendChild(company);

						// Creating company name element
						Element name = doc.createElement("name");
						name.setTextContent(a);
						company.appendChild(name);

						// Creating logo element
						Element logo = doc.createElement("logo");
						logo.setTextContent(b);
						company.appendChild(logo);

						// Creating contact info element
						Element contactDetailsRight = doc.createElement("contactInfoRight");
						contactDetailsRight.setTextContent(c);
						company.appendChild(contactDetailsRight);

						Element contactDetailsLeft = doc.createElement("contactInfoLeft");
						contactDetailsLeft.setTextContent(d);
						company.appendChild(contactDetailsLeft);

						// Creating an XML file and writing data into that
						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty(OutputKeys.METHOD, "xml");
						transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
						DOMSource domSource = new DOMSource(doc);
						StreamResult streamResult = new StreamResult(
								new File(System.getProperty("user.dir") + "/XMLFiles/" + "StoredCompanyDetails.xml"));
						transformer.transform(domSource, streamResult);
					}
				}
			}
		}
	}

}

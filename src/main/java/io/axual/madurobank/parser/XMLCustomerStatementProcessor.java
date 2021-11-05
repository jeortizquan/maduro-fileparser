package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecord;
import io.axual.madurobank.entities.CustomerStatementRecordBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XMLCustomerStatementProcessor extends CustomerStatementProcessor {

    @Override
    public List<CustomerStatementRecord> readFile() {
        List<CustomerStatementRecord> customerStatementRecords = new ArrayList<>();
        CustomerStatementRecord newCustomerStatementRecord = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(this.getFileName()));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("record");

            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    try {
                        newCustomerStatementRecord = CustomerStatementRecordBuilder
                                .aCustomerStatementRecord()
                                .withTransactionReference(Long.parseLong(element.getAttribute("reference")))
                                .withAccounNumber(element.getElementsByTagName("accountNumber").item(0).getTextContent())
                                .withStartBalance(new BigDecimal(element.getElementsByTagName("startBalance").item(0).getTextContent()))
                                .withMutation(new BigDecimal(element.getElementsByTagName("mutation").item(0).getTextContent()))
                                .withDescription(element.getElementsByTagName("description").item(0).getTextContent())
                                .withEndBalance(new BigDecimal(element.getElementsByTagName("endBalance").item(0).getTextContent()))
                                .build();
                        customerStatementRecords.add(newCustomerStatementRecord);
                    } catch(Exception ex) {
                        customerStatementRecords.add(null);
                        logger.error("failed parse node nr. {} {}", i, element.getTagName());
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("parsing error {}",e);
        }

        return customerStatementRecords;
    }
}

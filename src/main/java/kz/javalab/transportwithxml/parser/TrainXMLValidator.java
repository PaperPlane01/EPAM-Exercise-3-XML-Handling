package kz.javalab.transportwithxml.parser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by PaperPlane on 24.07.2017.
 */
public class TrainXMLValidator {

    public static boolean isValidating(String xmlFileLocation, String schemaLocation) {
        DocumentBuilder parser = null;
        boolean result = true;
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            result = false;
        }

        Document document = null;
        try {
            document = parser.parse(new File(xmlFileLocation));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            result = false;
        }

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Source schemaFile = new StreamSource(new File(schemaLocation));
        Schema schema = null;
        try {
            schema = factory.newSchema(schemaFile);
        } catch (SAXException e) {
            e.printStackTrace();
            result = false;
        }

        Validator validator = schema.newValidator();

        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}

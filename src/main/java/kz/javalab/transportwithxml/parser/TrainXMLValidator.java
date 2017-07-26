package kz.javalab.transportwithxml.parser;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by PaperPlane on 24.07.2017.
 * This class is designated for validating XML file against XSD schema,
 */
public class TrainXMLValidator {

    /**
     * Validates XML file against XSD shema.
     * @param xmlFileLocation Location of the XML file.
     * @param schemaLocation Location of the XSD schema.
     * @return <Code>True</Code> if file has been successfully validated, <Code>False</Code> if not.
     */
    public static boolean isValidating(String xmlFileLocation, String schemaLocation) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(schemaLocation));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFileLocation)));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

package kz.javalab.transportwithxml.runner;


import kz.javalab.transportwithxml.parser.TrainDOMParser;
import kz.javalab.transportwithxml.parser.TrainSAXHandler;
import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.trainmanager.TrainManager;
import kz.javalab.transportwithxml.parser.TrainStAXParser;
import kz.javalab.transportwithxml.parser.TrainXMLValidator;
import org.xml.sax.SAXException;
import kz.javalab.transportwithxml.views.TrainManagerView;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class App {

    private static String FILE_PATH = "train.xml";
    private static String SCHEMA_PATH = "trainSchema.xsd";

    public static void main(String[] args) {

        System.out.println(TrainXMLValidator.isValidating(FILE_PATH, SCHEMA_PATH));

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        TrainSAXHandler trainHandler = new TrainSAXHandler();

        try {
            parser.parse(FILE_PATH, trainHandler);

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }


        Train trainParsedViaSAXMethod = trainHandler.getParsedTrain();

        TrainManager trainManager = new TrainManager(trainParsedViaSAXMethod);
        TrainManagerView trainManagerView = new TrainManagerView(trainManager);

        TrainStAXParser trainStAXParser = new TrainStAXParser();

        Train trainParsedViaStAXMethod = trainStAXParser.parseTrainInstanceFromXMLFile(FILE_PATH);;

        TrainDOMParser trainDOMParser = new TrainDOMParser();
        Train trainParsedViaDOMMethod = trainDOMParser.parseTrain(FILE_PATH);

        trainManagerView.getModel().setTrain(trainParsedViaSAXMethod);
        trainManagerView.show();
        System.out.println();
        trainManagerView.getModel().setTrain(trainParsedViaStAXMethod);
        trainManagerView.show();
        System.out.println();
        trainManagerView.getModel().setTrain(trainParsedViaDOMMethod);
        trainManagerView.show();
        System.out.println();

        System.out.println(trainParsedViaSAXMethod.equals(trainParsedViaStAXMethod));
        System.out.println(trainParsedViaSAXMethod.equals(trainParsedViaDOMMethod));
        System.out.println(trainParsedViaStAXMethod.equals(trainParsedViaDOMMethod));

    }
}

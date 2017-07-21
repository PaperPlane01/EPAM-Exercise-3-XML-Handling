package kz.javalab.transportwithxml.runner;


import kz.javalab.transportwithxml.parser.TrainSAXHandler;
import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.trainmanager.TrainManager;
import kz.javalab.transportwithxml.parser.TrainStAXParser;
import org.xml.sax.SAXException;
import kz.javalab.transportwithxml.views.TrainManagerView;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class App {

    private static String FILE_PATH = "D:\\MyProjects\\TransportXML\\src\\main\\resources\\xml\\train.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        TrainSAXHandler trainHandler = new TrainSAXHandler();

        parser.parse(FILE_PATH, trainHandler);

        Train trainParsedViaSAXMethod = trainHandler.getParsedTrain();

        TrainManager trainManager = new TrainManager(trainParsedViaSAXMethod);
        TrainManagerView trainManagerView = new TrainManagerView(trainManager);

        trainManagerView.show();

        TrainStAXParser trainStAXParser = new TrainStAXParser();

        Train trainParsedViaStAXMethod = trainStAXParser.parseTrainInstanceFromXMLFile(FILE_PATH);

        trainManagerView.getModel().setTrain(trainParsedViaSAXMethod);
        trainManagerView.show();

        System.out.println(trainParsedViaSAXMethod.equals(trainParsedViaStAXMethod));

    }
}

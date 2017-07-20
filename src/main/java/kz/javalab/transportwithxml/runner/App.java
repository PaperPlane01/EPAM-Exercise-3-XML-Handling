package kz.javalab.transportwithxml.runner;


import kz.javalab.transportwithxml.parser.TrainHandler;
import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.trainmanager.TrainManager;
import org.xml.sax.SAXException;
import kz.javalab.transportwithxml.views.TrainManagerView;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        TrainHandler trainHandler = new TrainHandler();

        parser.parse("D:\\MyProjects\\TransportXML\\src\\main\\resources\\xml\\train.xml", trainHandler);

        Train train = trainHandler.getParsedTrain();

        TrainManager trainManager = new TrainManager(train);
        TrainManagerView trainManagerView = new TrainManagerView(trainManager);

        trainManagerView.show();
        trainManagerView.showListOfPassengerCarsSortedByComfortLevel(false);
        trainManagerView.showListOfPassengerCarsWithPassengersCapacityWithinRange(20, 30);
    }
}

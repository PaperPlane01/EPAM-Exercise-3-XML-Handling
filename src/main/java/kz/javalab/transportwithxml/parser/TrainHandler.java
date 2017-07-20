package kz.javalab.transportwithxml.parser;

import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.ComfortLevel;
import kz.javalab.transportwithxml.entity.traincar.impl.ControlCar;
import kz.javalab.transportwithxml.entity.traincar.impl.FreightCar;
import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by PaperPlane on 20.07.2017.
 */
public class TrainHandler extends DefaultHandler {

    private Train train = null;
    private boolean trainFound;
    private boolean trainIDFound;
    private boolean trainCarsFound;
    private boolean controlCarFound;
    private boolean passengerCarFound;
    private boolean freightCarFound;
    private boolean carNumberFound;
    private boolean passengersCapacityFound;
    private boolean comfortLevelFound;
    private boolean weightCapacityFound;
    private TrainCar cacheTrainCar;

    public TrainHandler() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("train")) {
            trainFound = true;
        }

        if (qName.equals("trainID")) {
            trainIDFound = true;
        }

        if (qName.equals("trainCars")) {
            trainCarsFound = true;
        }

        if (qName.equals("controlCar")) {
            controlCarFound = true;
        }

        if (qName.equals("passengerCar")) {
            passengerCarFound = true;
        }

        if (qName.equals("freightCar")) {
            freightCarFound = true;
        }

        if (qName.equals("weightCapacity")) {
            weightCapacityFound = true;
        }

        if (qName.equals("passengersCapacity")) {
            passengersCapacityFound = true;
        }

        if (qName.equals("comfortLevel")) {
            comfortLevelFound = true;
        }

        if (qName.equals("carNumber")) {
            carNumberFound = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ((qName.equals("passengerCar"))
                || (qName.equals("controlCar"))
                || (qName.equals("freightCar"))
                || (qName.equals("trainCar"))) {
            train.addTrainCar(cacheTrainCar);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (trainFound) {
            train = new Train();
            trainFound = false;
        }

        if (trainIDFound) {
            long trainID = Long.valueOf(new String(ch, start, length));
            train.setTrainID(trainID);
            trainIDFound = false;
        }

        if (trainCarsFound) {
            trainCarsFound = false;
        }

        if (controlCarFound) {
            cacheTrainCar = new ControlCar();
            controlCarFound = false;
        }

        if (passengerCarFound) {
            cacheTrainCar = new PassengerCar();
            passengerCarFound = false;
        }

        if (freightCarFound) {
            cacheTrainCar = new FreightCar();
            freightCarFound = false;
        }

        if (weightCapacityFound) {
            int weightCapacity = Integer.valueOf(new String(ch, start, length));
            cacheTrainCar.setWeightCapacity(weightCapacity);
            weightCapacityFound = false;
        }

        if (passengersCapacityFound) {
            int passengersCapacity = Integer.valueOf(new String(ch, start, length));
            ((PassengerCar) cacheTrainCar).setPassengersCapacity(passengersCapacity);
            passengersCapacityFound = false;
        }

        if (comfortLevelFound) {
            String comfortLevel = new String(ch, start, length);

            if ("LOW".equals(comfortLevel)) {
                ((PassengerCar) cacheTrainCar).setComfortLevel(ComfortLevel.LOW);
            }

            if ("MIDDLE".equals(comfortLevel)) {
                ((PassengerCar) cacheTrainCar).setComfortLevel(ComfortLevel.MIDDLE);
            }

            if ("HIGH".equals(comfortLevel)) {
                ((PassengerCar) cacheTrainCar).setComfortLevel(ComfortLevel.HIGH);
            }

            comfortLevelFound = false;
        }

        if (carNumberFound) {
            int carNumber = Integer.valueOf(new String(ch, start, length));
            cacheTrainCar.setCarNumber(carNumber);
            carNumberFound = false;
        }
    }



    public Train getParsedTrain() {
        return train;
    }
}

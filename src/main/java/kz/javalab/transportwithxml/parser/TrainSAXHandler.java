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
public class TrainSAXHandler extends DefaultHandler {

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

    public TrainSAXHandler() {
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
        switch (qName) {
            case TrainXMLConstants.TagNames.TRAIN:
                trainFound = true;
                break;
            case TrainXMLConstants.TagNames.TRAIN_ID:
                trainIDFound = true;
                break;
            case TrainXMLConstants.TagNames.TRAIN_CARS:
                trainCarsFound = true;
                break;
            case TrainXMLConstants.TagNames.CONTROL_CAR:
                controlCarFound = true;
                break;
            case TrainXMLConstants.TagNames.FREIGHT_CAR:
                freightCarFound = true;
                break;
            case TrainXMLConstants.TagNames.PASSENGER_CAR:
                passengerCarFound = true;
                break;
            case TrainXMLConstants.TagNames.CAR_NUMBER:
                carNumberFound = true;
                break;
            case TrainXMLConstants.TagNames.WEIGHT_CAPACITY:
                weightCapacityFound = true;
                break;
            case TrainXMLConstants.TagNames.PASSENGERS_CAPACITY:
                passengersCapacityFound = true;
                break;
            case TrainXMLConstants.TagNames.COMFORT_LEVEL:
                comfortLevelFound = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ((qName.equals(TrainXMLConstants.TagNames.PASSENGER_CAR))
                || (qName.equals(TrainXMLConstants.TagNames.CONTROL_CAR))
                || (qName.equals(TrainXMLConstants.TagNames.FREIGHT_CAR))) {
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

            switch (comfortLevel) {
                case TrainXMLConstants.ComfortLevels.LOW:
                    ((PassengerCar) cacheTrainCar).setComfortLevel(ComfortLevel.LOW);
                    break;
                case TrainXMLConstants.ComfortLevels.MIDDLE:
                    ((PassengerCar) cacheTrainCar).setComfortLevel(ComfortLevel.MIDDLE);
                    break;
                case TrainXMLConstants.ComfortLevels.HIGH:
                    ((PassengerCar) cacheTrainCar).setComfortLevel(ComfortLevel.HIGH);
                    break;
                default:
                    break;
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

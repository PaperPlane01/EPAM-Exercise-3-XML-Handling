package kz.javalab.transportwithxml.parser;

import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.ComfortLevel;
import kz.javalab.transportwithxml.entity.traincar.impl.ControlCar;
import kz.javalab.transportwithxml.entity.traincar.impl.FreightCar;
import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by PaperPlane on 21.07.2017.
 */
public class TrainStAXParser {
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

    public TrainStAXParser() {
    }

    public Train parseTrainInstanceFromXMLFile(String pathToFile) {

        XMLInputFactory inputFactory = XMLInputFactory.newFactory();

        try {
            XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(new FileInputStream(pathToFile));

            while (xmlEventReader.hasNext()) {
                XMLEvent event = xmlEventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        parseStartElement(startElement);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        parseCharacters(characters);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        parseEndElement(endElement);
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return train;
    }

    private void parseStartElement(StartElement startElement) {
        String qName = startElement.getName().getLocalPart();

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

    private void parseCharacters(Characters characters) {
        if (trainFound) {
            train = new Train();
            trainFound = false;
        }

        if (trainIDFound) {
            long trainID = Long.valueOf(characters.getData());
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
            int weightCapacity = Integer.valueOf(characters.getData());
            cacheTrainCar.setWeightCapacity(weightCapacity);
            weightCapacityFound = false;
        }

        if (passengersCapacityFound) {
            int passengersCapacity = Integer.valueOf(characters.getData());
            ((PassengerCar) cacheTrainCar).setPassengersCapacity(passengersCapacity);
            passengersCapacityFound = false;
        }

        if (comfortLevelFound) {
            String comfortLevel = characters.getData();

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
            int carNumber = Integer.valueOf(characters.getData());
            cacheTrainCar.setCarNumber(carNumber);
            carNumberFound = false;
        }
    }

    private void parseEndElement(EndElement endElement) {
        String qName = endElement.getName().getLocalPart();

        if ((qName.equals(TrainXMLConstants.TagNames.PASSENGER_CAR))
                || (qName.equals(TrainXMLConstants.TagNames.CONTROL_CAR))
                || (qName.equals(TrainXMLConstants.TagNames.FREIGHT_CAR))) {
            train.addTrainCar(cacheTrainCar);
        }

    }
}

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
            int carNumber = Integer.valueOf(characters.getData());
            cacheTrainCar.setCarNumber(carNumber);
            carNumberFound = false;
        }
    }

    private void parseEndElement(EndElement endElement) {
        String qName = endElement.getName().getLocalPart();

        if ((qName.equals("passengerCar"))
                || (qName.equals("controlCar"))
                || (qName.equals("freightCar"))
                || (qName.equals("trainCar"))) {
            train.addTrainCar(cacheTrainCar);
        }

    }
}

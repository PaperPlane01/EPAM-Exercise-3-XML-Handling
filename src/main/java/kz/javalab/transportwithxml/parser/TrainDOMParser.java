package kz.javalab.transportwithxml.parser;

import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.ComfortLevel;
import kz.javalab.transportwithxml.entity.traincar.impl.ControlCar;
import kz.javalab.transportwithxml.entity.traincar.impl.FreightCar;
import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by PaperPlane on 21.07.2017.
 * This class is designated for parsing <Code>Train</Code> instance from XML file via DOM method.
 */
public class TrainDOMParser {

    /**
     * The result of parsing.
     */
    private Train train = null;

    /**
     * Parses <Code>Train</Code> instance from XML file via DOM method.
     * @param filePath Path of the XML file which is to be parsed.
     * @return Parsed train.
     */
    public Train parseTrain(String filePath) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            train = new Train();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filePath);
            document.normalize();

            Element rootTrain = document.getDocumentElement();

            int trainIDIndex = 1;
            int trainCarsIndex = 3;
            NodeList trainProperties = rootTrain.getChildNodes();


            Node trainIDNode = trainProperties.item(trainIDIndex);
            Long trainID = Long.valueOf(trainIDNode.getTextContent());
            train.setTrainID(trainID);

            Node trainCarsNode = trainProperties.item(trainCarsIndex);
            NodeList listOfTrainCars = trainCarsNode.getChildNodes();

            for (int index = 0; index < listOfTrainCars.getLength(); index++) {
                switch (listOfTrainCars.item(index).getNodeName()) {
                    case TrainXMLConstants.TagNames.CONTROL_CAR:
                        ControlCar controlCar = parseControlCar(listOfTrainCars.item(index));
                        train.addTrainCar(controlCar);
                        break;
                    case TrainXMLConstants.TagNames.PASSENGER_CAR:
                        PassengerCar passengerCar = parsePassengerCar(listOfTrainCars.item(index));
                        train.addTrainCar(passengerCar);
                        break;
                    case TrainXMLConstants.TagNames.FREIGHT_CAR:
                        FreightCar freightCar = parseFreightCar(listOfTrainCars.item(index));
                        train.addTrainCar(freightCar);
                        break;
                    default:
                        break;
                    }
                }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return train;
    }

    /**
     * Parses control car from the node.
     * @param controlCarNode Node of XML document containing data.
     * @return Node of XML document containing data.
     */
    private ControlCar parseControlCar(Node controlCarNode) {
        int trainCarType = 1;
        return  (ControlCar) parseTrainCar(trainCarType, controlCarNode);
    }

    /**
     * Parses freight car from the node.
     * @param freightCarNode
     * @return
     */
    private FreightCar parseFreightCar(Node freightCarNode) {
       int trainCarType = 2;
       return  (FreightCar) parseTrainCar(trainCarType, freightCarNode);
    }

    /**
     * Parses control car or freight car from the node.
     * @param trainCarType Indicates which type of train car should be parsed. 1 — control car, 2 — freight car.
     * @param trainCarNode Node of XML document containing data.
     * @return Parsed train car.
     */
    private TrainCar parseTrainCar(int trainCarType, Node trainCarNode) {
        TrainCar parsedTrainCar = null;

        switch (trainCarType) {
            case 1:
                parsedTrainCar = new ControlCar();
                break;
            case 2:
                parsedTrainCar = new FreightCar();
                break;
            default:
                break;
        }

        NodeList trainCarProperties = trainCarNode.getChildNodes();

        for (int index = 0; index < trainCarProperties.getLength(); index++) {
            if (trainCarProperties.item(index).getNodeType() == Node.ELEMENT_NODE) {
                switch (((Element) trainCarProperties.item(index)).getTagName()) {
                    case TrainXMLConstants.TagNames.CAR_NUMBER:
                        int carNumber = Integer.valueOf(trainCarProperties.item(index).getTextContent());
                        parsedTrainCar.setCarNumber(carNumber);
                        break;
                    case TrainXMLConstants.TagNames.WEIGHT_CAPACITY:
                        int weightCapacity = Integer.valueOf(trainCarProperties.item(index).getTextContent());
                        parsedTrainCar.setWeightCapacity(weightCapacity);
                        break;
                    default:
                        break;
                }
            }
        }

        return parsedTrainCar;
    }


    /**
     * Parses passenger car or freight car from the node.
     * @param passengerCarNode Node of XML document containing data.
     * @return Parsed passenger car.
     */
    private PassengerCar parsePassengerCar (Node passengerCarNode) {
        PassengerCar parsedPassengerCar = new PassengerCar();

        NodeList passengerCarProperties = passengerCarNode.getChildNodes();

        for (int index = 0; index < passengerCarProperties.getLength(); index++) {
            if (passengerCarProperties.item(index).getNodeType() == Node.ELEMENT_NODE) {
                switch (((Element) passengerCarProperties.item(index)).getTagName()) {
                    case TrainXMLConstants.TagNames.CAR_NUMBER:
                        int carNumber = Integer.valueOf(passengerCarProperties.item(index).getTextContent());
                        parsedPassengerCar.setCarNumber(carNumber);
                        break;
                    case TrainXMLConstants.TagNames.WEIGHT_CAPACITY:
                        int weightCapacity = Integer.valueOf(passengerCarProperties.item(index).getTextContent());
                        parsedPassengerCar.setWeightCapacity(weightCapacity);
                        break;
                    case TrainXMLConstants.TagNames.PASSENGERS_CAPACITY:
                        int passengersCapacity = Integer.valueOf(passengerCarProperties.item(index).getTextContent());
                        parsedPassengerCar.setPassengersCapacity(passengersCapacity);
                        break;
                    case TrainXMLConstants.TagNames.COMFORT_LEVEL:
                        String comfortLevel = passengerCarProperties.item(index).getTextContent();
                        switch (comfortLevel) {
                            case TrainXMLConstants.ComfortLevels.LOW:
                                parsedPassengerCar.setComfortLevel(ComfortLevel.LOW);
                                break;
                            case TrainXMLConstants.ComfortLevels.MIDDLE:
                                parsedPassengerCar.setComfortLevel(ComfortLevel.MIDDLE);
                                break;
                            case TrainXMLConstants.ComfortLevels.HIGH:
                                parsedPassengerCar.setComfortLevel(ComfortLevel.HIGH);
                                break;
                            default:
                                break;
                        }
                    default:
                        break;
                }

            }
        }


        return parsedPassengerCar;
    }
}

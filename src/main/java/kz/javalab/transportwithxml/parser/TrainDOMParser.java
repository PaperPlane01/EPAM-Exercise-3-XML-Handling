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
 */
public class TrainDOMParser {

    private Train train = null;

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
            System.out.println(trainProperties.item(trainIDIndex).getNodeName());
            Long trainID = Long.valueOf(trainIDNode.getTextContent());
            train.setTrainID(trainID);

            Node trainCarsNode = trainProperties.item(trainCarsIndex);
            System.out.println(trainCarsNode.getNodeName());
            NodeList listOfTrainCars = trainCarsNode.getChildNodes();

            for (int index = 0; index < listOfTrainCars.getLength(); index++) {
                if (listOfTrainCars.item(index).getNodeName().equals("controlCar")) {
                    ControlCar controlCar = parseControlCar(listOfTrainCars.item(index));
                    train.addTrainCar(controlCar);
                } else if (listOfTrainCars.item(index).getNodeName().equals("passengerCar")) {
                    PassengerCar passengerCar = parsePassengerCar(listOfTrainCars.item(index));
                    train.addTrainCar(passengerCar);
                } else if (listOfTrainCars.item(index).getNodeName().equals("freightCar")) {
                    FreightCar freightCar = parseFreightCar(listOfTrainCars.item(index));
                    train.addTrainCar(freightCar);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return train;
    }

    private ControlCar parseControlCar(Node controlCarNode) {
        int trainCarType = 1;
        return  (ControlCar) parseTrainCar(trainCarType, controlCarNode);
    }

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

        if (trainCarType == 1) {
            parsedTrainCar = new ControlCar();
        } else if (trainCarType == 2) {
            parsedTrainCar = new FreightCar();
        }

        NodeList trainCarProperties = trainCarNode.getChildNodes();

        for (int index = 0; index < trainCarProperties.getLength(); index++) {
            if (trainCarProperties.item(index).getNodeType() == Node.ELEMENT_NODE) {
                if (((Element) trainCarProperties.item(index)).getTagName().equals("carNumber")) {
                    int carNumber = Integer.valueOf(trainCarProperties.item(index).getTextContent());
                    parsedTrainCar.setCarNumber(carNumber);
                }

                if (((Element) trainCarProperties.item(index)).getTagName().equals("weightCapacity")) {
                    int weightCapacity = Integer.valueOf(trainCarProperties.item(index).getTextContent());
                    parsedTrainCar.setWeightCapacity(weightCapacity);
                }
            }
        }

        return parsedTrainCar;
    }


    private PassengerCar parsePassengerCar (Node passengerCarNode) {
        PassengerCar parsedPassengerCar = new PassengerCar();

        NodeList passengerCarProperties = passengerCarNode.getChildNodes();

        for (int index = 0; index < passengerCarProperties.getLength(); index++) {
            if (passengerCarProperties.item(index).getNodeType() == Node.ELEMENT_NODE) {
                if (((Element) passengerCarProperties.item(index)).getTagName().equals("carNumber")) {
                    int carNumber = Integer.valueOf(passengerCarProperties.item(index).getTextContent());
                    parsedPassengerCar.setCarNumber(carNumber);
                }
                if (((Element) passengerCarProperties.item(index)).getTagName().equals("weightCapacity")) {
                    int weightCapacity = Integer.valueOf(passengerCarProperties.item(index).getTextContent());
                   parsedPassengerCar.setWeightCapacity(weightCapacity);
                }

                if (((Element) passengerCarProperties.item(index)).getTagName().equals("passengersCapacity")) {
                    int passengersCapacity = Integer.valueOf(passengerCarProperties.item(index).getTextContent());
                    parsedPassengerCar.setPassengersCapacity(passengersCapacity);
                }

                if (((Element) passengerCarProperties.item(index)).getTagName().equals("comfortLevel")) {
                    String comfortLevel = passengerCarProperties.item(index).getTextContent();
                    switch (comfortLevel) {
                        case "LOW":
                            parsedPassengerCar.setComfortLevel(ComfortLevel.LOW);
                            break;
                        case "MIDDLE":
                            parsedPassengerCar.setComfortLevel(ComfortLevel.MIDDLE);
                            break;
                        case "HIGH":
                            parsedPassengerCar.setComfortLevel(ComfortLevel.HIGH);
                            break;
                        default:
                            break;
                    }
                }
            }
        }


        return parsedPassengerCar;
    }
}

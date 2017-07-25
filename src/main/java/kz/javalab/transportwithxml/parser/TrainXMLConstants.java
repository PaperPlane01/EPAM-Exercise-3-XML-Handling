package kz.javalab.transportwithxml.parser;

/**
 * Created by PaperPlane on 24.07.2017.
 */
public class TrainXMLConstants {
    public static class TagNames {
        public static final String TRAIN = "train";
        public static final String TRAIN_ID = "trainID";
        public static final String TRAIN_CARS = "trainCars";
        public static final String PASSENGER_CAR = "passengerCar";
        public static final String CONTROL_CAR = "controlCar";
        public static final String FREIGHT_CAR = "freightCar";
        public static final String CAR_NUMBER = "carNumber";
        public static final String WEIGHT_CAPACITY = "weightCapacity";
        public static final String PASSENGERS_CAPACITY = "passengersCapacity";
        public static final String COMFORT_LEVEL = "comfortLevel";
    }

    public static class ComfortLevels {
        public static final String LOW = "LOW";
        public static final String MIDDLE = "MIDDLE";
        public static final String HIGH = "HIGH";
    }
}

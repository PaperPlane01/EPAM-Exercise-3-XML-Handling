package kz.javalab.transportwithxml.entity.trainmanager;

import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;
import kz.javalab.transportwithxml.entity.traincar.TrainCar;

import java.util.Comparator;


public class TrainCarsComparators {
    public static final Comparator<PassengerCar> COMFORT_LEVEL_ORDER = new Comparator<PassengerCar>() {

        @Override
        public int compare(PassengerCar firstCar, PassengerCar secondCar) {
           return (firstCar.getComfortLevel().compareTo(secondCar.getComfortLevel()));
        }
    };

    public static final Comparator<PassengerCar> PASSENGERS_CAPACITY_ORDER = new Comparator<PassengerCar>() {
        @Override
        public int compare(PassengerCar firstCar, PassengerCar secondCar) {
            return firstCar.getPassengersCapacity() - secondCar.getPassengersCapacity();
        }
    };

    public static final Comparator<TrainCar> WEIGHT_CAPACITY_ORDER = new Comparator<TrainCar>() {
        @Override
        public int compare(TrainCar firstCar, TrainCar secondCar) {
            return firstCar.getWeightCapacity() - secondCar.getWeightCapacity();
        }
    };

    private TrainCarsComparators() {

    }


}

package kz.javalab.transportwithxml.entity.trainmanager;

import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;
import kz.javalab.transportwithxml.entity.traincar.TrainCar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class designated for manipulating and processing data contained in an instance of <Code>Train</Code> class.
 */
public class TrainManager {

    /**
     * Instance of <Code>Train</Code> class which is to be processed by TrainManager.
     */
    private Train train;

    public TrainManager() {
        this.train = new Train();
    }

    /**
     * Creates an instance of <Code>TrainManager</Code> class.
     * @param train An instance of <Code>Train</Code> class which is to be manipulated.
     */
    public TrainManager(Train train) {
        this.train = train;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    /**
     * Weight capacity of the train.
     * @return Weight capacity of the train.
     */
    public int getWeightCapacity() {

        int weightCapacity = 0;

        for (TrainCar trainCar : train.getTrainCars()) {
            weightCapacity += trainCar.getWeightCapacity();
        }

        return weightCapacity;
    }


    /**
     * Counts passengers capacity of the train.
     * @return Passengers capacity of the train.
     */
    public int getPassengersCapacity() {
        int passengersCapacity = 0;

        for (TrainCar trainCar : train.getTrainCars()) {
            if (trainCar instanceof PassengerCar) {
                PassengerCar passengerCar = (PassengerCar) trainCar;
                passengersCapacity += passengerCar.getPassengersCapacity();
            }
        }

        return passengersCapacity;
    }

    /**
     * Creates a list of passenger cars sorted by comfort level.
     * @param reverseOrder Indicates if order of the list should be reversed.
     * @return A list of passenger cars sorted by comfort level.
     */
    public ArrayList<PassengerCar> getListOfPassengerCarsSortedByComfortLevel(boolean reverseOrder) {
        ArrayList<PassengerCar> passengerCars = new ArrayList<>();

        for (TrainCar trainCar : train.getTrainCars()) {
            if (trainCar instanceof PassengerCar) {
                passengerCars.add((PassengerCar) trainCar);
            }
        }

        if (reverseOrder) {
            Collections.sort(passengerCars, TrainCarsComparators.COMFORT_LEVEL_ORDER.reversed());
        } else {
            Collections.sort(passengerCars, TrainCarsComparators.COMFORT_LEVEL_ORDER);
        }

        return passengerCars;
    }

    /**
     * Creates a list of passenger cars with passengers capacity which is in specified range.
     * @param minValue A minimum value of passengers capacity.
     * @param maxValue A maximum value of passengers capacity.
     * @return A list of passenger cars with passengers capacity is in specified range.
     */
    public ArrayList<PassengerCar> getListOfPassengerCarsWithPassengersCapacityWithinRange(int minValue, int maxValue) {
        ArrayList<PassengerCar> passengerCars = new ArrayList<>();

        for (TrainCar trainCar : train.getTrainCars()) {
            if (trainCar instanceof PassengerCar) {
               int passengersCapacity = ((PassengerCar) trainCar).getPassengersCapacity();

               if ((passengersCapacity > minValue) && (passengersCapacity < maxValue)) {
                   passengerCars.add((PassengerCar) trainCar);
                }
            }
        }

        Collections.sort(passengerCars, TrainCarsComparators.PASSENGERS_CAPACITY_ORDER);

        return passengerCars;
    }
}

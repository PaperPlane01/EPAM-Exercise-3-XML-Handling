package kz.javalab.transportwithxml.entity.train;

import kz.javalab.transportwithxml.entity.traincar.TrainCar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Train {
    /**
     * Cars of the train.
     */
    private ArrayList<TrainCar> trainCars = new ArrayList<>();
    /**
     * ID of the train.
     */
    private long trainID;


    public Train() {
        trainCars = new ArrayList<>();
    }

    public Train(ArrayList<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }

    public Train(TrainCar[] trainCarsArray) {
        this.trainCars = (ArrayList<TrainCar>) Arrays.asList(trainCarsArray);
    }
    public ArrayList<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(ArrayList<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }

    public long getTrainID() {
        return trainID;
    }

    public void setTrainID(long trainID) {
        this.trainID = trainID;
    }


    /**
     * Returns a train car with a specified number (position in the train)
     * @param trainCarNumber Number of the train car.
     * @return A train car with a specified number (position in the train)
     */
    public TrainCar getTrainCarByNumber(int trainCarNumber) {
        return trainCars.get(trainCarNumber);
    }

    public void setTrainCarByNumber(int trainCarNumber, TrainCar trainCar) {
        trainCars.set(trainCarNumber, trainCar);
    }

    public void addTrainCar(TrainCar trainCar) {
        trainCars.add(trainCar);

    }

    @Override
    public String toString() {
        return "Train{" +
                "trainCars=" + trainCars +
                ", trainID=" + trainID +
                '}';
    }

    public int getNumberOfTrainCars() {
        return trainCars.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainID == train.trainID &&
                Objects.equals(trainCars, train.trainCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainCars, trainID);
    }
}

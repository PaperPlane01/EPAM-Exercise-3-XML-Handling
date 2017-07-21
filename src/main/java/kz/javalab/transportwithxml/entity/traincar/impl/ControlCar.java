package kz.javalab.transportwithxml.entity.traincar.impl;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;

/**
 * Class representing a control car of the train.
 */
public class ControlCar  extends TrainCar {

    public ControlCar() {
        super();
    }

    /**
     * Constructor with specified weight capacity and car number parameters.
     * @param weightCapacity Weight capacity of the
     */
    public ControlCar(int weightCapacity) {
        super(weightCapacity);
    }

    public int getWeightCapacity() {
        return super.getWeightCapacity();
    }

    public void setWeightCapacity(int weightCapacity) {
        super.setWeightCapacity(weightCapacity);
    }

    public int getCarNumber() {
        return super.getCarNumber();
    }

    public void setCarNumber(int carNumber) {
        super.setCarNumber(carNumber);
    }

    @Override
    public String toString() {
        return "ControlCar{" +
                "weightCapacity=" + super.getWeightCapacity() +
                ", carNumber=" + super.getCarNumber() +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

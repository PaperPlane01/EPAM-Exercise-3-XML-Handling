package kz.javalab.transportwithxml.entity.traincar.impl;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;


/**
 * Class representing a passenger car.
 */
public class PassengerCar extends TrainCar {

    /**
     * Passengers capacity of the passenger car.
     */
    private int passengersCapacity;

    /**
     * Comfort level of the passenger car.
     */
    private ComfortLevel comfortLevel;

    public PassengerCar() {
    }

    /**
     * Creates in instance of passenger car with specified parameters.
     * @param passengersCapacity Passengers capacity.
     * @param weightCapacity Weight capacity of the passenger car (in kg).
     * @param comfortLevel Comfort level of the passenger car.
     */
    public PassengerCar(int passengersCapacity, int weightCapacity, ComfortLevel comfortLevel) {
        super(weightCapacity);
        this.passengersCapacity = passengersCapacity;
        this.comfortLevel = comfortLevel;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(ComfortLevel comfortLevel) {
        this.comfortLevel = comfortLevel;
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
        return "PassengerCar{" +
                "passengersCapacity=" + passengersCapacity +
                ", weightCapacity=" + super.getWeightCapacity() +
                ", comfortLevel=" + comfortLevel +
                ", carNumber=" + super.getCarNumber() +
                '}';
    }

}

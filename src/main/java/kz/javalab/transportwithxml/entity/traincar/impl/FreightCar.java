package kz.javalab.transportwithxml.entity.traincar.impl;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;

public class FreightCar extends TrainCar {

    public FreightCar() {
        super();
    }

    public FreightCar(int weightCapacity) {
        super(weightCapacity);
    }

    public void setWeightCapacity(int weightCapacity) {
        super.setWeightCapacity(weightCapacity);
    }

    public int getWeightCapacity() {
        return super.getWeightCapacity();
    }

    public int getCarNumber() {
        return super.getCarNumber();
    }

    public void setCarNumber(int carNumber) {
        super.setCarNumber(carNumber);
    }

    @Override
    public String toString() {
        return "FreightCar{" +
                "weightCapacity=" + super.getWeightCapacity() +
                ", carNumber=" + super.getCarNumber() +
                '}';
    }
}

package kz.javalab.transportwithxml.entity.traincar;

import java.util.Objects;

public abstract class TrainCar  {

    private int weightCapacity;
    private int carNumber;

    public TrainCar() {
    }

    public TrainCar(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainCar trainCar = (TrainCar) o;
        return weightCapacity == trainCar.weightCapacity &&
                carNumber == trainCar.carNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weightCapacity, carNumber);
    }
}

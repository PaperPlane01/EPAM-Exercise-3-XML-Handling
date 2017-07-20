package kz.javalab.transportwithxml.entity.traincar;

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
}

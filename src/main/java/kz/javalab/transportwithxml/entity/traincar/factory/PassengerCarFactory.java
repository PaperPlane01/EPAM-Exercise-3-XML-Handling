package kz.javalab.transportwithxml.entity.traincar.factory;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;

public class PassengerCarFactory extends TrainCarFactory {

    @Override
    public TrainCar createTrainCar() {
        return new PassengerCar();
    }
}

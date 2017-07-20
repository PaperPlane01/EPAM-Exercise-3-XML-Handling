package kz.javalab.transportwithxml.entity.traincar.factory;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.ControlCar;

public class ControlCarFactory extends TrainCarFactory{
    @Override
    public TrainCar createTrainCar() {
        return new ControlCar();
    }
}

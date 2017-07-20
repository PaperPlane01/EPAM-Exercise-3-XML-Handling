package kz.javalab.transportwithxml.entity.traincar.factory;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.FreightCar;


public class FreightCarFactory extends TrainCarFactory {

    @Override
    public TrainCar createTrainCar() {
        return new FreightCar();
    }
}

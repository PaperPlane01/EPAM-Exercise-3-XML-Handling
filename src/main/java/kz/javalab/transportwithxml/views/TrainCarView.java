package kz.javalab.transportwithxml.views;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;

public abstract class TrainCarView implements View {

    public abstract TrainCar getModel();

    public abstract void setModel(TrainCar model);
}

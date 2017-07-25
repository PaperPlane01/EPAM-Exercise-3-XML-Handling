package kz.javalab.transportwithxml.views;


import kz.javalab.transportwithxml.entity.train.Train;
import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.ControlCar;
import kz.javalab.transportwithxml.entity.traincar.impl.FreightCar;
import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;
import kz.javalab.transportwithxml.entity.trainmanager.TrainManager;

import java.util.ArrayList;

/**
 * Class designated for visual representation of instance of <Class>Train</Class>.
 */
public class TrainView implements View {


    /**
     * Data structure to be displayed.
     */
    private Train model;
    /**
     * Visual representations of train's cars.
     */
    private ArrayList<TrainCarView> trainCarViews;

    public TrainView() {
        this.trainCarViews = new ArrayList<>();
    }

    public TrainView(Train model) {
        this.model = model;
        this.trainCarViews = new ArrayList<>();
        initTrainCarViews();
    }

    public Train getModel() {
        return model;
    }

    public void setTrain(Train train) {
        this.model = train;
    }

    @Override
    public void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Train ID: " + model.getTrainID() + "   ");
        stringBuilder.append("Passengers capacity: " + (new TrainManager(model)).getPassengersCapacity() + "   ");
        stringBuilder.append("Weight capacity: " + (new TrainManager(model)).getWeightCapacity());
        showMessage(stringBuilder.toString());
        showTrainCarsViews();
    }

    private void initTrainCarViews() {
        for (TrainCar trainCar : model.getTrainCars()) {
           if (trainCar instanceof ControlCar) {
               trainCarViews.add(new ControlCarView((ControlCar) trainCar));
           } else if (trainCar instanceof FreightCar) {
               trainCarViews.add(new FreightCarView((FreightCar) trainCar));
           } else if (trainCar instanceof PassengerCar) {
               trainCarViews.add(new PassengerCarView((PassengerCar) trainCar));
            }
        }
    }

    private void showTrainCarsViews() {
        for (TrainCarView trainCarView : trainCarViews) {
            trainCarView.show();
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}

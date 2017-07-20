package kz.javalab.transportwithxml.views;


import kz.javalab.transportwithxml.entity.traincar.impl.PassengerCar;
import kz.javalab.transportwithxml.entity.trainmanager.TrainManager;

/**
 * Class designated for visual representation of instance of <Class>TrainManager</Class>.
 */
public class TrainManagerView implements View {

    /**
     * Data structure to be displayed.
     */
    private TrainManager model;

    public TrainManagerView(TrainManager model) {
        this.model = model;
    }

    @Override
    public void show() {
        (new TrainView(model.getTrain())).show();
    }

    public void showListOfPassengerCarsSortedByComfortLevel(boolean reverse) {
        StringBuilder stringBuilder = new StringBuilder("List of passenger cars sorted by comfort level: ");

        if (reverse) {
            stringBuilder.append("(order is reversed)");
        }

        showMessage(stringBuilder.toString());

        for (PassengerCar passengerCar : model.getListOfPassengerCarsSortedByComfortLevel(reverse)) {
            PassengerCarView passengerCarView = new PassengerCarView(passengerCar);
            passengerCarView.show();
        }
    }

    public void showListOfPassengerCarsWithPassengersCapacityWithinRange(int minValue, int maxValue) {

        StringBuilder stringBuilder = new StringBuilder("List of passenger cars with capacity within range: ").append(minValue).append(" - ").append(maxValue);


        showMessage(stringBuilder.toString());

        for (PassengerCar passengerCar : model.getListOfPassengerCarsWithPassengersCapacityWithinRange(minValue, maxValue)) {
            PassengerCarView passengerCarView = new PassengerCarView(passengerCar);
            passengerCarView.show();
        }
    }

    public TrainManager getModel() {
        return model;
    }

    public void setModel(TrainManager model) {
        this.model = model;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}

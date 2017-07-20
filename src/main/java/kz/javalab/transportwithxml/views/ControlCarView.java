package kz.javalab.transportwithxml.views;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.ControlCar;

/**
 * Class designated for visual representation of instance of <Class>ControlCar</Class>.
 */
public class ControlCarView extends  TrainCarView implements View {

    /**
     * Data structure to be displayed.
     */
    private ControlCar model;

    public ControlCarView() {
        model = new ControlCar();
    }

    public ControlCarView(ControlCar model) {
        this.model = model;
    }

    @Override
    public ControlCar getModel() {
        return model;
    }

    @Override
    public void setModel(TrainCar model) {
        try {
            this.model = (ControlCar) model;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Train car type: Control car").append("   ");
        stringBuilder.append("Train car number: ").append(model.getCarNumber()).append("   ");
        stringBuilder.append("Weight capacity: ").append(model.getWeightCapacity()).append("   ");
        showMessage(stringBuilder.toString());
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }


}

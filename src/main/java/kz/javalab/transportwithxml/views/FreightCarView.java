package kz.javalab.transportwithxml.views;


import kz.javalab.transportwithxml.entity.traincar.TrainCar;
import kz.javalab.transportwithxml.entity.traincar.impl.FreightCar;

/**
 * Class designated for visual representation of instance of <Class>FreightCar</Class>.
 */
public class FreightCarView extends TrainCarView implements View {

    /**
     * Data structure to be displayed.
     */
    private FreightCar model;

    public FreightCarView() {
        this.model = new FreightCar();
    }

    public FreightCarView(FreightCar model) {
        this.model = model;
    }

    @Override
    public FreightCar getModel() {
        return model;
    }

    @Override
    public void setModel(TrainCar model) {
        this.model = (FreightCar) model;
    }

    @Override
    public void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Train car type: Freight car").append("   ");
        stringBuilder.append("Train car number: ").append(model.getCarNumber()).append("   ");
        stringBuilder.append("Weight capacity: ").append(model.getWeightCapacity());
        showMessage(stringBuilder.toString());
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}

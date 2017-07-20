package kz.javalab.transportwithxml.views;


public interface View {

    /**
     * Method designated to show view.
     */
    void show();

    /**
     * Method designated to display a message.
     * @param message Message to be shown.
     */
    void showMessage(String message);
}

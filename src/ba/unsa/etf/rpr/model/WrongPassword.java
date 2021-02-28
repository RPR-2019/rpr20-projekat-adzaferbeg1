package ba.unsa.etf.rpr.model;

public class WrongPassword extends Exception {
    public WrongPassword(String message){
        super(message);
    }
}

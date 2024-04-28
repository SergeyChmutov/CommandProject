package pro.dev.animalshelter.exception;

public class TravelDirectionsNotFoundException extends RuntimeException {
    public TravelDirectionsNotFoundException(String message) {
        super(message);
    }
}

package pro.dev.animalshelter.service;

public class SheltersNotFoundException extends RuntimeException {
    public SheltersNotFoundException(String massege) {
        super(massege);
    }
}

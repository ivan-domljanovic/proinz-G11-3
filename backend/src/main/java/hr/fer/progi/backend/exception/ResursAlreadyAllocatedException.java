package hr.fer.progi.backend.exception;

public class ResursAlreadyAllocatedException extends RuntimeException {
    public ResursAlreadyAllocatedException(String message) {
        super(message);
    }
}

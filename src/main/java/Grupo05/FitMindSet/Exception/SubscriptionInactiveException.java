package Grupo05.FitMindSet.Exception;

public class SubscriptionInactiveException extends RuntimeException {
    public SubscriptionInactiveException(String message) {
        super(message);
    }

    public SubscriptionInactiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
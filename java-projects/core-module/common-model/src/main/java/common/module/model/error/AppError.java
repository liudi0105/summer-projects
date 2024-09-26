package common.module.model.error;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }

    public AppError(Throwable cause) {
        super(cause);
    }
}

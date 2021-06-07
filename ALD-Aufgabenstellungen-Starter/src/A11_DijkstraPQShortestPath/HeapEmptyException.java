package A11_DijkstraPQShortestPath;

public class HeapEmptyException extends Exception {
    public HeapEmptyException() {
    }

    public HeapEmptyException(String message) {
        super(message);
    }

    public HeapEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeapEmptyException(Throwable cause) {
        super(cause);
    }

    public HeapEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

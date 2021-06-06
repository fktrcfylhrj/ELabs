package connection_pool;

public class PoolException extends Exception {
    PoolException() {}

    public PoolException(Throwable e) {
        super(e);
    }
}


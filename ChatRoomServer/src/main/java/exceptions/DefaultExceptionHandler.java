package exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LOGGER.error("Exception occurred {}", ex);
    }
}
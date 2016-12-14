package remoteejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 * Implements the remote EJB api.
 *
 */
@Stateless
public class LogMessageOnServerLogImpl implements LogMessageOnServerLog {
    private static final Logger LOGGER = Logger.getLogger(LogMessageOnServerLogImpl.class.getCanonicalName());

    private static final String BIG_MARKER = "\\o/\\o/\\o/\\o/\\o/\\o/\\o/\\o/\\o/\\o/\\o/";

    /**
     * Something gets logged into the server log using jdk logging.
     */
    @Override
    public void logOnServer(String messageToLog) {
        String header = String.format("%1$s%n%2$s%n%1$s%n", BIG_MARKER, messageToLog);
        LOGGER.info(header);
    }

}

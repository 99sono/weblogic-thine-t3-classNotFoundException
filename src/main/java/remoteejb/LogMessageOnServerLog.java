package remoteejb;

import javax.ejb.Remote;

/**
 * A remote EJB api that we will want to call in a system test.
 */
@Remote
public interface LogMessageOnServerLog {

    void logOnServer(String messageToLog);

}

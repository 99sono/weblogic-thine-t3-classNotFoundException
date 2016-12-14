package trimer;

import static java.util.Collections.synchronizedList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.Interceptors;

import interceptor.PostConstructInterceptor;

/**
 * This is just a timer modeled according to our timer pattern, to quickly verify injection issues on weblogic
 * container.
 *
 */
@Singleton
@Startup
@Interceptors(value = PostConstructInterceptor.class)
public class ConcreteTimerExample extends AbstractTimer {

    private static final Logger LOGGER = Logger.getLogger(ConcreteTimerExample.class.getCanonicalName());

    private final List<String> allOperationsExecutedOnTheTimer = synchronizedList(new ArrayList<String>());

    /**
     * Initialization gets the actual files in the folder to the setLastFileSet.
     */
    @PostConstruct
    public void postConstruct() {
        allOperationsExecutedOnTheTimer.add("postConstruct()");
        LOGGER.log(Level.INFO, "Post construct being called");

    }

    /**
     * This method does the scan and creates an event if something is changed and informs listener.
     */
    @Override
    public void executeTimerLogic() {
        allOperationsExecutedOnTheTimer.add("poll()");
        LOGGER.log(Level.INFO,
                "Timer run. Going to execute basic service which should have been injected on parent class.");
        getBasicService().doWork();
        String infoMsg = String.format(
                "The opeartions invoked on this timer - so far are: %n%n%1$s.%n%n"
                        + " If In the list above we see that postConstruct() has been invoked after toString() we have a bug in weblogic.",
                allOperationsExecutedOnTheTimer.toString());
        LOGGER.log(Level.INFO, infoMsg);
    }

    /**
     * This method creates a String with all properties of this timer task.
     *
     * @return comma-separated String with all properties of this task
     */
    @Override
    public String toString() {
        allOperationsExecutedOnTheTimer.add("toString()");
        LOGGER.log(Level.INFO, "Timer two string is being invoked.");
        return "I18nFileScannerTimer";
    }

}

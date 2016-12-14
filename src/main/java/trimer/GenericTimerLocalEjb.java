package trimer;

import javax.ejb.Local;

/**
 * A basic interface to start and stop a timer.
 *
 */
@Local
public interface GenericTimerLocalEjb {

    /**
     * Allows the timer to be started
     */
    void startTimer();

    /**
     * ALlows the timer to be stopped.
     */
    void stopTimer();

    /**
     * Executes the concrete timer logic
     */
    void executeTimerLogic();
}

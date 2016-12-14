package trimer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import service.BasicService;

/**
 * Abstract base timer. All timer classes will extend this
 */
public abstract class AbstractTimer implements GenericTimerLocalEjb {

    private static final Logger LOGGER = Logger.getLogger(AbstractTimer.class.getCanonicalName());

    @Resource
    TimerService timerService;

    @Inject
    BasicService basicService;

    volatile Timer currentTimer = null;

    private final ScheduleExpression scheduleExpression;

    private boolean suppressTracing = true;

    //
    /**
     * Create a new <code>AbstractWMTimer</code> with default timer schedule settings to fire every 30 seconds within
     * the minute.
     */
    public AbstractTimer() {
        this(new ScheduleExpression().hour("*").minute("*").second("*/5"));
    }

    /**
     * Create a new <code>AbstractWMTimer</code> using given timer schedule settings.
     */
    public AbstractTimer(ScheduleExpression scheduleExpression) {
        this.scheduleExpression = scheduleExpression;
    }

    // LifeCycle

    /**
     * EJB Timer time out function. This will trigger the WM 6 Timer poll method.
     */
    @Timeout
    public void timeout(Timer timer) {
        executeTimerLogic();
    }

    /**
     * Default timer schedule settings to fire every 30 seconds within the minute.
     */

    public ScheduleExpression getScheduleExpression() {
        return scheduleExpression;
    }

    @Override
    public void startTimer() {
        currentTimer = timerService.createCalendarTimer(scheduleExpression, new TimerConfig("TimerInfo", false));

    }

    @Override
    public void stopTimer() {
        try {
            if (currentTimer != null) {
                currentTimer.cancel();
            }
        } catch (NoSuchObjectLocalException ex) {
            LOGGER.log(Level.SEVERE, "Could not cancel a running timer with id  because of ", ex);
        } finally {
            currentTimer = null;
        }
    }

    public BasicService getBasicService() {
        return basicService;
    }

}

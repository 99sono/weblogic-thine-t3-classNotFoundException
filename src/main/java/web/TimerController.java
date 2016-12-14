package web;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import trimer.ConcreteTimerExample;

/**
 * A JSF bean that allows us to start and stop our dummy timer.
 */
@RequestScoped
@Named("timerController")
public class TimerController {
    private static final Logger LOGGER = Logger.getLogger(TimerController.class.getCanonicalName());

    final String controllerName = "Timer Controller";

    @Inject
    ConcreteTimerExample timer;

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("timerController - Request scoped bean constructed. The timer field is set with: " + timer);
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("timerController - Request scoped bean destroyed. ");
    }

    public String getControllerName() {
        return controllerName;
    }

    public String startTimer() {
        LOGGER.info("Timer controller is going to start the timer");
        timer.startTimer();
        return null;
    }

    public String stopTimer() {
        LOGGER.info("Timer controller is going to start the timer");
        timer.stopTimer();
        return null;
    }
}
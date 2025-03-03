package com.watu.scheduler ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class ActionScheduler {

    // Use the default logger
    private static final Logger logger = LoggerFactory.getLogger(ActionScheduler.class);

    private final List<Action> actions;

    public ActionScheduler() throws IOException {
        // Load actions from the CSV file at startup
        this.actions = CsvLoader.loadCsvFile("/actions.csv");
    }

    @Scheduled(fixedRate = 60_000) // Run every minute (60,000 milliseconds)
    public void checkAndExecuteActions() {
        logger.info("Scheduled task running at: {}", ZonedDateTime.now(ZoneId.of("Africa/Lagos")));


        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Africa/Lagos"));
        DayOfWeek currentDay = now.getDayOfWeek();
        LocalTime currentTime = now.toLocalTime();

        for (Action action : actions) {
            boolean isDayMatch = (action.getBitmask() & (1 << (currentDay.getValue() - 1))) != 0;
            if (isDayMatch && currentTime.equals(action.getTime())) {
                logger.info("Action executed at: {}", now);
            }
        }
    }
}
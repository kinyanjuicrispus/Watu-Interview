package com.watu.scheduler;

import java.time.LocalTime;

public class Action {
    private LocalTime time;
    private int bitmask;

    public Action(LocalTime time, int bitmask) {
        this.time = time;
        this.bitmask = bitmask;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getBitmask() {
        return bitmask;
    }
}
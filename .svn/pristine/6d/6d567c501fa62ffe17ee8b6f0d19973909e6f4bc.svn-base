package com.strongbulb.kickdiary.eventbus.holder;

import com.squareup.otto.Bus;
import com.strongbulb.kickdiary.eventbus.thread.OttoMainThreadBus;


/**
 * Created by JeonGuKang on 2017-01-11.
 */

public class OttoBusHolder extends Bus {

    private static final Bus mBus = new OttoMainThreadBus();

    public static Bus get() {
        return mBus;
    }

}

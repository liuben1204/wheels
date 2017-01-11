package com.bq.android.wheels.event;

/**
 * Created by liuben on 17-1-11.
 */

public class BaseEvent {
    public String id;

    public BaseEvent(String id) {
        this.id = id;
    }

    public BaseEvent() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

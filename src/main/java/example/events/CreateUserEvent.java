package example.events;

import fx.data.Event;
import fx.data.EventId;

public class CreateUserEvent implements Event {
    public static EventId id = new EventId("createUserEvent");

    @Override
    public EventId id() {
        return id;
    }

    @Override
    public Object payload() {
        return null;
    }
}

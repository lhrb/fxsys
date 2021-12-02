package example.events;

import example.DB;
import fx.data.Event;
import fx.data.EventId;

public class AddUserEvent implements Event<DB.User> {

    private final DB.User user;

    public AddUserEvent(DB.User user) {
        this.user = user;
    }

    @Override
    public EventId id() {
        return new EventId("addUserEvent");
    }

    @Override
    public DB.User payload() {
        return user;
    }
}

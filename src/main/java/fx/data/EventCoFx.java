package fx.data;

import fx.data.CoFxId;
import fx.data.Coeffect;
import fx.data.Event;

/**
 * Events get attach to the context map as co-effect. Th
 */
public class EventCoFx<T> implements Coeffect<Event<T>> {

    private final Event<T> event;

    public EventCoFx(Event<T> event) {
        this.event = event;
    }

    @Override
    public CoFxId id() {
        return CoFxId.EVENT;
    }

    @Override
    public Event payload() {
        return event;
    }
}

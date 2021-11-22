/**
 * representation of an event as data
 */
public interface Event<T> {

    /**
     * a unique event name
     */
    EventId id();

    /**
     * event data
     */
    T payload();
}

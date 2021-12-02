package fx.data;

/**
 * State of the world (ok admittedly just the part T) at the time the event happened.
 */
public interface Coeffect<T> {
    CoFxId id();
    T payload();
}

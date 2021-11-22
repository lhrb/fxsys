/**
 * representation of an effect as data
 */
public interface Effect<T> {

    /**
     * unique name of the effect
     */
    FxId id();

    /**
     * effect data
     */
    T payload();

}

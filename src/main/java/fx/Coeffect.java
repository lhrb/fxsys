package fx;

public interface Coeffect<T> {
    CoFxId id();
    T payload();
}
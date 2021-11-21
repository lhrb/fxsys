import java.util.*;

public class Context {
    private final Map<CofxName, Object> coeffects;
    private final Map<FxName, Object> effects;

    private final Queue<Interceptor> interceptorQueue;
    private final Stack<Interceptor> interceptorStack;

    public Context(Collection<Interceptor> interceptors) {
        coeffects = new HashMap<>();
        effects = new HashMap<>();
        interceptorQueue = new LinkedList<>(interceptors);
        interceptorStack = new Stack<>();
    }

    public <T> Context cofx(CofxName key, T cofx) {
        coeffects.put(key, cofx);
        return this;
    }

    public <T> Context fx(FxName key, T effect) {
        effects.put(key, effect);
        return this;
    }

    public <T> T getEffect(FxName key) {
        Object obj = effects.get(key);
        return (T) obj;
    }

    public <T> T getCoFx(CofxName key) {
        Object obj = coeffects.get(key);
        return (T) obj;
    }

    public <T> T getEvent() {
        return (T) coeffects.get(CofxName.event);
    }


    public void run() {
        while (runNext());
    }

    // experimental dirty method -> use iterable/iterator
    public boolean runNext() {
        Interceptor nextEnter = interceptorQueue.poll();
        if (nextEnter != null) {
            interceptorStack.push(nextEnter);
            System.out.println("Apply enter: " + nextEnter.name());
            nextEnter.enter().apply(this);
        } else {
            if (!interceptorStack.empty()) {
                Interceptor nextExit = interceptorStack.pop();
                System.out.println("Apply exit: " + nextExit.name());
                nextExit.exit().apply(this);
            } else {
                return false;
            }
        }
        return true;
    }

}

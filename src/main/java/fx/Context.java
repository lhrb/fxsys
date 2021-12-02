package fx;

import fx.data.CoFxId;
import fx.data.Coeffect;
import fx.data.Effect;
import fx.data.FxId;
import fx.data.EventCoFx;
import fx.interceptors.Interceptor;

import java.util.*;

public class Context {
    private final Map<CoFxId, Object> coeffects;
    private final Map<FxId, Object> effects;

    private final Queue<Interceptor> interceptorQueue;
    private final Stack<Interceptor> interceptorStack;

    public Context(Collection<Interceptor> interceptors) {
        coeffects = new HashMap<>();
        effects = new HashMap<>();
        interceptorQueue = new LinkedList<>(interceptors);
        interceptorStack = new Stack<>();
    }

    public <T> Context addCoFx(Coeffect<T> coeffect) {
        coeffects.put(coeffect.id(), coeffect);
        return this;
    }

    public <T> Context addFx(Effect<T> effect) {
        effects.put(effect.id(), effect);
        return this;
    }

    public <T> T getEffect(FxId key) {
        Object obj = effects.get(key);
        return (T) obj;
    }

    public <T> T getCoFx(CoFxId key) {
        Object obj = coeffects.get(key);
        return (T) obj;
    }

    public <T> T getEvent() {
        EventCoFx event = (EventCoFx) coeffects.get(CoFxId.EVENT);
        return (T) event.payload();
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

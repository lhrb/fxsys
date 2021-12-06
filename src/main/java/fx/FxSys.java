package fx;

import fx.data.Event;
import fx.data.EventCoFx;
import fx.data.EventId;
import fx.interceptors.Interceptor;

import java.util.*;
import java.util.concurrent.*;

public class FxSys {

    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final BlockingQueue<Event> workQueue = new ArrayBlockingQueue<>(10);
    private final Map<EventId, Collection<Interceptor>> routes = new HashMap<>();

    public FxSys() {
        // this is probably somewhat over-engineered
        exec.submit(() -> {
            try {
                while (true)
                    processEvent(workQueue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public FxSys registerEventRoute(EventId eventId, Collection<Interceptor> route)
    {
        routes.put(eventId, route);
        return this;
    }

    public <T> FxSys dispatch(Event<T> event)
    {
        workQueue.offer(event);
        return this;
    }

    public void shutdown() {
        exec.shutdown();
        try {
            exec.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private <T> void processEvent(Event<T> event) {
        if (routes.containsKey(event.id())) {
            var eventCoFx = new EventCoFx<>(event);
            new Context(routes.get(event.id()))
                    .addCoFx(eventCoFx)
                    .run();

        }
    }
}

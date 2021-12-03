package example;

import example.effects.DbFxHandler;
import example.effects.InputFxHandler;
import example.events.AddUserEvent;
import example.events.AddUserEventHandler;
import example.events.CreateUserEvent;
import example.events.CreateUserEventHandler;
import fx.FxSys;
import fx.interceptors.Interceptor;

import java.util.Arrays;
import java.util.Collection;

public class Example {

    public static void main(String[] args) throws InterruptedException {
        var fxSystem = new FxSys();

        var db = new DB();
        Collection<Interceptor> interceptors = Arrays.asList(
                new DbFxHandler(db),
                new AddUserEventHandler()
        );

        Collection<Interceptor> createUser = Arrays.asList(
                new InputFxHandler(fxSystem::dispatch),
                new CreateUserEventHandler()
        );

        fxSystem.registerEventRoute(AddUserEvent.id, interceptors);
        fxSystem.registerEventRoute(CreateUserEvent.id, createUser);

        fxSystem.dispatch(new AddUserEvent(new DB.User("Peter", 30)));
        fxSystem.dispatch(new AddUserEvent(new DB.User("Peter", 30)));
        fxSystem.dispatch(new AddUserEvent(new DB.User("Hilde", 32)));
        fxSystem.dispatch(new CreateUserEvent());

        while (db.getUsers().size() < 3) {
            Thread.sleep(500);
        }

        db.getUsers().forEach(System.out::println);
        System.exit(0);
    }
}

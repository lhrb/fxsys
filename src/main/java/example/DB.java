package example;

import java.util.ArrayList;
import java.util.Collection;

public class DB {
    public record User(String name, Integer age) {}

    private final Collection<User> users = new ArrayList<>();

    public DB addUser(User newUser) {
        users.add(newUser);
        return this;
    }

    public Collection<User> getUsers() {
        return users;
    }
}

package ru.atom.http.server;

import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.HashSet;
import java.util.Set;

public class UserService implements UserServiceDAO {

    private static UserService instance;

    public static UserService getInstance () {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    private UserService () {}

    private Set<User> users = new ConcurrentHashSet<>();

    @Override
    public void login(User user) {
        users.add(user);
    }

    @Override
    public void logout(User user) {
        users.remove(user);
    }

    @Override
    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name))
                return u;
        }
        throw new NullPointerException("User not found.");
    }

    @Override
    public Set<User> getOnline() {
        return users;
    }
}

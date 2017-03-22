package ru.atom.http.server;

import java.util.Set;

public interface UserServiceDAO {

    void login (String name);
    void logout (String name);
    Set<User> getOnline ();
}

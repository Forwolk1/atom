package ru.atom.http.server;

import java.util.Set;

public interface UserServiceDAO {

    /**
     * Залогинить пользователя
     *
     * @param user кого залогинить
     */
    void login (User user);

    /**
     * Разлогинить пользователя
     *
     * @param user кого разлогинить
     */
    void logout (User user);

    /**
     * Получить пользователя по имени
     *
     * @param name имя пользователя
     * @return объект User
     */
    User getUserByName (String name);

    /**
     * Получить список пользователей онлайн
     *
     * @return User'ы онлайн
     */
    Set<User> getOnline ();
}

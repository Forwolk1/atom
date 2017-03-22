package ru.atom.http.server;

public interface LoggerDAO {

    /**
     * Человек залогинился
     *
     * @param name имя пользователя
     */
    void login (String name);

    /**
     * Человек пишет в чат
     *
     * @param name имя пользователя
     * @param message сообщение
     */
    void sendMessage (String name, String message);
}

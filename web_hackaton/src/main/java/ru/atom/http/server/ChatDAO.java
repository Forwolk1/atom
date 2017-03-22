package ru.atom.http.server;

import java.io.IOException;
import java.util.List;

public interface ChatDAO {

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

    /**
     * Получение списка сообщений
     *
     * @param n количество последних сообщений
     * @return последние n сообщений
     */
    List<Message> getMessages (int n);
}

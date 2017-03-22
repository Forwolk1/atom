package ru.atom.http;

import org.junit.After;
import org.junit.Test;
import ru.atom.http.server.User;
import ru.atom.http.server.UserService;
import ru.atom.http.server.UserServiceDAO;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserServiceDAO dao = UserService.getInstance();

    @After
    public void logoutAll () {
        dao.getOnline().stream().forEach(u -> dao.logout(u));
    }

    @Test
    public void loginTest () {
        User u1 = new User("Вася", "qwerty12300");
        User u2 = new User("Петя", "Lalka228");

        dao.login(u1);
        assertTrue(dao.getOnline().stream().anyMatch(u -> u.equals(u1)));
        assertFalse(dao.getOnline().stream().anyMatch(u -> u.equals(u2)));
    }

    @Test
    public void logoutTest () {
        User u1 = new User("Вася", "qwerty12300");
        User u2 = new User("Петя", "Lalka228");

        dao.login(u1);
        assertTrue(dao.getOnline().stream().anyMatch(u -> u.equals(u1)));
        assertFalse(dao.getOnline().stream().anyMatch(u -> u.equals(u2)));

        dao.login(u2);
        dao.logout(u1);

        assertFalse(dao.getOnline().stream().anyMatch(u -> u.equals(u1)));
        assertTrue(dao.getOnline().stream().anyMatch(u -> u.equals(u2)));
    }

    @Test
    public void getUserByNameTest () {
        User u1 = new User("Вася", "qwerty12300");
        User u2 = new User("Петя", "Lalka228");

        dao.login(u1);
        dao.login(u2);

        assertEquals(u1, dao.getUserByName("Вася"));
        assertEquals(u2, dao.getUserByName("Петя"));
    }

    @Test (expected=RuntimeException.class)
    public void getUnexistingUser () {
        dao.getUserByName("Паша");
    }
}

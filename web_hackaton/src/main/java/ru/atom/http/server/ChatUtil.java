package ru.atom.http.server;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatUtil implements ChatDAO {
    private List<Message> messages = new ArrayList<>();

    private ChatUtil () {}

    private static ChatUtil instance;

    public static ChatUtil getInstance () {
        if (instance == null)
            instance = new ChatUtil();
        return instance;
    }

    @Override
    public void login(String name) throws IOException {
        Date currDate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");
        String strDate = format.format(currDate);
        String content = strDate + ": User " + name + " was loggined";
        Message message = new Message(currDate, name, content);
        messages.add(message);
        saveToFile(messages);
    }

    @Override
    public void sendMessage(String name, String message) {
        Date currDate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");
        String strDate = format.format(currDate);
        String content = strDate + ":[" + name + "]:" + message;
        Message mes = new Message(currDate, name, content);
        messages.add(mes);
        saveToFile(messages);
    }

    @Override
    public List<Message> getMessages(int n) {
        List<Message> returnList = new ArrayList<>();
        if (n > messages.size()) {
            throw new IndexOutOfBoundsException("Not so much messages");
        } else {
            returnList = messages.subList(messages.size() - n - 1, messages.size() - 1);
            return returnList;
        }


    }

    private static void saveToFile(List<Message> list) {
        File file = new File("~/atom.dat");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {

                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }
}

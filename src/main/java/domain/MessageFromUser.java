package domain;

import java.io.Serializable;

public class MessageFromUser implements Entity, Serializable {
    private String name;
    private String email;
    private String topic;
    private String text;
    private int id;
    private static int currentID;

    public MessageFromUser(String name, String email, String topic, String text) {
        this.name = name;
        this.email = email;
        this.topic = topic;
        this.text = text;
        this.id = ++currentID;
    }

    public MessageFromUser() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

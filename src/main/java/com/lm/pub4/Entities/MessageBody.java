package com.lm.pub4.Entities;

public class MessageBody {

    String id;
    String topicName;
    String text;

    public String getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getText() {
        return text;
    }

    public MessageBody(String id, String topicName, String text) {
        this.id = id;
        this.topicName = topicName;
        this.text = text;
    }
}

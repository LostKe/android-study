package com.example.event_bus;

/**
 * Created by youx on 2016-12-05.
 */
public class ItemEvent {

    private int id;
    private String content;

    public ItemEvent() {
    }

    public ItemEvent(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
